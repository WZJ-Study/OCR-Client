package cc.wangzijie.ocr.snapshot;


import cc.wangzijie.ocr.config.SnapshotCameraConfig;
import cc.wangzijie.ocr.utils.DateFormat;
import cc.wangzijie.ocr.utils.DateUtils;
import cc.wangzijie.ocr.utils.FileSizeUtils;
import cc.wangzijie.ui.utils.AwtRobotUtils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class SnapshotCamera {


    private final String outputFolderPath;
    private final String fileNamePrefix;
    private final String imageFormat;


    public SnapshotCamera(SnapshotCameraConfig cameraConfig) {
        if (cameraConfig == null) {
            this.outputFolderPath = SnapshotCameraConfig.DEFAULT_OUTPUT_FOLDER_PATH;
            this.fileNamePrefix = SnapshotCameraConfig.DEFAULT_FILE_NAME_PREFIX;
            this.imageFormat = SnapshotCameraConfig.DEFAULT_IMAGE_FORMAT;
        } else {
            this.outputFolderPath = cameraConfig.getOutputFolderPath() == null ? SnapshotCameraConfig.DEFAULT_OUTPUT_FOLDER_PATH : cameraConfig.getOutputFolderPath();
            this.fileNamePrefix = cameraConfig.getFileNamePrefix() == null ? SnapshotCameraConfig.DEFAULT_FILE_NAME_PREFIX : cameraConfig.getFileNamePrefix();
            this.imageFormat = cameraConfig.getImageFormat() == null ? SnapshotCameraConfig.DEFAULT_IMAGE_FORMAT : cameraConfig.getImageFormat();
        }
        log.info("==== 初始化 GuiCamera ==== 文件输出目录: {}\n文件命名前缀：{}\n图片格式：{}", this.outputFolderPath, this.fileNamePrefix, this.imageFormat);
    }

    public File snapshot(javafx.scene.shape.Rectangle rect) {
        return this.snapshot(AwtRobotUtils.convert(rect));
    }

    public File snapshot(Rectangle rect) {
        // 截屏
        BufferedImage screenshot = AwtRobotUtils.createScreenCapture(rect);
        // 保存到文件
        try {
            File file = this.prepareFile();
            ImageIO.write(screenshot, imageFormat, file);
            log.info("==== 截屏 GuiCamera ==== 截屏文件大小：{} \n保存为：{}", FileSizeUtils.displayFileSize(file), file.getAbsolutePath());
            return file;
        } catch (IOException e) {
            log.error("==== 截屏 GuiCamera ==== 截屏失败，捕获到异常！", e);
        }
        return null;
    }

    private File prepareFile() {
        String nowStr = DateUtils.nowStr(DateFormat.DUMMY_CODE);
        String folderPath = outputFolderPath + File.separator + nowStr;
        String fileName = String.format("%s-%s.%s", this.fileNamePrefix, nowStr, this.imageFormat);
        String filePath = String.format("%s%s%s", folderPath, File.separator, fileName);
        File file = new File(filePath);
        boolean mkdirsFlag = file.mkdirs();
        if (mkdirsFlag) {
            log.info("==== 截屏 GuiCamera ==== prepareFile 创建上级目录：{}", folderPath);
        }
        return file;
    }
}
