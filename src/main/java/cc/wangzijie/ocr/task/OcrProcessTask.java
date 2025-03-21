package cc.wangzijie.ocr.task;

import cc.wangzijie.constants.Constants;
import cc.wangzijie.ui.model.SettingsWindowModel;
import cc.wangzijie.utils.DateFormat;
import cc.wangzijie.utils.DateUtils;
import cc.wangzijie.utils.FileSizeUtils;
import cc.wangzijie.utils.JacksonUtils;
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
     * 数据列表区域模型
     */
    private final DataListAreaModel dataListAreaModel;

    /**
     * OCR识别结果数据库保存服务
     */
    private final IOcrSectionResultService ocrSectionResultService;

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

    /**
     * 截屏图片保存配置
     */
    private final String outputFolderPath;
    private final String nowStr;

    public OcrProcessTask(DataListAreaModel dataListAreaModel, SettingsWindowModel settingsWindowModel, IOcrSectionResultService ocrSectionResultService, InferenceEngine ocrEngine, BufferedImage snapshotImage, Map<String, OcrSection> ocrRectMap) {
        this.dataListAreaModel = dataListAreaModel;
        this.ocrSectionResultService = ocrSectionResultService;
        this.snapshotImage = snapshotImage;
        this.ocrRectMap = ocrRectMap;
        this.ocrEngine = ocrEngine;
        this.nowStr = DateUtils.nowStr(DateFormat.DUMMY_CODE);
        if (settingsWindowModel == null || settingsWindowModel.getOutputFolderPath() == null) {
            this.outputFolderPath = Constants.DEFAULT_OUTPUT_FOLDER_PATH + File.separator + nowStr;
        } else {
            this.outputFolderPath = settingsWindowModel.getOutputFolderPath() + File.separator + nowStr;
        }
        log.info("==== 初始化 OcrProcessTask ==== 当前时间：{}\n截屏文件输出目录: {}\n",
                this.nowStr, this.outputFolderPath);
    }

    @Override
    public void run() {
        // 创建上级目录
        this.initOutputFolder();

        // OCR识别各框选区域
        String collectTime = DateUtils.nowStr();
        List<OcrSectionResult> resultList = new LinkedList<>();
        for (String key : this.ocrRectMap.keySet()) {
            try {
                OcrSection ocrSection = this.ocrRectMap.get(key);

                // 创建截取区域的新图片
                BufferedImage rectImage = this.snapshotImage.getSubimage(ocrSection.getX(), ocrSection.getY(), ocrSection.getWidth(), ocrSection.getHeight());
                String subFileName = String.format("截屏图片-%s.OCR区域.%s.%s", nowStr, key, Constants.IMAGE_FORMAT);
                String subFilePath = String.format("%s%s%s", this.outputFolderPath, File.separator, subFileName);

                // 保存截取区域的图片
                ImageIO.write(rectImage, Constants.IMAGE_FORMAT, new File(subFilePath));

                // 执行OCR识别
                OcrResult ocrResult = this.ocrEngine.runOcr(subFilePath);
                log.info("==== OCR识别处理 ==== 截屏图片文件OCR识别成功，区域：{} ==> 识别结果：{}", key, ocrResult.toString());
                OcrSectionResult result = ocrSection.newResult(ocrResult, collectTime);
                resultList.add(result);

                // OCR识别结果更新到UI视图模型中
                dataListAreaModel.addData(ocrSection.displayPosition(), result);
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

        // 保存截屏图片文件
        this.saveToFile();

        // 绘制各框选区域并保存图片
        this.saveDrawImageToFile();
    }

    /**
     * 初始化输出目录
     */
    private void initOutputFolder() {
        try {
            File file = new File(this.outputFolderPath);
            boolean mkdirsFlag = file.mkdirs();
            if (mkdirsFlag) {
                log.info("==== initOutputFolder ==== 初始化输出目录：{}", this.outputFolderPath);
            }
        } catch (Exception e) {
            log.error("==== initOutputFolder ==== 初始化输出目录失败！", e);
        }
    }

    /**
     * 截屏图片保存为文件
     */
    private void saveToFile() {
        try {
            int height = this.snapshotImage.getHeight();
            int width = this.snapshotImage.getWidth();
            log.info("==== 截屏图片保存为文件 ==== 保存截屏图片，图片尺寸 height = {} width = {}", height, width);
            String fileName = String.format("截屏图片-%s.%s", this.nowStr, Constants.IMAGE_FORMAT);
            String filePath = String.format("%s%s%s", this.outputFolderPath, File.separator, fileName);
            File file = new File(filePath);
            ImageIO.write(this.snapshotImage, Constants.IMAGE_FORMAT, file);
            log.info("==== 截屏图片保存为文件 ==== 截屏文件大小：{} \n保存为：{}", FileSizeUtils.displayFileSize(file), file.getAbsolutePath());
        } catch (IOException e) {
            log.error("==== 截屏图片保存为文件 ==== 截屏文件保存失败，捕获到异常！", e);
        }
    }



    private void saveDrawImageToFile() {
        // 创建Graphics2D对象
        Graphics2D g2d = this.snapshotImage.createGraphics();

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
            String fileName = String.format("截屏图片-%s.绘制框选区域.%s", this.nowStr, Constants.IMAGE_FORMAT);
            String filePath = String.format("%s%s%s", this.outputFolderPath, File.separator, fileName);
            ImageIO.write(this.snapshotImage, Constants.IMAGE_FORMAT, new File(filePath));
        } catch (IOException e) {
            log.error("==== OCR识别处理 ==== 绘制框选区域图片文件保存失败，捕获到异常！", e);
        }
    }

    private void outputAsJsonFile(List<OcrSectionResult> resultList) {
        if (CollectionUtils.isEmpty(resultList)) {
            return;
        }
        String json = JacksonUtils.toJSONStringPretty(resultList);

        String fileName = String.format("截屏图片-%s.OCR识别结果.json", this.nowStr);
        String filePath = String.format("%s%s%s", this.outputFolderPath, File.separator, fileName);
        try (OutputStream os = new FileOutputStream(filePath)) {
            IOUtils.write(json, os, Charset.defaultCharset());
        } catch (IOException e) {
            log.error("==== OCR识别处理 ==== JSON文件保存OCR识别结果失败，捕获到异常！", e);
        }
    }


}
