package cc.wangzijie.ui.model;

import cc.wangzijie.server.entity.OcrResult;
import cc.wangzijie.ui.vo.OcrSectionResultVO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DataListAreaModel {

    private final ObjectProperty<Image> dataListTitleBarMenuButtonImage = new SimpleObjectProperty<>();
    private final StringProperty dataListTitleBarMenuTitle = new SimpleStringProperty();
    private final ObjectProperty<Image> dataListTitleBarSearchButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> dataListTitleBarDeleteButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<OcrSectionResultVO>> ocrSectionResultList = new SimpleObjectProperty<>();


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


    public ObservableList<OcrSectionResultVO> getOcrSectionResultList() {
        return ocrSectionResultList.get();
    }

    public ObjectProperty<ObservableList<OcrSectionResultVO>> ocrSectionResultListProperty() {
        return ocrSectionResultList;
    }

    public void setOcrSectionResultList(ObservableList<OcrSectionResultVO> ocrSectionResultList) {
        this.ocrSectionResultList.set(ocrSectionResultList);
    }

    public boolean addToOcrSectionResultList(OcrSectionResultVO vo) {
        if (null == ocrSectionResultList.get()) {
            ocrSectionResultList.set(FXCollections.observableArrayList());
        }
        return ocrSectionResultList.get().add(vo);
    }

    public boolean addToOcrSectionResultList(OcrResult ocrResult) {
        if (null == ocrSectionResultList.get()) {
            ocrSectionResultList.set(FXCollections.observableArrayList());
        }
        return ocrSectionResultList.get().add(new OcrSectionResultVO(ocrResult));
    }
}
