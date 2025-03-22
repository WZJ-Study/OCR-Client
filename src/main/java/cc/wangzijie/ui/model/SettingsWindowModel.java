package cc.wangzijie.ui.model;

import cc.wangzijie.constants.Constants;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

@Component
public class SettingsWindowModel {


    private final ObjectProperty<Image> closeWindowButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> applySettingButtonImage = new SimpleObjectProperty<>();

    private final IntegerProperty intervalSeconds = new SimpleIntegerProperty();

    private final StringProperty outputFolderPath = new SimpleStringProperty();
    private final BooleanProperty outputFolderEnabledFlag = new SimpleBooleanProperty(true);

    private final StringProperty callbackHookUrl = new SimpleStringProperty();
    private final BooleanProperty callbackHookEnabledFlag = new SimpleBooleanProperty(true);

    public Image getCloseWindowButtonImage() {
        return closeWindowButtonImage.get();
    }

    public ObjectProperty<Image> closeWindowButtonImageProperty() {
        return closeWindowButtonImage;
    }

    public void setCloseWindowButtonImage(Image closeWindowButtonImage) {
        this.closeWindowButtonImage.set(closeWindowButtonImage);
    }

    public Image getApplySettingButtonImage() {
        return applySettingButtonImage.get();
    }

    public ObjectProperty<Image> applySettingButtonImageProperty() {
        return applySettingButtonImage;
    }

    public void setApplySettingButtonImage(Image applySettingButtonImage) {
        this.applySettingButtonImage.set(applySettingButtonImage);
    }

    public int getIntervalSeconds() {
        return intervalSeconds.get();
    }

    public IntegerProperty intervalSecondsProperty() {
        return intervalSeconds;
    }

    public void setIntervalSeconds(int intervalSeconds) {
        this.intervalSeconds.set(intervalSeconds);
    }

    public String getOutputFolderPath() {
        return outputFolderPath.get();
    }

    public StringProperty outputFolderPathProperty() {
        return outputFolderPath;
    }

    public void setOutputFolderPath(String outputFolderPath) {
        this.outputFolderPath.set(outputFolderPath);
    }

    public boolean isOutputFolderEnabledFlag() {
        return outputFolderEnabledFlag.get();
    }

    public BooleanProperty outputFolderEnabledFlagProperty() {
        return outputFolderEnabledFlag;
    }

    public void setOutputFolderEnabledFlag(boolean outputFolderEnabledFlag) {
        this.outputFolderEnabledFlag.set(outputFolderEnabledFlag);
    }

    public String getCallbackHookUrl() {
        return callbackHookUrl.get();
    }

    public StringProperty callbackHookUrlProperty() {
        return callbackHookUrl;
    }

    public void setCallbackHookUrl(String callbackHookUrl) {
        this.callbackHookUrl.set(callbackHookUrl);
    }

    public boolean isCallbackHookEnabledFlag() {
        return callbackHookEnabledFlag.get();
    }

    public BooleanProperty callbackHookEnabledFlagProperty() {
        return callbackHookEnabledFlag;
    }

    public void setCallbackHookEnabledFlag(boolean callbackHookEnabledFlag) {
        this.callbackHookEnabledFlag.set(callbackHookEnabledFlag);
    }
}
