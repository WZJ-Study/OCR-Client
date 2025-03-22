package cc.wangzijie.ui.view;


import cc.wangzijie.config.ConfigManager;
import cc.wangzijie.config.ServerConfig;
import cc.wangzijie.constants.ConfigKeys;
import cc.wangzijie.constants.Constants;
import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.SettingsWindowModel;
import cc.wangzijie.ui.utils.ImageLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Slf4j
@Component
public class SettingsWindowView implements Initializable {

    @Resource
    private ServerConfig serverConfig;

    @Resource
    private ConfigManager configManager;

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

    @FXML
    public TextField outputFolderPathInput;
    @FXML
    public CheckBox outputFolderEnabledInput;

    @FXML
    public TextField callbackHookUrlInput;
    @FXML
    public CheckBox callbackHookEnabledInput;

    @FXML
    public CheckBox databaseEnabledInput;

    private double offsetX;
    private double offsetY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定FXML组件与model属性 - 标题栏右侧窗口按钮
        closeWindowButtonImage.imageProperty().bindBidirectional(settingsWindowModel.closeWindowButtonImageProperty());
        // 绑定FXML组件与model属性 - 窗口底部应用设置按钮
        applySettingButtonImage.imageProperty().bindBidirectional(settingsWindowModel.applySettingButtonImageProperty());

        boolean savePropertiesFlag = false;

        // 设置#1.定时采集间隔（秒）
        String intervalSeconds = configManager.getProperty(ConfigKeys.KEY_INTERVAL_SECONDS);
        if (StringUtils.isBlank(intervalSeconds)) {
            intervalSeconds = String.valueOf(Constants.DEFAULT_INTERVAL_SECONDS);
            configManager.setProperty(ConfigKeys.KEY_INTERVAL_SECONDS, intervalSeconds);
            savePropertiesFlag = true;
        }
        intervalSecondsInput.setText(intervalSeconds);

        // 设置#2.输出文件夹路径
        String outputFolderPath = configManager.getProperty(ConfigKeys.KEY_OUTPUT_FOLDER_PATH);
        if (StringUtils.isBlank(outputFolderPath)) {
            outputFolderPath = Constants.DEFAULT_OUTPUT_FOLDER_PATH;
            configManager.setProperty(ConfigKeys.KEY_OUTPUT_FOLDER_PATH, outputFolderPath);
            savePropertiesFlag = true;
        }
        outputFolderPathInput.setText(outputFolderPath);

        // 设置#2.输出文件夹路径 - 是否启用
        String outputFolderEnabledFlag = configManager.getProperty(ConfigKeys.KEY_OUTPUT_FOLDER_ENABLED_FLAG);
        if (StringUtils.isBlank(outputFolderEnabledFlag)) {
            outputFolderEnabledFlag = Constants.TRUE;
            configManager.setProperty(ConfigKeys.KEY_OUTPUT_FOLDER_ENABLED_FLAG, outputFolderEnabledFlag);
            savePropertiesFlag = true;
        }
        outputFolderEnabledInput.setSelected(Objects.equals(outputFolderEnabledFlag, Constants.TRUE));

        // 设置#3.Hook回调URL
        String callbackHookUrl = configManager.getProperty(ConfigKeys.KEY_CALLBACK_HOOK_URL);
        if (StringUtils.isBlank(callbackHookUrl)) {
            callbackHookUrl = serverConfig.buildUrl(Constants.DEFAULT_CALLBACK_HOOK_URI);
            configManager.setProperty(ConfigKeys.KEY_CALLBACK_HOOK_URL, callbackHookUrl);
            savePropertiesFlag = true;
        }
        callbackHookUrlInput.setText(callbackHookUrl);

        // 设置#3.Hook回调URL - 是否启用
        String callbackHookEnabledFlag = configManager.getProperty(ConfigKeys.KEY_CALLBACK_HOOK_ENABLED_FLAG);
        if (StringUtils.isBlank(callbackHookEnabledFlag)) {
            callbackHookEnabledFlag = Constants.TRUE;
            configManager.setProperty(ConfigKeys.KEY_CALLBACK_HOOK_ENABLED_FLAG, callbackHookEnabledFlag);
            savePropertiesFlag = true;
        }
        callbackHookEnabledInput.setSelected(Objects.equals(callbackHookEnabledFlag, Constants.TRUE));

