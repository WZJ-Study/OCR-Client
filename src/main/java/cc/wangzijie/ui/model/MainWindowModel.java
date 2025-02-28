package cc.wangzijie.ui.model;

import cc.wangzijie.ui.utils.AwtRobotUtils;
import javafx.beans.property.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    private final ObjectProperty<Rectangle> screenshotArea = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> screenshotImage = new SimpleObjectProperty<>();


    private final ObjectProperty<Image> dataListTitleBarMenuButtonImage = new SimpleObjectProperty<>();
    private final StringProperty dataListTitleBarMenuTitle = new SimpleStringProperty();
    private final ObjectProperty<Image> dataListTitleBarSearchButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> dataListTitleBarDeleteButtonImage = new SimpleObjectProperty<>();


    private final BooleanProperty collectRunningFlag = new SimpleBooleanProperty(false);
    private final BooleanProperty screenshotAreaHasImageFlag = new SimpleBooleanProperty(false);

    @Getter
    private BufferedImage screenshot;

    public void doScreenshot() {
        Rectangle rect = this.screenshotArea.get();
        if (null == rect) {
            log.info("==== 执行截屏 === 未选定截屏区域！默认截屏整个屏幕！");
            try {
                // 捕获屏幕区域‌
                this.screenshot = new Robot().createScreenCapture(new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                // 转换为JavaFX Image并显示预览‌
                this.setScreenshotImage(SwingFXUtils.toFXImage(this.screenshot, null));
                this.setScreenshotAreaHasImageFlag(true);
            } catch (Exception ex) {
                log.error("==== 执行截屏 === 全屏截图失败！", ex);
            }
        } else {
            log.info("==== 执行截屏 === 截屏区域 rect={}", rect);
            try {
                // 捕获屏幕区域‌
                this.screenshot = AwtRobotUtils.createScreenCapture(rect);

                // 转换为JavaFX Image并显示预览‌
                this.setScreenshotImage(SwingFXUtils.toFXImage(this.screenshot, null));
                this.setScreenshotAreaHasImageFlag(true);
            } catch (Exception ex) {
                log.error("==== 执行截屏 === 区域截图失败！", ex);
            }
        }
    }

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

    public Rectangle getScreenshotArea() {
        return screenshotArea.get();
    }

    public ObjectProperty<Rectangle> screenshotAreaProperty() {
        return screenshotArea;
    }

    public void setScreenshotArea(Rectangle screenshotArea) {
        this.screenshotArea.set(screenshotArea);
    }

    public Image getScreenshotImage() {
        return screenshotImage.get();
    }

    public ObjectProperty<Image> screenshotImageProperty() {
        return screenshotImage;
    }

    public void setScreenshotImage(Image screenshotImage) {
        this.screenshotImage.set(screenshotImage);
    }

    public Image getDataListTitleBarMenuButtonImage() {
        return dataListTitleBarMenuButtonImage.get();
    }

    public ObjectProperty<Image> dataListTitleBarMenuButtonImageProperty() {
        return dataListTitleBarMenuButtonImage;
    }

    public void setDataListTitleBarMenuButtonImage(Image dataListTitleBarMenuButtonImage) {
        this.dataListTitleBarMenuButtonImage.set(dataListTitleBarMenuButtonImage);
    }

    public String getDataListTitleBarMenuTitle() {
        return dataListTitleBarMenuTitle.get();
    }

    public StringProperty dataListTitleBarMenuTitleProperty() {
        return dataListTitleBarMenuTitle;
    }

    public void setDataListTitleBarMenuTitle(String dataListTitleBarMenuTitle) {
        this.dataListTitleBarMenuTitle.set(dataListTitleBarMenuTitle);
    }

    public Image getDataListTitleBarSearchButtonImage() {
        return dataListTitleBarSearchButtonImage.get();
    }

    public ObjectProperty<Image> dataListTitleBarSearchButtonImageProperty() {
        return dataListTitleBarSearchButtonImage;
    }

    public void setDataListTitleBarSearchButtonImage(Image dataListTitleBarSearchButtonImage) {
        this.dataListTitleBarSearchButtonImage.set(dataListTitleBarSearchButtonImage);
    }

    public Image getDataListTitleBarDeleteButtonImage() {
        return dataListTitleBarDeleteButtonImage.get();
    }

    public ObjectProperty<Image> dataListTitleBarDeleteButtonImageProperty() {
        return dataListTitleBarDeleteButtonImage;
    }

    public void setDataListTitleBarDeleteButtonImage(Image dataListTitleBarDeleteButtonImage) {
        this.dataListTitleBarDeleteButtonImage.set(dataListTitleBarDeleteButtonImage);
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

    public boolean isScreenshotAreaHasImageFlag() {
        return screenshotAreaHasImageFlag.get();
    }

    public BooleanProperty screenshotAreaHasImageFlagProperty() {
        return screenshotAreaHasImageFlag;
    }

    public void setScreenshotAreaHasImageFlag(boolean screenshotAreaHasImageFlag) {
        this.screenshotAreaHasImageFlag.set(screenshotAreaHasImageFlag);
    }



}
