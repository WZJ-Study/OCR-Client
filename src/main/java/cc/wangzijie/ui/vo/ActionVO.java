package cc.wangzijie.ui.vo;

import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.ui.enums.ActionTypeEnum;
import javafx.scene.shape.Rectangle;
import lombok.Data;

@Data
public class ActionVO {

    private ActionTypeEnum type;
    private String key;
    private Rectangle rect;
    private OcrSection ocrSection;

}
