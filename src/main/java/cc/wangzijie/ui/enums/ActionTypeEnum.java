package cc.wangzijie.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ActionTypeEnum {

    ADD_RECT("addRect", "增加识别区域"),
    REMOVE_RECT("removeRect", "移除识别区域"),
    ;

    private final String type;
    private final String desc;

}
