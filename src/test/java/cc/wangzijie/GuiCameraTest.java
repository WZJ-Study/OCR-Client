package cc.wangzijie;


import cc.wangzijie.ocr.snapshot.SnapshotCamera;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GuiCameraTest {

    private static Rectangle initScreenRect() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        log.info("==== 初始化 截屏区域 ==== 屏幕尺寸: {}", d);
        return new Rectangle(0, 0, d.width, d.height);
    }

    public static void main(String[] args) throws InterruptedException {
        Rectangle rect = initScreenRect();
        SnapshotCamera cam = new SnapshotCamera(null, null);
        cam.snapshot(rect);
        TimeUnit.SECONDS.sleep(10L);
        cam.snapshot(rect);
    }
}
