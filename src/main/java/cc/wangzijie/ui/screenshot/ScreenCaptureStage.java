package cc.wangzijie.ui.screenshot;

import cc.wangzijie.ui.model.ScreenshotAreaModel;
import cc.wangzijie.ui.view.MainWindowView;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScreenCaptureStage {
    private final Stage mainWindowStage;
    private final MainWindowView mainWindowView;
    private final ScreenshotAreaModel screenshotAreaModel;
    private Stage stage;
    private Scene scene;

    // 动态矩形框
    private final Rectangle selectionRect = new Rectangle();

    // 鼠标拖拽起点
    private Point2D dragStart = null;

    public ScreenCaptureStage(Stage mainWindowStage, MainWindowView mainWindowView, ScreenshotAreaModel screenshotAreaModel) {
        this.mainWindowStage = mainWindowStage;
        this.mainWindowView = mainWindowView;
        this.screenshotAreaModel = screenshotAreaModel;
        // 1. 初始化全屏遮罩窗口
        initScreenshotStage();
    }

    public void show() {
        log.info("==== 显示截屏全屏遮罩窗口 ===");
        if (null != mainWindowStage && mainWindowStage.isShowing()) {
            Platform.runLater(mainWindowStage::hide);
        }
        stage.show();
    }

    /**
     * 初始化全屏遮罩窗口
     */
    protected void initScreenshotStage() {
        // 半透明背景‌
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: rgba(0,0,0,0.1);");

        // 创建舞台和场景
        this.scene = new Scene(root);
        this.scene.setFill(Color.TRANSPARENT);
        this.initScene(root);
        this.stage = new Stage();
        this.stage.setScene(this.scene);

        // 无边框透明窗口‌ + 全屏模式‌
        this.stage.initStyle(StageStyle.TRANSPARENT);
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint(null);
        this.stage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                this.stage.close();
            }
        });
    }

    protected void initScene(AnchorPane root) {
        this.scene.setOnMousePressed(e -> {
            log.debug("==== 鼠标按下事件 ==== x={} y={} screenX={} screenY={}",
                    e.getX(), e.getY(), e.getScreenX(), e.getScreenY());
            // 添加框选图形‌
            this.dragStart = new Point2D(e.getScreenX(), e.getScreenY());
            this.selectionRect.setLayoutX(e.getX());
            this.selectionRect.setLayoutY(e.getY());
            this.selectionRect.setFill(Color.TRANSPARENT);
            this.selectionRect.setStroke(Color.RED);
            root.getChildren().add(this.selectionRect);
        });

        this.scene.setOnMouseDragged(e -> {
            // 计算相对坐标‌
            double width = Math.abs(e.getScreenX() - this.dragStart.getX());
            double height = Math.abs(e.getScreenY() - this.dragStart.getY());
            this.selectionRect.setWidth(width);
            this.selectionRect.setHeight(height);
        });

        this.scene.setOnMouseReleased(e -> {
            // 计算相对坐标‌
            double width = Math.abs(e.getScreenX() - this.dragStart.getX());
            double height = Math.abs(e.getScreenY() - this.dragStart.getY());
            this.selectionRect.setWidth(width);
            this.selectionRect.setHeight(height);
            log.debug("==== 鼠标放松事件 ==== \n拖拽起点： screenX={} screenY={} \n当前位置：screenX={} screenY={} \n当前选择框：width={} height={}",
                    this.dragStart.getX(), this.dragStart.getY(), e.getScreenX(), e.getScreenY(), width, height);

            root.getChildren().remove(this.selectionRect);
            this.stage.close();
            // 执行截图操作‌
            this.captureAndSave(this.selectionRect);
        });
    }

    /**
     * 屏幕捕获与保存
     */
    private void captureAndSave(Rectangle rect) {
        log.info("==== 执行截屏 === rect={}", rect);
        try {
            // 设置截屏区域
            this.screenshotAreaModel.setScreenshotArea(rect);

            // 捕获屏幕区域‌
            this.screenshotAreaModel.doScreenshot();

            // 恢复主窗口显示
            if (null != mainWindowStage && !mainWindowStage.isShowing()) {
                Platform.runLater(mainWindowStage::show);
            }

            this.mainWindowView.onReload();
        } catch (Exception ex) {
            log.error("截图失败！", ex);
        }
    }

}
