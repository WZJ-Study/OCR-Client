package cc.wangzijie;

import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.ui.JavaFxApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OcrClientApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(OcrClientApplication.class);
        builder.headless(false);
        SpringHelper.setApplicationContext(builder.run(args));
        JavaFxApplication.main(args);
    }

}
