package cc.wangzijie.ui.utils;

import javafx.scene.image.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Objects;

@Slf4j
public class ImageLoader {

    public static Image load(String path) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            Objects.requireNonNull(is);
            return new Image(is);
        } catch (Exception e) {
            log.error("图片资源加载失败！", e);
        }
        return null;
    }

}
