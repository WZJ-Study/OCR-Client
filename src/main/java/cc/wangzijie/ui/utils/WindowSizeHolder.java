package cc.wangzijie.ui.utils;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WindowSizeHolder {

    @Getter
    private static boolean maximized = false;

    private static double x;
    private static double y;
    private static double width;
    private static double height;

    public static void backup(Stage stage) {
        x = stage.getX();
        y = stage.getY();
        width = stage.getWidth();
        height = stage.getHeight();
        log.info("==== WindowSizeHolder ==== 备份最大化之前的窗口位置大小：x={}, y={}, width={}, height={}", x, y, width, height);
        maximized = true;
    }

    public static void restore(Stage stage) {
        stage.setX(x);
        stage.setY(y);
        stage.setWidth(width);
        stage.setHeight(height);
        log.info("==== WindowSizeHolder ==== 恢复到最大化之前的窗口位置大小：x={}, y={}, width={}, height={}", x, y, width, height);
        maximized = false;
    }

}
