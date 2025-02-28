package cc.wangzijie.ui.view;


import cc.wangzijie.constants.Constants;
import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.MainWindowModel;
import cc.wangzijie.ui.screenshot.ScreenCaptureStage;
import cc.wangzijie.ui.utils.ImageLoader;
import cc.wangzijie.ui.utils.WindowSizeHolder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class MainWindowView implements Initializable {

//    @Resource
//    private SpringFxmlLoader springFxmlLoader;

    @Resource
    private StageManager stageManager;

    @Resource
    private MainWindowModel mainWindowModel;


    @FXML
    private ImageView mainWindowLogoImage;


    @FXML
    private ImageView closeWindowButtonImage;
    @FXML
    private ImageView maximizeWindowButtonImage;
    @FXML
    private ImageView minimizeWindowButtonImage;
    @FXML
    private ImageView openSettingsWindowButtonImage;
    @FXML
    private ImageView reloadMainWindowButtonImage;


    @FXML
    private ImageView openFullMenuButtonImage;
    @FXML
    private ImageView historyDataMenuButtonImage;
    @FXML
    private ImageView screenshotMenuButtonImage;
    @FXML
    private ImageView withdrawMenuButtonImage;
    @FXML
    private ImageView startCollectMenuButtonImage;
    @FXML
    private ImageView stopCollectMenuButtonImage;


    @FXML
    private ImageView screenshotImage;


    @FXML
    private ImageView dataListTitleBarMenuButtonImage;
    @FXML
    private Label dataListTitleBarMenuTitle;


    @FXML
    private ImageView dataListTitleBarSearchButtonImage;
    @FXML
    private ImageView dataListTitleBarDeleteButtonImage;

    private double offsetX;
    private double offsetY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定FXML组件与model属性 - 标题栏logo
        mainWindowLogoImage.imageProperty().bindBidirectional(mainWindowModel.mainWindowLogoImageProperty());

        // 绑定FXML组件与model属性 - 标题栏右侧窗口按钮
        closeWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.closeWindowButtonImageProperty());
        maximizeWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.maximizeWindowButtonImageProperty());
        minimizeWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.minimizeWindowButtonImageProperty());
        openSettingsWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.openSettingsWindowButtonImageProperty());
        reloadMainWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.reloadMainWindowButtonImageProperty());

        // 绑定FXML组件与model属性 - 菜单栏按钮
        openFullMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.openFullMenuButtonImageProperty());
        historyDataMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.historyDataMenuButtonImageProperty());
        screenshotMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.screenshotMenuButtonImageProperty());
        withdrawMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.withdrawMenuButtonImageProperty());
        startCollectMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.startCollectMenuButtonImageProperty());
        stopCollectMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.stopCollectMenuButtonImageProperty());

        // 绑定FXML组件与model属性 - 主界面 - 左侧截屏图片预览
        screenshotImage.imageProperty().bindBidirectional(mainWindowModel.screenshotImageProperty());

        // 绑定FXML组件与model属性 - 主界面 - 右侧数据列表 - 标题栏
        dataListTitleBarMenuButtonImage.imageProperty().bindBidirectional(mainWindowModel.dataListTitleBarMenuButtonImageProperty());
        dataListTitleBarMenuTitle.textProperty().bind(mainWindowModel.dataListTitleBarMenuTitleProperty());
        dataListTitleBarSearchButtonImage.imageProperty().bindBidirectional(mainWindowModel.dataListTitleBarSearchButtonImageProperty());
        dataListTitleBarDeleteButtonImage.imageProperty().bindBidirectional(mainWindowModel.dataListTitleBarDeleteButtonImageProperty());


        // 处理model属性 - 标题栏logo
        mainWindowModel.setMainWindowLogoImage(ImageLoader.load(Constants.LOGO_IMAGE_PATH));

        // 处理model属性 - 标题栏右侧窗口按钮
        mainWindowModel.setCloseWindowButtonImage(ImageLoader.load(Constants.CLOSE_IMAGE_PATH));
        mainWindowModel.setMaximizeWindowButtonImage(ImageLoader.load(Constants.MAXIMIZE_IMAGE_PATH));
        mainWindowModel.setMinimizeWindowButtonImage(ImageLoader.load(Constants.MINIMIZE_IMAGE_PATH));
        mainWindowModel.setOpenSettingsWindowButtonImage(ImageLoader.load(Constants.SETTINGS_IMAGE_PATH));
        mainWindowModel.setReloadMainWindowButtonImage(ImageLoader.load(Constants.RELOAD_IMAGE_PATH));

        // 处理model属性 - 菜单栏按钮
        mainWindowModel.setOpenFullMenuButtonImage(ImageLoader.load(Constants.DRAG_IMAGE_PATH));
        mainWindowModel.setHistoryDataMenuButtonImage(ImageLoader.load(Constants.HISTORY_DATA_IMAGE_PATH));
        mainWindowModel.setScreenshotMenuButtonImage(ImageLoader.load(Constants.SCREENSHOT_BLUE_IMAGE_PATH));
        mainWindowModel.setWithdrawMenuButtonImage(ImageLoader.load(Constants.WITHDRAW_WHITE_IMAGE_PATH));
        mainWindowModel.setStartCollectMenuButtonImage(ImageLoader.load(Constants.RUN_BLUE_IMAGE_PATH));
        mainWindowModel.setStopCollectMenuButtonImage(ImageLoader.load(Constants.PAUSE_GREY_IMAGE_PATH));
        mainWindowModel.setCollectRunningFlag(false);

        // 处理model属性 - 主界面 - 左侧截屏图片预览区域
        mainWindowModel.setScreenshotImage(ImageLoader.load(Constants.SCREEN_CAPTURE_IMAGE_PATH));
        mainWindowModel.setScreenshotAreaHasImageFlag(false);

        // 处理model属性 - 主界面 - 右侧数据列表区域 - 标题栏
        mainWindowModel.setDataListTitleBarMenuButtonImage(ImageLoader.load(Constants.DRAG_IMAGE_PATH));
        mainWindowModel.setDataListTitleBarMenuTitle(Constants.DATA_LIST_TITLE);
        mainWindowModel.setDataListTitleBarSearchButtonImage(ImageLoader.load(Constants.SEARCH_IMAGE_PATH));
        mainWindowModel.setDataListTitleBarDeleteButtonImage(ImageLoader.load(Constants.DELETE_IMAGE_PATH));
    }

    @FXML
    protected void onMainWindowMousePressed(MouseEvent event) {
        this.offsetX = event.getSceneX();
        this.offsetY = event.getSceneY();
    }

    @FXML
    protected void onMainWindowMouseDragged(MouseEvent event) {
        Stage stage = stageManager.getMainWindowStage();
        if (null != stage) {
            Platform.runLater(() -> {
                stage.setX(event.getScreenX() - offsetX);
                stage.setY(event.getScreenY() - offsetY);
            });
        }
    }

    @FXML
    protected void onCloseWindowButtonClick() {
        log.info("==== onCloseWindowButtonClick ==== 点击【关闭窗口】按钮，退出！");
        Platform.exit();
        SpringHelper.close();
    }

    @FXML
    protected void onMaximizeWindowButtonClick() {
        log.info("==== onMaximizeWindowButtonClick ==== 点击【最大化窗口/恢复窗口大小】按钮！");
        Stage stage = stageManager.getMainWindowStage();
        if (null != stage) {
            if (WindowSizeHolder.isMaximized()) {
                // 关闭全屏
                log.info("==== onMaximizeWindowButtonClick ==== 恢复窗口大小！");
                Platform.runLater(() -> {
                    WindowSizeHolder.restore(stage);
                });
                mainWindowModel.setMaximizeWindowButtonImage(ImageLoader.load(Constants.MAXIMIZE_IMAGE_PATH));
            } else {
                // 全屏
                log.info("==== onMaximizeWindowButtonClick ==== 最大化窗口！");
                WindowSizeHolder.backup(stage);
                Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
                log.info("==== WindowSizeHolder ==== 最大化的窗口位置大小：visualBounds={}", visualBounds);
                Platform.runLater(() -> {
                    stage.setX(visualBounds.getMinX());
                    stage.setY(visualBounds.getMinY());
                    stage.setWidth(visualBounds.getWidth());
                    stage.setHeight(visualBounds.getHeight());
                });
                mainWindowModel.setMaximizeWindowButtonImage(ImageLoader.load(Constants.RESIZE_IMAGE_PATH));
            }
        }
    }

    @FXML
    protected void onMinimizeWindowButtonClick() {
        log.info("==== onMinimizeWindowButtonClick ==== 点击【最小化窗口】按钮！");
        Stage stage = stageManager.getMainWindowStage();
        if (null != stage && !stage.isIconified()) {
            Platform.runLater(() -> stage.setIconified(true));
        }
    }

    @FXML
    protected void onOpenSettingsWindowButtonClick() {
        log.info("==== onOpenSettingsWindowButtonClick ==== 点击【打开[设置]窗口】按钮！");
//        Stage settingsWindowStage = stageManager.getSettingsWindowStage();
//        if (null == settingsWindowStage) {
//            settingsWindowStage = new Stage();
//            springFxmlLoader.loadTo(FxmlViews.SETTINGS_WINDOW, settingsWindowStage);
//
//        }
    }

    @FXML
    protected void onReloadButtonClick() {
        log.info("==== onReloadButtonClick ==== 点击【重新加载】按钮！");

    }


    @FXML
    protected void onOpenFullMenuButtonClick() {
        log.info("==== onOpenFullMenuButtonClick ==== 点击【菜单栏】按钮！");

    }


    @FXML
    protected void onHistoryDataMenuButtonClick() {
        log.info("==== onHistoryDataMenuButtonClick ==== 点击【历史数据】按钮！");

    }

    @FXML
    protected void onScreenshotMenuButtonClick() {
        log.info("==== onScreenshotMenuButtonClick ==== 点击【截屏】按钮！");
        Stage stage = stageManager.getMainWindowStage();
        ScreenCaptureStage screenCaptureStage = new ScreenCaptureStage(stage, mainWindowModel);
        screenCaptureStage.show();
    }

    @FXML
    protected void onWithdrawMenuButtonClick() {
        log.info("==== onWithdrawMenuButtonClick ==== 点击【撤回上一步】按钮！");

    }

    @FXML
    protected void onStartCollectMenuButtonClick() {
        log.info("==== onStartCollectMenuButtonClick ==== 点击【开始采集】按钮！");

    }

    @FXML
    protected void onStopCollectMenuButtonClick() {
        log.info("==== onStopCollectMenuButtonClick ==== 点击【结束采集】按钮！");

    }

    @FXML
    protected void onDataListTitleBarMenuButtonClick() {
        log.info("==== onDataListTitleBarMenuButtonClick ==== 点击【数据列表区域-菜单栏】按钮！");

    }

    @FXML
    protected void onDataListTitleBarSearchButtonClick() {
        log.info("==== onDataListTitleBarSearchButtonClick ==== 点击【数据列表区域-菜单栏-搜索】按钮！");

    }

    @FXML
    protected void onDataListTitleBarDeleteButtonClick() {
        log.info("==== onDataListTitleBarDeleteButtonClick ==== 点击【数据列表区域-菜单栏-删除】按钮！");

    }

}
