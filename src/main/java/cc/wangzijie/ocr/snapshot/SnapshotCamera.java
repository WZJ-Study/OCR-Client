package cc.wangzijie.ocr.snapshot;


import cc.wangzijie.config.SnapshotFileConfig;
import cc.wangzijie.ui.utils.AwtRobotUtils;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
public class SnapshotCamera {




    public SnapshotCamera(SnapshotFileConfig cameraConfig) {

    }

    public BufferedImage doSnapshot(javafx.scene.shape.Rectangle rect) {
        return this.doSnapshot(AwtRobotUtils.convert(rect));
    }

    public BufferedImage doSnapshot(Rectangle rect) {
        return AwtRobotUtils.createScreenCapture(rect);
    }



}
