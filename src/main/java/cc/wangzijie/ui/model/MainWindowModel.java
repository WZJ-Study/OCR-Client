package cc.wangzijie.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainWindowModel {

    private final ObjectProperty<Image> mainWindowLogoImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> closeWindowButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> maximizeWindowButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> minimizeWindowButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> openSettingsWindowButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> reloadMainWindowButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> openFullMenuButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> historyDataMenuButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> screenshotMenuButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> withdrawMenuButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> startCollectMenuButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> stopCollectMenuButtonImage = new SimpleObjectProperty<>();
    private final BooleanProperty collectRunningFlag = new SimpleBooleanProperty(false);


    public Image getMainWindowLogoImage() {
        return mainWindowLogoImage.get();
    }

    public ObjectProperty<Image> mainWindowLogoImageProperty() {
        return mainWindowLogoImage;
    }

    public void setMainWindowLogoImage(Image mainWindowLogoImage) {
        this.mainWindowLogoImage.set(mainWindowLogoImage);
    }


    public Image getCloseWindowButtonImage() {
        return closeWindowButtonImage.get();
    }

    public ObjectProperty<Image> closeWindowButtonImageProperty() {
        return closeWindowButtonImage;
    }

    public void setCloseWindowButtonImage(Image closeWindowButtonImage) {
        this.closeWindowButtonImage.set(closeWindowButtonImage);
    }

    public Image getMaximizeWindowButtonImage() {
        return maximizeWindowButtonImage.get();
    }

    public ObjectProperty<Image> maximizeWindowButtonImageProperty() {
        return maximizeWindowButtonImage;
    }

    public void setMaximizeWindowButtonImage(Image maximizeWindowButtonImage) {
        this.maximizeWindowButtonImage.set(maximizeWindowButtonImage);
    }

    public Image getMinimizeWindowButtonImage() {
        return minimizeWindowButtonImage.get();
    }

    public ObjectProperty<Image> minimizeWindowButtonImageProperty() {
        return minimizeWindowButtonImage;
    }

    public void setMinimizeWindowButtonImage(Image minimizeWindowButtonImage) {
        this.minimizeWindowButtonImage.set(minimizeWindowButtonImage);
    }

    public Image getOpenSettingsWindowButtonImage() {
        return openSettingsWindowButtonImage.get();
    }

    public ObjectProperty<Image> openSettingsWindowButtonImageProperty() {
        return openSettingsWindowButtonImage;
    }

    public void setOpenSettingsWindowButtonImage(Image openSettingsWindowButtonImage) {
        this.openSettingsWindowButtonImage.set(openSettingsWindowButtonImage);
    }

    public Image getReloadMainWindowButtonImage() {
        return reloadMainWindowButtonImage.get();
    }

    public ObjectProperty<Image> reloadMainWindowButtonImageProperty() {
        return reloadMainWindowButtonImage;
    }

    public void setReloadMainWindowButtonImage(Image reloadMainWindowButtonImage) {
        this.reloadMainWindowButtonImage.set(reloadMainWindowButtonImage);
    }

    public Image getOpenFullMenuButtonImage() {
        return openFullMenuButtonImage.get();
    }

    public ObjectProperty<Image> openFullMenuButtonImageProperty() {
        return openFullMenuButtonImage;
    }

    public void setOpenFullMenuButtonImage(Image openFullMenuButtonImage) {
        this.openFullMenuButtonImage.set(openFullMenuButtonImage);
    }

    public Image getHistoryDataMenuButtonImage() {
        return historyDataMenuButtonImage.get();
    }

    public ObjectProperty<Image> historyDataMenuButtonImageProperty() {
        return historyDataMenuButtonImage;
    }

    public void setHistoryDataMenuButtonImage(Image historyDataMenuButtonImage) {
        this.historyDataMenuButtonImage.set(historyDataMenuButtonImage);
    }

    public Image getScreenshotMenuButtonImage() {
        return screenshotMenuButtonImage.get();
    }

    public ObjectProperty<Image> screenshotMenuButtonImageProperty() {
        return screenshotMenuButtonImage;
    }

    public void setScreenshotMenuButtonImage(Image screenshotMenuButtonImage) {
        this.screenshotMenuButtonImage.set(screenshotMenuButtonImage);
    }

    public Image getWithdrawMenuButtonImage() {
        return withdrawMenuButtonImage.get();
    }

    public ObjectProperty<Image> withdrawMenuButtonImageProperty() {
        return withdrawMenuButtonImage;
    }

    public void setWithdrawMenuButtonImage(Image withdrawMenuButtonImage) {
        this.withdrawMenuButtonImage.set(withdrawMenuButtonImage);
    }

    public Image getStartCollectMenuButtonImage() {
        return startCollectMenuButtonImage.get();
    }

    public ObjectProperty<Image> startCollectMenuButtonImageProperty() {
        return startCollectMenuButtonImage;
    }

    public void setStartCollectMenuButtonImage(Image startCollectMenuButtonImage) {
        this.startCollectMenuButtonImage.set(startCollectMenuButtonImage);
    }

    public Image getStopCollectMenuButtonImage() {
        return stopCollectMenuButtonImage.get();
    }

    public ObjectProperty<Image> stopCollectMenuButtonImageProperty() {
        return stopCollectMenuButtonImage;
    }

    public void setStopCollectMenuButtonImage(Image stopCollectMenuButtonImage) {
        this.stopCollectMenuButtonImage.set(stopCollectMenuButtonImage);
    }


    public boolean isCollectRunningFlag() {
        return collectRunningFlag.get();
    }

    public BooleanProperty collectRunningFlagProperty() {
        return collectRunningFlag;
    }

    public void setCollectRunningFlag(boolean collectRunningFlag) {
        this.collectRunningFlag.set(collectRunningFlag);
    }


}
