package cc.wangzijie.fxml.loader.impl;


import cc.wangzijie.fxml.FxmlViews;
import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.fxml.loader.SpringFxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Service
public class SpringFxmlLoaderImpl implements SpringFxmlLoader {

    @Resource
    private SpringFxmlLoader springFxmlLoader;

    protected static FXMLLoader springFxmlLoader(URL url) {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setControllerFactory(SpringHelper::getBean);
        return fxmlLoader;
    }

    @Override
    public Parent load(String path) {
        try {
            URL url = new ClassPathResource(path).getURL();
            return springFxmlLoader(url).load();
        } catch (IOException e) {
            log.error("加载FXML失败！", e);
        }
        return null;
    }

    @Override
    public void loadTo(FxmlViews view, Stage stage) {
        if (null == view || null == stage) {
            return;
        }
        Parent root = springFxmlLoader.load(view.getFxmlPath());
        if (null == root) {
            return;
        }
        stage.setScene(new Scene(root));
        stage.setTitle(view.getTitle());
        stage.sizeToScene();
        stage.show();
    }
}
