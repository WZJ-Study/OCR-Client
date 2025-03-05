package cc.wangzijie.ocr.snapshot;


import cc.wangzijie.ocr.OCRManager;
import cc.wangzijie.ocr.component.TaskExecutor;
import cc.wangzijie.ocr.task.OcrProcessTask;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.function.Supplier;

@Slf4j
public class SnapshotTask implements Runnable {

    /**
     * OCR任务调度中心
     */
    private final OCRManager ocrManager;

    /**
     * 截屏工具
     */
    private final SnapshotCamera snapshotCamera;

    private final Supplier<Rectangle> snapshotRectSupplier;

    public SnapshotTask(OCRManager ocrManager, SnapshotCamera snapshotCamera, Supplier<Rectangle> snapshotRectSupplier) {
        this.ocrManager = ocrManager;
        this.snapshotCamera = snapshotCamera;
        this.snapshotRectSupplier = snapshotRectSupplier;
    }

    @Override
    public void run() {
        Rectangle rect = this.snapshotRectSupplier.get();
        File file = this.snapshotCamera.snapshot(rect);
        if (null != file && file.exists()) {
            OcrProcessTask task = this.ocrManager.createTask(file);
            log.info("==== 截屏定时任务 ==== 截屏成功，图片文件【{}】已创建处理任务！", file.getName());
            TaskExecutor.execute(task);
        } else {
            log.error("==== 截屏定时任务 ==== 截屏失败，截屏图片文件不存在！");
        }
    }

}
