package cc.wangzijie.fxml.loader.impl;


import cc.wangzijie.fxml.FxmlViews;
import cc.wangzijie.fxml.loader.SpringFxmlLoader;
import cc.wangzijie.spring.SpringHelper;
import cc.wangzijie.ui.utils.CssLoader;
import cc.wangzijie.ui.utils.DrawUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
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

        // 窗口尺寸计算
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double width = view.getPrefWidth();
        double height = view.getPrefHeight();
        if (view.validDynamicWidthRatio()) {
            width = bounds.getWidth() * view.getDynamicWidthRatio();
        }
        if (view.validDynamicHeightRatio()) {
            height = bounds.getHeight() * view.getDynamicHeightRatio();
        }
        log.info("==== 窗口尺寸 ==== width={} height={}", width, height);

        // 场景scene
        Scene scene = new Scene(root, width, height);
        scene.setFill(Color.TRANSPARENT);

        URL css = CssLoader.load(view.getCssPath());
        if (null != css) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        // 舞台stage
        stage.setScene(scene);
        stage.setTitle(view.getTitle());
        stage.initStyle(view.getStageStyle());
        stage.setResizable(view.isResizeable());
        DrawUtils drawUtils = new DrawUtils(view.getPrefWidth(), view.getPrefHeight());
        if (view.isResizeable()) {
            drawUtils.addStretch(stage, root);
        }
        stage.show();
    }
}
