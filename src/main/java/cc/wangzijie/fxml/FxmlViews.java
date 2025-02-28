package cc.wangzijie.fxml;

import cc.wangzijie.constants.Constants;
import javafx.stage.StageStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum FxmlViews {

    MAIN_WINDOW(0, Constants.MAIN_WINDOW_TITLE, "/cc/wangzijie/ui/view/MainWindowView.fxml", "/cc/wangzijie/ui/css/MainWindowView.css", StageStyle.TRANSPARENT, true, -1.0, -1.0, 1200.0, 800.0),
    SETTINGS_WINDOW(1, Constants.SETTINGS_WINDOW_TITLE, "/cc/wangzijie/ui/view/SettingsWindowView.fxml", "/cc/wangzijie/ui/css/SettingsWindowView.css", StageStyle.DECORATED, false, -1.0, -1.0,800.0, 600.0),
    HISTORY_DATA_WINDOW(2, Constants.HISTORY_DATA_WINDOW_TITLE, "/cc/wangzijie/ui/view/HistoryDataWindowView.fxml", "/cc/wangzijie/ui/css/HistoryDataWindowView.css", StageStyle.DECORATED, true, -1.0, -1.0,800.0, 600.0),
    ;

    private final Integer id;
    private final String title;
    private final String fxmlPath;
    private final String cssPath;
    private final StageStyle stageStyle;
    private final boolean resizeable;
    private final Double dynamicWidthRatio;
    private final Double dynamicHeightRatio;
    private final Double prefWidth;
    private final Double prefHeight;

    public boolean validDynamicWidthRatio() {
        if (null == dynamicWidthRatio) {
            return false;
        }
        return dynamicWidthRatio > 0 && dynamicWidthRatio <= 1;
    }

    public boolean validDynamicHeightRatio() {
        if (null == dynamicHeightRatio) {
            return false;
        }
        return dynamicHeightRatio > 0 && dynamicHeightRatio <= 1;
    }
}
