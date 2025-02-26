package cc.wangzijie.ui.view;


import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.MainWindowModel;
import cc.wangzijie.ui.model.MousePositionModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainWindowView implements Initializable {

    @Resource
    private StageManager stageManager;

    @Resource
    private MainWindowModel mainWindowModel;

    @Resource
    private MousePositionModel mousePositionModel;

    @FXML
    private Label mousePosition;

    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mousePosition.textProperty().bind(mousePositionModel.displayTextProperty());

        mainWindowModel.mousePositionProperty().bind(mousePosition.textProperty());
        mainWindowModel.welcomeTextProperty().bind(welcomeText.textProperty());
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!" + System.currentTimeMillis());
        Stage stage = stageManager.getMainWindowStage();
        if (null != stage) {
            stage.getScene().setOnMouseClicked(mousePositionModel::refresh);
        }
    }

}
