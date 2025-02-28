package cc.wangzijie.ui.view;


import cc.wangzijie.fxml.loader.SpringFxmlLoader;
import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.SettingsWindowModel;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class SettingsWindowView implements Initializable {

    @Resource
    private SpringFxmlLoader springFxmlLoader;

    @Resource
    private StageManager stageManager;

    @Resource
    private SettingsWindowModel settingsWindowModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定FXML组件与model属性
//        mainWindowLogoImage.imageProperty().bindBidirectional(mainWindowModel.mainWindowLogoImageProperty());
//        mainWindowTitle.textProperty().bindBidirectional(mainWindowModel.mainWindowTitleProperty());
//        mousePosition.textProperty().bindBidirectional(mainWindowModel.mousePositionProperty());
//        welcomeText.textProperty().bind(mainWindowModel.welcomeTextProperty());

        // 处理model属性
//        Image logoImage = ImageLoader.load(Constants.LOGO_IMAGE_PATH);
//        mainWindowModel.setMainWindowLogoImage(logoImage);
//        mainWindowModel.setMainWindowTitle(Constants.MAIN_WINDOW_TITLE);
//        mainWindowModel.mousePositionProperty().bind(mousePositionModel.displayTextProperty());
    }

//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!" + System.currentTimeMillis());
//        Stage stage = stageManager.getMainWindowStage();
//        if (null != stage) {
//            stage.getScene().setOnMouseClicked(mousePositionModel::refresh);
//        }
//    }
//
//    @FXML
//    protected void onCloseWindowButtonClick() {
//        log.info("==== onCloseWindowButtonClick ==== 点击【关闭窗口】按钮，退出！");
//        SpringHelper.close();
//        Platform.exit();
//    }
//
//    @FXML
//    protected void onMaximizeWindowButtonClick() {
//        log.info("==== onMaximizeWindowButtonClick ==== 点击【最大化窗口】按钮！");
//        Stage stage = stageManager.getMainWindowStage();
//        if (null != stage && !stage.isFullScreen()) {
//            stage.setFullScreen(true);
//        }
//    }
//
//    @FXML
//    protected void onMinimizeWindowButtonClick() {
//        log.info("==== onMinimizeWindowButtonClick ==== 点击【最小化窗口】按钮！");
//        Stage stage = stageManager.getMainWindowStage();
//        if (null != stage && stage.isShowing()) {
//            stage.hide();
//        }
//    }
//
//    @FXML
//    protected void onOpenSettingsWindowButtonClick() {
//        log.info("==== onOpenSettingsWindowButtonClick ==== 点击【打开[设置]窗口】按钮！");
//        Stage settingsWindowStage = stageManager.getSettingsWindowStage();
//        if (null == settingsWindowStage) {
//            settingsWindowStage = new Stage();
//            springFxmlLoader.loadTo(FxmlViews.SETTINGS_WINDOW, settingsWindowStage);
//
//        }
//    }
//
//    @FXML
//    protected void onReloadButtonClick() {
//        log.info("==== onReloadButtonClick ==== 点击【重新加载】按钮！");
//
//    }
}
