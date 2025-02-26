package cc.wangzijie.fxml;

import cc.wangzijie.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum FxmlViews {

    MAIN_WINDOW(0, Constants.MAIN_WINDOW_TITLE, "/cc/wangzijie/ui/view/MainWindowView.fxml"),
    SETTINGS_WINDOW(1, Constants.SETTINGS_WINDOW_TITLE, "/cc/wangzijie/ui/view/SettingsWindowView.fxml"),
    HISTORY_DATA_WINDOW(2, Constants.HISTORY_DATA_WINDOW_TITLE, "/cc/wangzijie/ui/view/HistoryDataWindowView.fxml"),
    ;

    private final Integer id;
    private final String title;
    private final String fxmlPath;



}
