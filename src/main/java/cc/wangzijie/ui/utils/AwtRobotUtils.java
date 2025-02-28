package cc.wangzijie.ui.utils;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.image.BufferedImage;


@Slf4j
public class AwtRobotUtils {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (Exception e) {
            log.error("Robot初始化失败！", e);
        }
    }

    public static BufferedImage createScreenCapture(java.awt.Rectangle rect) {
        return robot.createScreenCapture(rect);
    }

    public static BufferedImage createScreenCapture(javafx.scene.shape.Rectangle rect) {
        return createScreenCapture(convert(rect));
    }

    public static Rectangle convert(javafx.scene.shape.Rectangle rect) {
        return new java.awt.Rectangle(
                (int) rect.getLayoutX(),
                (int) rect.getLayoutY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );
    }
}
