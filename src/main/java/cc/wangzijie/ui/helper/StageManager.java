package cc.wangzijie.ui.helper;

import javafx.stage.Stage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
public class StageManager {

    /**
     * 主窗口
     */
    private Stage mainWindowStage;

    /**
     * 设置窗口
     */
    private Stage settingsWindowStage;

    /**
     * 历史数据窗口
     */
    private Stage historyDataWindowStage;

}
