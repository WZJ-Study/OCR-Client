package cc.wangzijie.ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CollectConfigModel {

    private final IntegerProperty collectInterval = new SimpleIntegerProperty(10);

    public int getCollectInterval() {
        return collectInterval.get();
    }

    public IntegerProperty collectIntervalProperty() {
        return collectInterval;
    }

    public void setCollectInterval(int collectInterval) {
        this.collectInterval.set(collectInterval);
    }
}
