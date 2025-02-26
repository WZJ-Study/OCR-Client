package cc.wangzijie.fxml.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return ((Stage)getSource());
    }

}
