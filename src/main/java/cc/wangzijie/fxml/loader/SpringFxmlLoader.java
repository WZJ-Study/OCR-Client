package cc.wangzijie.fxml.loader;

import cc.wangzijie.fxml.FxmlViews;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface SpringFxmlLoader {

    Parent load(String path);

    void loadTo(FxmlViews view, Stage stage);

}
