package cc.wangzijie.ocr.task;

import cc.wangzijie.ocr.OCRManager;
import cc.wangzijie.ocr.component.TaskExecutor;
import cc.wangzijie.ui.model.ScreenshotAreaModel;
import cc.wangzijie.ui.utils.AwtRobotUtils;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;

@Slf4j
public class SnapshotTask implements Runnable {

    /**
     * 截屏区域模型
     */
    private final ScreenshotAreaModel screenshotAreaModel;

    /**
     * OCR任务调度中心
     */
    private final OCRManager ocrManager;

    private final Rectangle snapshotRect;

    public SnapshotTask(ScreenshotAreaModel screenshotAreaModel, OCRManager ocrManager, Rectangle snapshotRect) {
        this.screenshotAreaModel = screenshotAreaModel;
        this.ocrManager = ocrManager;
        this.snapshotRect = snapshotRect;
    }

    @Override
    public void run() {
        // 截屏
        BufferedImage screenshot = AwtRobotUtils.createScreenCapture(this.snapshotRect);
        // 显示预览‌
        this.screenshotAreaModel.setScreenshot(screenshot);
        // 启动处理任务
        TaskExecutor.execute(this.ocrManager.createTask(screenshot));
    }

}
