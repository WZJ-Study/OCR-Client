package cc.wangzijie.ocr;


import cc.wangzijie.ocr.component.TaskExecutor;
import cc.wangzijie.ocr.config.SnapshotCameraConfig;
import cc.wangzijie.ocr.snapshot.SnapshotCamera;
import cc.wangzijie.ocr.snapshot.SnapshotTask;
import cc.wangzijie.ocr.task.OcrProcessTask;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * OCR任务调度中心
 */
@Slf4j
public class OCRManager {

    /**
     * 截屏工具
     */
    private final SnapshotCamera snapshotCamera;

    /**
     * RapidOCR识别引擎
     */
    private final InferenceEngine ocrEngine;


    /**
     * OCR识别框选区域
     */
    private final Map<String, Rectangle> ocrRectMap;

    /**
     * 定时截屏采集任务线程Future
     */
    private ScheduledFuture<?> scheduledFuture;

    /**
     * 定时截屏采集任务时间间隔
     */
    private Integer intervalSeconds;

    private volatile boolean running;

    public OCRManager() throws AWTException {
        this.ocrEngine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
        this.snapshotCamera = new SnapshotCamera(null);
        // 默认时间间隔：10s
        this.intervalSeconds = 10;
        this.ocrRectMap = new ConcurrentHashMap<>();
        // 设置运行标志=已停止
        this.running = false;
    }

    public OCRManager(SnapshotCameraConfig cameraConfig) throws AWTException {
        this.ocrEngine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
        this.snapshotCamera = new SnapshotCamera(cameraConfig);
        // 默认时间间隔：10s
        this.intervalSeconds = 10;
        this.ocrRectMap = new ConcurrentHashMap<>();
        // 设置运行标志=已停止
        this.running = false;
    }

    /**
     * 开始运行
     */
    public synchronized void start() {
        if (this.running) {
            return;
        }
//        if (this.ocrRectMap.isEmpty()) {
//            log.error("请至少添加一个识别区域！");
//            return;
//        }
        // 开始定时截屏采集
        this.scheduledFuture = TaskExecutor.scheduleWithFixedDelay(new SnapshotTask(this, this.snapshotCamera), intervalSeconds);
        // 设置运行标志=运行中
        this.running = true;
    }

    /**
     * 结束运行
     */
    public synchronized void stop() {
        // 停止截屏定时任务
        this.scheduledFuture.cancel(true);
        // 设置运行标志=已停止
        this.running = false;
    }

    public void setIntervalSeconds(int intervalSeconds) {
        if (this.running) {
            log.error("正在运行中，不可修改采集时间间隔！请先停止运行！");
            return;
        }
        this.intervalSeconds = intervalSeconds;
    }

    public synchronized boolean addOcrRect(String key, Rectangle rect) {
//        if (this.running) {
//            log.error("正在运行中，不可修改识别区域！请先停止运行！");
//            return false;
//        }
        Rectangle oldRect = this.ocrRectMap.put(key, rect);
        if (oldRect != null) {
            log.info("更新识别区域：key={} \noldRect={} \nnewRect={}", key, oldRect, rect);
        }
        return true;
    }

    public synchronized boolean removeOcrRect(String key) {
//        if (this.running) {
//            log.error("正在运行中，不可修改识别区域！请先停止运行！");
//            return false;
//        }
        Rectangle oldRect = this.ocrRectMap.remove(key);
        if (oldRect != null) {
            log.info("删除识别区域：key={} \noldRect={}", key, oldRect);
        }
        return true;
    }

    public OcrProcessTask createTask(File file) {
        return new OcrProcessTask(this.ocrEngine, file, this.ocrRectMap);
    }

}
