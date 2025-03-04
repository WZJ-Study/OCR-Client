package cc.wangzijie.ui.model;

import cc.wangzijie.server.entity.OcrSectionResult;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@Component
public class DataListAreaModel {

    private final ObjectProperty<Image> dataListTitleBarMenuButtonImage = new SimpleObjectProperty<>();
    private final StringProperty dataListTitleBarMenuTitle = new SimpleStringProperty();
    private final ObjectProperty<Image> dataListTitleBarSearchButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> dataListTitleBarDeleteButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<OcrSectionResult>> ocrSectionResultList = new SimpleObjectProperty<>();

    @Getter
    private final Map<String, OcrSectionResult> dataMap = new LinkedHashMap<>();

    public synchronized void clearDataMap() {
        this.dataMap.clear();
        this.ocrSectionResultList.get().clear();
    }

    public synchronized void addData(String key, OcrSectionResult data) {
        OcrSectionResult oldData = this.dataMap.put(key, data);
        if (oldData != null) {
            // 更新
            int index = this.ocrSectionResultList.get().indexOf(oldData);
            if (index != -1) {
                // 替换
                this.ocrSectionResultList.get().set(index, data);
                this.ocrSectionResultList.get().remove(oldData);
            } else {
                // 新增
                this.ocrSectionResultList.get().add(data);
            }
        } else {
            // 首次新增
            this.ocrSectionResultList.get().add(data);
        }
    }

    public synchronized OcrSectionResult removeData(String key) {
        if (!this.dataMap.containsKey(key)) {
            return null;
        }
        OcrSectionResult oldData = this.dataMap.remove(key);
        if (oldData != null) {
            this.ocrSectionResultList.get().remove(oldData);
        }
        return oldData;
    }


    public Image getDataListTitleBarMenuButtonImage() {
        return dataListTitleBarMenuButtonImage.get();
    }

    public ObjectProperty<Image> dataListTitleBarMenuButtonImageProperty() {
        return dataListTitleBarMenuButtonImage;
    }

    public void setDataListTitleBarMenuButtonImage(Image dataListTitleBarMenuButtonImage) {
        this.dataListTitleBarMenuButtonImage.set(dataListTitleBarMenuButtonImage);
    }

    public String getDataListTitleBarMenuTitle() {
        return dataListTitleBarMenuTitle.get();
    }

    public StringProperty dataListTitleBarMenuTitleProperty() {
        return dataListTitleBarMenuTitle;
    }

    public void setDataListTitleBarMenuTitle(String dataListTitleBarMenuTitle) {
        this.dataListTitleBarMenuTitle.set(dataListTitleBarMenuTitle);
    }

    public Image getDataListTitleBarSearchButtonImage() {
        return dataListTitleBarSearchButtonImage.get();
    }

    public ObjectProperty<Image> dataListTitleBarSearchButtonImageProperty() {
        return dataListTitleBarSearchButtonImage;
    }

    public void setDataListTitleBarSearchButtonImage(Image dataListTitleBarSearchButtonImage) {
        this.dataListTitleBarSearchButtonImage.set(dataListTitleBarSearchButtonImage);
    }

    public Image getDataListTitleBarDeleteButtonImage() {
        return dataListTitleBarDeleteButtonImage.get();
    }

    public ObjectProperty<Image> dataListTitleBarDeleteButtonImageProperty() {
        return dataListTitleBarDeleteButtonImage;
    }

    public void setDataListTitleBarDeleteButtonImage(Image dataListTitleBarDeleteButtonImage) {
        this.dataListTitleBarDeleteButtonImage.set(dataListTitleBarDeleteButtonImage);
    }


    public ObservableList<OcrSectionResult> getOcrSectionResultList() {
        return ocrSectionResultList.get();
    }

    public ObjectProperty<ObservableList<OcrSectionResult>> ocrSectionResultListProperty() {
        return ocrSectionResultList;
    }

    public void setOcrSectionResultList(ObservableList<OcrSectionResult> ocrSectionResultList) {
        this.ocrSectionResultList.set(ocrSectionResultList);
    }

}