        // 设置#4.输出到本地SQLite数据库 - 是否启用
        String databaseEnabledFlag = configManager.getProperty(ConfigKeys.KEY_DATABASE_ENABLED_FLAG);
        if (StringUtils.isBlank(databaseEnabledFlag)) {
            databaseEnabledFlag = Constants.TRUE;
            configManager.setProperty(ConfigKeys.KEY_DATABASE_ENABLED_FLAG, databaseEnabledFlag);
            savePropertiesFlag = true;
        }
        databaseEnabledInput.setSelected(Objects.equals(databaseEnabledFlag, Constants.TRUE));

        // 保存配置文件
        if (savePropertiesFlag) {
            configManager.saveProperties();
        }

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
        int intervalSeconds = this.processIntervalSecondsInput(intervalSecondsInput.getText());
        String intervalSecondsText = String.valueOf(intervalSeconds);
        intervalSecondsInput.setText(intervalSecondsText);
        settingsWindowModel.setIntervalSeconds(intervalSeconds);
        configManager.setProperty(ConfigKeys.KEY_INTERVAL_SECONDS, intervalSecondsText);

        // 设置#2.输出文件夹路径
        String outputFolderPath = this.processOutputFolderPathInput(outputFolderPathInput.getText());
        outputFolderPathInput.setText(outputFolderPath);
        settingsWindowModel.setOutputFolderPath(outputFolderPath);
        configManager.setProperty(ConfigKeys.KEY_OUTPUT_FOLDER_PATH, outputFolderPath);

        // 设置#2.输出文件夹路径 - 是否启用
        boolean outputFolderEnabledFlag = outputFolderEnabledInput.isSelected();
        settingsWindowModel.setOutputFolderEnabledFlag(outputFolderEnabledFlag);
        configManager.setProperty(ConfigKeys.KEY_OUTPUT_FOLDER_ENABLED_FLAG, outputFolderEnabledFlag ? Constants.TRUE : Constants.FALSE);

        // 设置#3.Hook回调URL
        String callbackHookUrl = this.processCallbackHookUrlInput(callbackHookUrlInput.getText());
        callbackHookUrlInput.setText(callbackHookUrl);
        settingsWindowModel.setCallbackHookUrl(callbackHookUrl);
        configManager.setProperty(ConfigKeys.KEY_CALLBACK_HOOK_URL, callbackHookUrl);

        // 设置#3.Hook回调URL - 是否启用
        boolean callbackHookEnabledFlag = callbackHookEnabledInput.isSelected();
        settingsWindowModel.setCallbackHookEnabledFlag(callbackHookEnabledFlag);
        configManager.setProperty(ConfigKeys.KEY_CALLBACK_HOOK_ENABLED_FLAG, callbackHookEnabledFlag ? Constants.TRUE : Constants.FALSE);

        // 设置#4.输出到本地SQLite数据库 - 是否启用
        boolean databaseEnabledFlag = databaseEnabledInput.isSelected();
        settingsWindowModel.setDatabaseEnabledFlag(databaseEnabledFlag);
        configManager.setProperty(ConfigKeys.KEY_DATABASE_ENABLED_FLAG, databaseEnabledFlag ? Constants.TRUE : Constants.FALSE);

        // 保存配置文件
        configManager.saveProperties();

        // 关闭窗口
        Stage stage = stageManager.getSettingsWindowStage();
        if (null != stage) {
            Platform.runLater(stage::hide);
        }
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

    private String processOutputFolderPathInput(String input) {
        log.info("==== 处理输入值 ==== 原始输入：{}", input);
        if (StringUtils.isNotBlank(input)) {
            File file = new File(input);
            if (file.exists()) {
                if (file.isDirectory()) {
                    return input;
                }
            } else {
                if (file.mkdirs()) {
                    return input;
                }
            }
        }
        return Constants.DEFAULT_OUTPUT_FOLDER_PATH;
    }

    private String processCallbackHookUrlInput(String input) {
        log.info("==== 处理输入值 ==== 原始输入：{}", input);
        if (StringUtils.isNotBlank(input)) {
            if (input.startsWith("http://") || input.startsWith("https://")) {
                try {
                    // This will throw an exception if the URL is malformed
                    new URL(input);
                    return input;
                } catch (MalformedURLException e) {
                    log.error("URL格式错误！>> " + input, e);
                }
            }
        }
        return serverConfig.buildUrl(Constants.DEFAULT_CALLBACK_HOOK_URI);
    }


}
