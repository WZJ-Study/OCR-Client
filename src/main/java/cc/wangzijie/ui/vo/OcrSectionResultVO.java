package cc.wangzijie.ui.vo;

import cc.wangzijie.server.entity.OcrSectionResult;
import javafx.beans.property.*;

import java.util.Date;


public class OcrSectionResultVO {


    /**
     * checkBox选中情况
     */
    private final SimpleBooleanProperty checkedFlag;

    /**
     * 采集数据ID
     */
    private final SimpleLongProperty resultId;

    /**
     * 采集区域ID
     */
    private final SimpleLongProperty sectionId;

    /**
     * 字段名称
     */
    private final SimpleStringProperty name;

    /**
     * 字段位置 (x;y;width;height)
     */
    private final SimpleStringProperty position;

    /**
     * 字段位置-x
     */
    private final SimpleIntegerProperty x;

    /**
     * 字段位置-y
     */
    private final SimpleIntegerProperty y;

    /**
     * 字段位置-width
     */
    private final SimpleIntegerProperty width;

    /**
     * 字段位置-height
     */
    private final SimpleIntegerProperty height;

    /**
     * 字段类型：数字/文本
     */
    private final SimpleStringProperty type;

    /**
     * 采集结果值
     */
    private final SimpleStringProperty value;

    /**
     * 采集时间
     */
    private final SimpleObjectProperty<Date> collectTime;


    public OcrSectionResultVO(Boolean checkedFlag, Long resultId, Long sectionId, String name, String position, Integer x, Integer y, Integer width, Integer height, String type, String value, Date collectTime) {
        this.checkedFlag = new SimpleBooleanProperty(this, "checkedFlag", checkedFlag);
        this.resultId = new SimpleLongProperty(this, "resultId", resultId);
        this.sectionId = new SimpleLongProperty(this, "sectionId", sectionId);
        this.name = new SimpleStringProperty(this, "name", name);
        this.position = new SimpleStringProperty(this, "position", position);
        this.x = new SimpleIntegerProperty(this, "x", x);
        this.y = new SimpleIntegerProperty(this, "y", y);
        this.width = new SimpleIntegerProperty(this, "width", width);
        this.height = new SimpleIntegerProperty(this, "height", height);
        this.type = new SimpleStringProperty(this, "type", type);
        this.value = new SimpleStringProperty(this, "value", value);
        this.collectTime = new SimpleObjectProperty<>(this, "collectTime", collectTime);
    }

    public OcrSectionResultVO(OcrSectionResult data) {
        this.checkedFlag = new SimpleBooleanProperty(this, "checkedFlag", false);
        this.resultId = new SimpleLongProperty(this, "resultId", data.getId());
        this.sectionId = new SimpleLongProperty(this, "sectionId", data.getSectionId());
        this.name = new SimpleStringProperty(this, "name", data.getName());
        this.position = new SimpleStringProperty(this, "position", data.getPosition());
        this.x = new SimpleIntegerProperty(this, "x", data.getX());
        this.y = new SimpleIntegerProperty(this, "y", data.getY());
        this.width = new SimpleIntegerProperty(this, "width", data.getWidth());
        this.height = new SimpleIntegerProperty(this, "height", data.getHeight());
        this.type = new SimpleStringProperty(this, "type", data.getType());
        this.value = new SimpleStringProperty(this, "value", data.getValue());
        this.collectTime = new SimpleObjectProperty<>(this, "collectTime", data.getCollectTime());
    }

    public Date getCollectTime() {
        return collectTime.get();
    }

    public SimpleObjectProperty<Date> collectTimeProperty() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime.set(collectTime);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getHeight() {
        return height.get();
    }

    public SimpleIntegerProperty heightProperty() {
        return height;
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public int getWidth() {
        return width.get();
    }

    public SimpleIntegerProperty widthProperty() {
        return width;
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public int getY() {
        return y.get();
    }

    public SimpleIntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public int getX() {
        return x.get();
    }

    public SimpleIntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public long getSectionId() {
        return sectionId.get();
    }

    public SimpleLongProperty sectionIdProperty() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId.set(sectionId);
    }

    public long getResultId() {
        return resultId.get();
    }

    public SimpleLongProperty resultIdProperty() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId.set(resultId);
    }

    public boolean isCheckedFlag() {
        return checkedFlag.get();
    }

    public SimpleBooleanProperty checkedFlagProperty() {
        return checkedFlag;
    }

    public void setCheckedFlag(boolean checkedFlag) {
        this.checkedFlag.set(checkedFlag);
    }

    @Override
    public String toString() {
        return "OcrSectionResultVO{" + "checkedFlag=" + checkedFlag + ", resultId=" + resultId + ", sectionId=" + sectionId + ", name=" + name + ", position=" + position + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", type=" + type + ", value=" + value + ", collectTime=" + collectTime + '}';
    }
}
