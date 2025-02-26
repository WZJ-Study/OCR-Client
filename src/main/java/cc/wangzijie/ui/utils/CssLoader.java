package cc.wangzijie.ui.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Objects;

@Slf4j
public class CssLoader {

    public static URL load(String path) {
        try {
            URL url = CssLoader.class.getResource(path);
            Objects.requireNonNull(url);
            return url;
        } catch (Exception e) {
            log.error("CSS样式表文件加载失败！", e);
        }
        return null;
    }

}
