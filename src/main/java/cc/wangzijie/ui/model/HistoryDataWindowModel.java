package cc.wangzijie.ui.model;

import cc.wangzijie.constants.Constants;
import cc.wangzijie.ocr.utils.DateFormat;
import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class HistoryDataWindowModel {

    @Resource
    private IOcrSectionResultService ocrSectionResultService;

    private final ObjectProperty<Image> closeWindowButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<OcrSectionResult>> historyDataList = new SimpleObjectProperty<>();
    private final BooleanProperty selectAllFlag = new SimpleBooleanProperty(false);

    private final StringProperty collectTimeBeginSearchText = new SimpleStringProperty();
    private final StringProperty collectTimeEndSearchText = new SimpleStringProperty();
    private final StringProperty nameSearchText = new SimpleStringProperty();


    public void initSearchArea() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime begin = now.minusMinutes(3);
        this.collectTimeBeginSearchText.setValue(begin.format(Constants.DT_FORMAT));
        this.collectTimeEndSearchText.setValue(now.format(Constants.DT_FORMAT));
        this.nameSearchText.setValue("");
    }

    public void doSearch() {
        String collectTimeBegin = collectTimeBeginSearchText.get();
        String collectTimeEnd = collectTimeEndSearchText.get();
        String name = nameSearchText.get();
        log.info("==== doSearch ==== 开始查询！采集时间范围：【{}】至【{}】，字段名称：【{}】", collectTimeBegin, collectTimeEnd, name);
        List<OcrSectionResult> list = ocrSectionResultService.searchList(collectTimeBegin, collectTimeEnd, name);
        int size = list.size();
        log.info("==== doSearch ==== 查询完毕！查询到【{}】条数据！（采集时间范围：【{}】至【{}】，字段名称：【{}】）", size, collectTimeBegin, collectTimeEnd, name);
        this.getHistoryDataList().clear();
        this.getHistoryDataList().addAll(list);
    }


    public Image getCloseWindowButtonImage() {
        return closeWindowButtonImage.get();
    }

    public ObjectProperty<Image> closeWindowButtonImageProperty() {
        return closeWindowButtonImage;
    }

    public void setCloseWindowButtonImage(Image closeWindowButtonImage) {
        this.closeWindowButtonImage.set(closeWindowButtonImage);
    }

    public ObservableList<OcrSectionResult> getHistoryDataList() {
        return historyDataList.get();
    }

    public ObjectProperty<ObservableList<OcrSectionResult>> historyDataListProperty() {
        return historyDataList;
    }

    public void setHistoryDataList(ObservableList<OcrSectionResult> historyDataList) {
        this.historyDataList.set(historyDataList);
    }


    public boolean isSelectAllFlag() {
        return selectAllFlag.get();
    }

    public BooleanProperty selectAllFlagProperty() {
        return selectAllFlag;
    }

    public void setSelectAllFlag(boolean selectAllFlag) {
        this.selectAllFlag.set(selectAllFlag);
    }

    public String getCollectTimeBeginSearchText() {
        return collectTimeBeginSearchText.get();
    }

    public StringProperty collectTimeBeginSearchTextProperty() {
        return collectTimeBeginSearchText;
    }

    public void setCollectTimeBeginSearchText(String collectTimeBeginSearchText) {
        this.collectTimeBeginSearchText.set(collectTimeBeginSearchText);
    }

    public String getCollectTimeEndSearchText() {
        return collectTimeEndSearchText.get();
    }

    public StringProperty collectTimeEndSearchTextProperty() {
        return collectTimeEndSearchText;
    }

    public void setCollectTimeEndSearchText(String collectTimeEndSearchText) {
        this.collectTimeEndSearchText.set(collectTimeEndSearchText);
    }

    public String getNameSearchText() {
        return nameSearchText.get();
    }

    public StringProperty nameSearchTextProperty() {
        return nameSearchText;
    }

    public void setNameSearchText(String nameSearchText) {
        this.nameSearchText.set(nameSearchText);
    }
}
