package cc.wangzijie;

import cc.wangzijie.utils.SpringHelper;
import cc.wangzijie.ui.JavaFxApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class PtOcrApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PtOcrApplication.class);
        builder.headless(false);
        SpringHelper.setApplicationContext(builder.run(args));
        JavaFxApplication.main(args);
    }

}
