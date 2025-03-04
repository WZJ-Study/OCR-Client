package cc.wangzijie.ocr.task;


import cc.wangzijie.ocr.utils.JacksonUtils;
import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import cc.wangzijie.ui.vo.OcrSectionResultVO;
import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.ocr.InferenceEngine;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.commons.collections4.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class OcrProcessTask implements Runnable {

    /**
     * OCR识别结果数据库保存服务
     */
    private final IOcrSectionResultService ocrSectionResultService;

    /**
     * RapidOCR识别引擎
     */
    private final InferenceEngine ocrEngine;

    /**
     * 待OCR识别的截图文件
     */
    private final File snapshotFile;

    /**
     * OCR识别框选区域
     */
    private final Map<String, OcrSection> ocrRectMap;


    public OcrProcessTask(IOcrSectionResultService ocrSectionResultService, InferenceEngine ocrEngine, File snapshotFile, Map<String, OcrSection> ocrRectMap) {
        this.ocrSectionResultService = ocrSectionResultService;
        this.snapshotFile = snapshotFile;
        this.ocrRectMap = ocrRectMap;
        this.ocrEngine = ocrEngine;
    }

    @Override
    public void run() {
        // 读取截屏图片文件
        BufferedImage image = null;
        try {
            image = ImageIO.read(snapshotFile);
            int height = image.getHeight();
            int width = image.getWidth();
            log.info("==== OCR识别处理 ==== 处理截屏图片文件【{}】，图片尺寸 height = {} width = {}", snapshotFile.getName(), height, width);
        } catch (IOException e) {
            log.error("==== OCR识别处理 ==== 截屏图片文件打开失败，捕获到异常！", e);
        }

        // 处理该截屏图片
        if (null != image) {
            // OCR识别各框选区域
            Date collectTime = new Date();
            List<OcrSectionResult> resultList = new LinkedList<>();
            for (String key : this.ocrRectMap.keySet()) {
                try {
                    OcrSection ocrSection = this.ocrRectMap.get(key);

                    // 创建截取区域的新图片
                    BufferedImage rectImage = image.getSubimage(ocrSection.getX(), ocrSection.getY(), ocrSection.getWidth(), ocrSection.getHeight());
                    String filePath = snapshotFile.getAbsolutePath() + ".OCR区域." + key + ".png";

                    // 保存修改后的图片
                    ImageIO.write(rectImage, "png", new File(filePath));

                    // 执行OCR识别
                    OcrResult ocrResult = this.ocrEngine.runOcr(filePath);
                    log.info("==== OCR识别处理 ==== 截屏图片文件OCR识别成功，区域：{} ==> 识别结果：{}", key, ocrResult.toString());
                    resultList.add(ocrSection.newResult(ocrResult, collectTime));
                } catch (Exception e) {
                    log.error("==== OCR识别处理 ==== 截屏图片文件OCR识别失败，捕获到异常！", e);
                }
            }

            // OCR识别结果保存到数据库
            if (this.ocrSectionResultService != null && CollectionUtils.isNotEmpty(resultList)) {
                this.ocrSectionResultService.saveBatch(resultList);
            }

            // JSON文件保存OCR识别结果
            this.outputAsJsonFile(resultList);

            // 绘制各框选区域并保存图片
            this.outputDrawImage(image);
        }
    }

    private void outputDrawImage(BufferedImage image) {
        if (null == image) {
            return;
        }

        // 创建Graphics2D对象
        Graphics2D g2d = image.createGraphics();

        // 设置颜色和线宽
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        // 绘制各框选区域
        for (String key : this.ocrRectMap.keySet()) {
            // 画矩形框
            OcrSection ocrSection = this.ocrRectMap.get(key);
            Rectangle rect = new Rectangle(ocrSection.getX(), ocrSection.getY(), ocrSection.getWidth(), ocrSection.getHeight());
            g2d.draw(rect);

            // 画文本
            g2d.drawString(ocrSection.displaySection(), ocrSection.getX(), ocrSection.getY());
        }

        // 释放图形上下文使用的资源
        g2d.dispose();

        // 保存修改后的图片
        try {
            String filePath = snapshotFile.getAbsolutePath() + ".绘制框选区域.png";
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void outputAsJsonFile(List<OcrSectionResult> resultList) {
        if (CollectionUtils.isEmpty(resultList)) {
            return;
        }
        String json = JacksonUtils.toJSONStringPretty(resultList);
        String filePath = snapshotFile.getAbsolutePath() + ".OCR识别结果.json";
        try (OutputStream os = new FileOutputStream(filePath)) {
            IOUtils.write(json, os, Charset.defaultCharset());
        } catch (IOException e) {
            log.error("==== OCR识别处理 ==== JSON文件保存OCR识别结果失败，捕获到异常！", e);
        }
    }


}
