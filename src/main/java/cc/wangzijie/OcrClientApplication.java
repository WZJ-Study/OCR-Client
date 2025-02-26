package cc.wangzijie;

import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.ui.JavaFxApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OcrClientApplication {

    public static void main(String[] args) {
        SpringHelper.setApplicationContext(SpringApplication.run(OcrClientApplication.class, args));
        JavaFxApplication.main(args);
    }

}
