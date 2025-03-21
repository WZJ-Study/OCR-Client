package cc.wangzijie.ui.model;


import cc.wangzijie.ui.view.MainWindowView;
import cc.wangzijie.ui.vo.ActionVO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
@Component
public class WithdrawModel {

    @Resource
    private MainWindowView mainWindowView;

    private static final int MAX_DEQUE_SIZE = 100;
    private final BooleanProperty canWithdrawFlag = new SimpleBooleanProperty(false);
    private final Deque<ActionVO> actionDeque = new ArrayDeque<>(MAX_DEQUE_SIZE);

    public void addAction(ActionVO action) {
        actionDeque.addFirst(action);
        canWithdrawFlag.set(!actionDeque.isEmpty());
        if (actionDeque.size() > MAX_DEQUE_SIZE) {
            actionDeque.removeLast();
        }
    }

    public void doWithdraw() {
        if (actionDeque.isEmpty()) {
            return;
        }
        ActionVO action = actionDeque.removeFirst();
        canWithdrawFlag.set(!actionDeque.isEmpty());
        doWithdrawAction(action);
    }

    protected void doWithdrawAction(ActionVO action) {
        log.info("==== doWithdrawAction ==== 撤回动作：{}", action);
        switch (action.getType()) {
            case ADD_RECT:
                // 原动作是新增区域，撤回动作为删除区域
                mainWindowView.doWithdrawAddRect(action);
                break;
            case REMOVE_RECT:
                // 原动作是删除区域，撤回动作为新增区域
                mainWindowView.doWithdrawRemoveRect(action);
                break;
            default:
                log.error("未定义的撤回动作，跳过！");
                break;
        }
    }

    public void resetActionDeque() {
        actionDeque.clear();
        canWithdrawFlag.set(false);
    }

    public boolean isCanWithdrawFlag() {
        return canWithdrawFlag.get();
    }

    public BooleanProperty canWithdrawFlagProperty() {
        return canWithdrawFlag;
    }
}
