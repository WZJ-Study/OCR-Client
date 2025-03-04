package cc.wangzijie.ui.model;

import cc.wangzijie.ocr.OCRManager;
import cc.wangzijie.ocr.config.SnapshotCameraConfig;
import cc.wangzijie.server.service.IOcrSectionResultService;
import cc.wangzijie.ui.utils.AwtRobotUtils;
import javafx.beans.property.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ScreenshotAreaModel {

    private final ObjectProperty<Rectangle> screenshotArea = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> screenshotImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Cursor> screenshotImageCursor = new SimpleObjectProperty<>();
    private final StringProperty screenshotImageHint = new SimpleStringProperty();
    private final BooleanProperty screenshotImageHintVisible = new SimpleBooleanProperty(true);
    private final BooleanProperty screenshotAreaHasImageFlag = new SimpleBooleanProperty(false);

    @Resource
    private IOcrSectionResultService ocrSectionResultService;


    @Resource
    private SnapshotCameraConfig snapshotCameraConfig;

    @Getter
    private final OCRManager ocrManager = new OCRManager(this, ocrSectionResultService,  snapshotCameraConfig);

    @Getter
    private final List<Rectangle> rectList = new ArrayList<>();

    @Getter
    @Setter
    private Integer imageHeight;

    @Getter
    @Setter
    private Integer imageWidth;

    @Getter
    @Setter
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
                this.setScreenshotImageCursor(Cursor.CROSSHAIR);
                this.setScreenshotImageHintVisible(false);
                this.setScreenshotAreaHasImageFlag(true);
                this.setImageHeight(this.screenshot.getHeight());
                this.setImageWidth(this.screenshot.getWidth());
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
                this.setScreenshotImageCursor(Cursor.CROSSHAIR);
                this.setScreenshotImageHintVisible(false);
                this.setScreenshotAreaHasImageFlag(true);
                this.setImageHeight(this.screenshot.getHeight());
                this.setImageWidth(this.screenshot.getWidth());
            } catch (Exception ex) {
                log.error("==== 执行截屏 === 区域截图失败！", ex);
            }
        }
    }

    public void clearRectList() {
        rectList.clear();
    }

    public void addRect(Rectangle rectangle) {
        rectList.add(rectangle);
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

    public Cursor getScreenshotImageCursor() {
        return screenshotImageCursor.get();
    }

    public ObjectProperty<Cursor> screenshotImageCursorProperty() {
        return screenshotImageCursor;
    }

    public void setScreenshotImageCursor(Cursor screenshotImageCursor) {
        this.screenshotImageCursor.set(screenshotImageCursor);
    }

    public String getScreenshotImageHint() {
        return screenshotImageHint.get();
    }

    public StringProperty screenshotImageHintProperty() {
        return screenshotImageHint;
    }

    public void setScreenshotImageHint(String screenshotImageHint) {
        this.screenshotImageHint.set(screenshotImageHint);
    }

    public boolean isScreenshotImageHintVisible() {
        return screenshotImageHintVisible.get();
    }

    public BooleanProperty screenshotImageHintVisibleProperty() {
        return screenshotImageHintVisible;
    }

    public void setScreenshotImageHintVisible(boolean screenshotImageHintVisible) {
        this.screenshotImageHintVisible.set(screenshotImageHintVisible);
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
