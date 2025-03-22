package cc.wangzijie.ocr.task;

import cc.wangzijie.constants.Constants;
import cc.wangzijie.ocr.OCRManager;
import cc.wangzijie.ocr.component.TaskExecutor;
import cc.wangzijie.ui.model.SettingsWindowModel;
import cc.wangzijie.utils.*;
import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import cc.wangzijie.ui.model.DataListAreaModel;
import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.ocr.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class OcrProcessTask implements Runnable {

    /**
     * OCR任务调度中心
     */
    private final OCRManager ocrManager;

    /**
     * RapidOCR识别引擎
     */
    private final InferenceEngine ocrEngine;

    /**
     * 待OCR识别的截图图片
     */
    private final BufferedImage snapshotImage;

    /**
     * OCR识别框选区域
     */
    private final Map<String, OcrSection> ocrRectMap;

    public OcrProcessTask(OCRManager ocrManager, InferenceEngine ocrEngine, BufferedImage snapshotImage, Map<String, OcrSection> ocrRectMap) {
        this.ocrManager = ocrManager;
        this.snapshotImage = snapshotImage;
        this.ocrRectMap = ocrRectMap;
        this.ocrEngine = ocrEngine;
    }

    @Override
    public void run() {
        String collectTime = DateUtils.nowStr();
        String subFolder = String.format("OcrProcessTask-%s", collectTime);
        log.info("==== OcrProcessTask ==== 开始OCR识别！当前时间：{}\n", collectTime);

        // OCR识别各框选区域
        List<OcrSectionResult> resultList = new LinkedList<>();
        for (String key : this.ocrRectMap.keySet()) {
            try {
                OcrSection ocrSection = this.ocrRectMap.get(key);

                // 创建截取区域的新图片
                BufferedImage rectImage = this.snapshotImage.getSubimage(ocrSection.getX(), ocrSection.getY(), ocrSection.getWidth(), ocrSection.getHeight());

                // 保存截取区域的图片
                String subFileName = "SubFile_" + ocrSection.getId();
                File subFile =  File.createTempFile(subFileName, Constants.IMAGE_FORMAT_WITH_DOT);
                ImageIO.write(rectImage, Constants.IMAGE_FORMAT, subFile);

                // 执行OCR识别
                OcrResult ocrResult = this.ocrEngine.runOcr(subFile.getPath());
                log.info("==== OCR识别处理 ==== 截屏图片文件OCR识别成功，区域：{} ==> 识别结果：\n\n{}", key, ocrResult.getStrRes());
                OcrSectionResult result = ocrSection.newResult(ocrResult, collectTime);
                resultList.add(result);

                // OCR识别结果更新到UI视图模型中
                this.ocrManager.newResult(ocrSection.displayPosition(), result);
            } catch (Exception e) {
                log.error("==== OCR识别处理 ==== 截屏图片文件OCR识别失败，捕获到异常！", e);
            }
        }

        // OCR识别结果保存到数据库
        TaskExecutor.execute(this.ocrManager.createDatabaseOutputTask(resultList));

        // OCR识别结果保存到文件
        TaskExecutor.execute(this.ocrManager.createFileOutputTask(this.snapshotImage, resultList));

        // OCR识别结果触发回调钩子
        TaskExecutor.execute(this.ocrManager.createCallbackHookTask(resultList));
    }

}
