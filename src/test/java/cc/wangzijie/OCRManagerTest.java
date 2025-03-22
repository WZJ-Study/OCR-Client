package cc.wangzijie;

import cc.wangzijie.ocr.OCRManager;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OCRManagerTest {

    public static void main(String[] args) throws InterruptedException {
        OCRManager ocrManager = new OCRManager(null,null, null,null,null, null);
        ocrManager.setIntervalSeconds(5);
        // ocrManager.addOcrSection(new OcrSection());
        log.info("==== 主线程 ==== 1. 启动OCR Manager");
        ocrManager.start();
        log.info("==== 主线程 ==== 1. 休眠 60 秒");
        TimeUnit.SECONDS.sleep(60);
        log.info("==== 主线程 ==== 2. 停止OCR Manager");
        ocrManager.stop();
        log.info("==== 主线程 ==== 2. 休眠 30 秒");
        TimeUnit.SECONDS.sleep(30);
        log.info("==== 主线程 ==== 3. 启动OCR Manager");
        ocrManager.start();
        log.info("==== 主线程 ==== 3. 休眠 60 秒");
        TimeUnit.SECONDS.sleep(60);
        log.info("==== 主线程 ==== 4. 停止OCR Manager");
        ocrManager.stop();
        log.info("==== 主线程 ==== 4. 结束");
    }

}
