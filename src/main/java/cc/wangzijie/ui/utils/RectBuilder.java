package cc.wangzijie.ui.utils;

import cc.wangzijie.ui.model.ScreenshotAreaModel;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class RectBuilder {

    private StackPane node;

    private ScreenshotAreaModel screenshotAreaModel;

    // 动态矩形框
    private Rectangle selectionRect;

    // 鼠标拖拽起点
    private Point2D dragStart;


    public RectBuilder(StackPane node, ScreenshotAreaModel screenshotAreaModel) {
        this.node = node;
        this.screenshotAreaModel = screenshotAreaModel;
    }

    public void onMousePressed(MouseEvent e) {
        // 鼠标拖拽起点
        this.dragStart = new Point2D(e.getX(), e.getY());
        log.info("==== 鼠标拖拽起点 ====\nevent button={}\nevent x={}, y={}\nscene x={} y={}\nscreen x={} y={}",
                e.getButton(), e.getX(), e.getY(), e.getSceneX(), e.getSceneY(), e.getScreenX(), e.getScreenY());

        // 添加框选矩形框
        this.selectionRect = new Rectangle();
        this.selectionRect.setTranslateX(e.getX());
        this.selectionRect.setTranslateY(e.getY());
        this.selectionRect.setFill(Color.TRANSPARENT);
        this.selectionRect.setStroke(Color.RED);
        this.node.getChildren().add(this.selectionRect);
        screenshotAreaModel.addRect(this.selectionRect);
    }

    public void onMouseDragged(MouseEvent e) {
        // 计算相对坐标‌
        double width = Math.abs(e.getX() - this.dragStart.getX());
        double height = Math.abs(e.getY() - this.dragStart.getY());
        this.selectionRect.setWidth(width);
        this.selectionRect.setHeight(height);
    }

    public Rectangle onMouseReleased(MouseEvent e) {
        log.info("==== 鼠标拖拽结束 ====\nevent button={}\nevent x={}, y={}\nscene x={} y={}\nscreen x={} y={}",
                e.getButton(), e.getX(), e.getY(), e.getSceneX(), e.getSceneY(), e.getScreenX(), e.getScreenY());

        // 计算相对坐标‌
        double width = Math.abs(e.getX() - this.dragStart.getX());
        double height = Math.abs(e.getY() - this.dragStart.getY());
        this.selectionRect.setWidth(width);
        this.selectionRect.setHeight(height);
        log.info("==== 鼠标拖拽结束 ==== \n拖拽起点： x={} y={} \n当前位置：x={} y={} \n当前选择框：width={} height={}",
                this.dragStart.getX(), this.dragStart.getY(), e.getX(), e.getY(), width, height);
        return this.selectionRect;
    }
}
