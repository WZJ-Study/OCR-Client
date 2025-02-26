package cc.wangzijie.ui.view;


import cc.wangzijie.constants.Constants;
import cc.wangzijie.fxml.FxmlViews;
import cc.wangzijie.fxml.loader.SpringFxmlLoader;
import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.MainWindowModel;
import cc.wangzijie.ui.model.MousePositionModel;
import cc.wangzijie.ui.utils.ImageLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Objects;
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

    @Resource
    private MousePositionModel mousePositionModel;

    @FXML
    private ImageView mainWindowLogoImage;

    @FXML
    private Label mousePosition;

    @FXML
    private Label welcomeText;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定FXML组件与model属性
        mainWindowLogoImage.imageProperty().bindBidirectional(mainWindowModel.mainWindowLogoImageProperty());

        closeWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.closeWindowButtonImageProperty());
        maximizeWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.maximizeWindowButtonImageProperty());
        minimizeWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.minimizeWindowButtonImageProperty());
        openSettingsWindowButtonImage.imageProperty().bindBidirectional(mainWindowModel.openSettingsWindowButtonImageProperty());
        reloadMainWindowButtonImage.imageProperty().bind(mainWindowModel.reloadMainWindowButtonImageProperty());

        mousePosition.textProperty().bindBidirectional(mainWindowModel.mousePositionProperty());
        welcomeText.textProperty().bind(mainWindowModel.welcomeTextProperty());


        // 处理model属性
        mainWindowModel.setMainWindowLogoImage(ImageLoader.load(Constants.LOGO_IMAGE_PATH));

        mainWindowModel.setCloseWindowButtonImage(ImageLoader.load(Constants.CLOSE_IMAGE_PATH));
        mainWindowModel.setMaximizeWindowButtonImage(ImageLoader.load(Constants.MAXIMIZE_IMAGE_PATH));
        mainWindowModel.setMinimizeWindowButtonImage(ImageLoader.load(Constants.MINIMIZE_IMAGE_PATH));
        mainWindowModel.setOpenSettingsWindowButtonImage(ImageLoader.load(Constants.SETTINGS_IMAGE_PATH));
        mainWindowModel.setReloadMainWindowButtonImage(ImageLoader.load(Constants.RELOAD_IMAGE_PATH));

        mainWindowModel.mousePositionProperty().bind(mousePositionModel.displayTextProperty());
    }

    @FXML
    protected void onHelloButtonClick() {
        mainWindowModel.setWelcomeText("Welcome to JavaFX Application!" + System.currentTimeMillis());
        Stage stage = stageManager.getMainWindowStage();
        if (null != stage && null != stage.getScene()) {
            Platform.runLater(() -> stage.getScene().setOnMouseClicked(mousePositionModel::refresh));
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
        log.info("==== onMaximizeWindowButtonClick ==== 点击【最大化窗口】按钮！");
        Stage stage = stageManager.getMainWindowStage();
        if (null != stage && !stage.isFullScreen()) {
            Platform.runLater(() -> stage.setFullScreen(true));
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
}
