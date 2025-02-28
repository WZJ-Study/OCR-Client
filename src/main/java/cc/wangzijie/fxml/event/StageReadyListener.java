package cc.wangzijie.fxml.event;


import cc.wangzijie.fxml.FxmlViews;
import cc.wangzijie.fxml.loader.SpringFxmlLoader;
import cc.wangzijie.ui.helper.StageManager;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StageReadyListener implements ApplicationListener<StageReadyEvent> {

    @Resource
    private SpringFxmlLoader springFxmlLoader;

    @Resource
    private StageManager stageManager;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        springFxmlLoader.loadTo(FxmlViews.MAIN_WINDOW, stage);
        stageManager.setMainWindowStage(stage);
    }

}
