package cc.wangzijie.ui.view;


import cc.wangzijie.constants.Constants;
import cc.wangzijie.fxml.loader.SpringFxmlLoader;
import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.SettingsWindowModel;
import cc.wangzijie.ui.utils.ImageLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class SettingsWindowView implements Initializable {

    @Resource
    private StageManager stageManager;

    @Resource
    private SettingsWindowModel settingsWindowModel;


    @FXML
    private ImageView closeWindowButtonImage;
    @FXML
    public ImageView applySettingButtonImage;


    @FXML
    public TextField intervalSecondsInput;


    private double offsetX;
    private double offsetY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定FXML组件与model属性 - 标题栏右侧窗口按钮
        closeWindowButtonImage.imageProperty().bindBidirectional(settingsWindowModel.closeWindowButtonImageProperty());
        // 绑定FXML组件与model属性 - 窗口底部应用设置按钮
        applySettingButtonImage.imageProperty().bindBidirectional(settingsWindowModel.applySettingButtonImageProperty());

        // 设置#1.定时采集间隔（秒）
        intervalSecondsInput.setText(String.valueOf(Constants.DEFAULT_INTERVAL_SECONDS));

        // 处理model属性 - 标题栏右侧窗口按钮
        settingsWindowModel.setCloseWindowButtonImage(ImageLoader.load(Constants.CLOSE_IMAGE_PATH));
        // 处理model属性 - 窗口底部应用设置按钮
        settingsWindowModel.setApplySettingButtonImage(ImageLoader.load(Constants.APPLY_IMAGE_PATH));
    }


    @FXML
    protected void onSettingsWindowMousePressed(MouseEvent event) {
        this.offsetX = event.getSceneX();
        this.offsetY = event.getSceneY();
    }

    @FXML
    protected void onSettingsWindowMouseDragged(MouseEvent event) {
        Stage stage = stageManager.getSettingsWindowStage();
        if (null != stage) {
            Platform.runLater(() -> {
                stage.setX(event.getScreenX() - offsetX);
                stage.setY(event.getScreenY() - offsetY);
            });
        }
    }

    @FXML
    protected void onCloseWindowButtonClick() {
        Stage stage = stageManager.getSettingsWindowStage();
        if (null != stage) {
            Platform.runLater(stage::hide);
        }
    }


    @FXML
    protected void onApplySettings() {
        // 设置#1.定时采集间隔（秒）
        int seconds = this.processIntervalSecondsInput(intervalSecondsInput.getText());
        intervalSecondsInput.setText(String.valueOf(seconds));
        settingsWindowModel.setIntervalSeconds(seconds);
    }


    /**
     * 限制只能输入数字，并限制输入数字的范围：1~120
     *
     * @param input 输入文本
     * @return 处理后的输入文本
     */
    private int processIntervalSecondsInput(String input) {
        log.info("==== 处理输入值 ==== 原始输入：{}", input);
        if (StringUtils.isBlank(input)) {
            return Constants.DEFAULT_INTERVAL_SECONDS;
        }
        if (!input.matches("\\d*")) {
            input = input.replaceAll("[^\\d]","");
        }
        log.info("==== 处理输入值 ==== 去除非数字文本之后：{}", input);
        if (StringUtils.isBlank(input)) {
            return Constants.DEFAULT_INTERVAL_SECONDS;
        }
        int value = Integer.parseInt(input);
        log.info("==== 处理输入值 ==== 转为数字之后：{}", value);
        if (value < Constants.MIN_INTERVAL_SECONDS) {
            value = Constants.MIN_INTERVAL_SECONDS;
        } else if (value > Constants.MAX_INTERVAL_SECONDS) {
            value = Constants.MAX_INTERVAL_SECONDS;
        }
        log.info("==== 处理输入值 ==== 限制数字范围之后：{}", value);
        return value;
    }

}
