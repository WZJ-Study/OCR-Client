package cc.wangzijie.ui.model;

import cc.wangzijie.server.entity.OcrSectionResult;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class DataListAreaModel {

    private final ObjectProperty<Image> dataListTitleBarMenuButtonImage = new SimpleObjectProperty<>();
    private final StringProperty dataListTitleBarMenuTitle = new SimpleStringProperty();
    private final ObjectProperty<Image> dataListTitleBarSearchButtonImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> dataListTitleBarDeleteButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<OcrSectionResult>> ocrSectionResultList = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<OcrSectionResult>> searchedOcrSectionResultList = new SimpleObjectProperty<>();
    private final StringProperty searchText = new SimpleStringProperty();
    private final BooleanProperty selectAllFlag = new SimpleBooleanProperty(false);

    @Getter
    private final Map<String, OcrSectionResult> dataMap = new LinkedHashMap<>();

    public synchronized void clearDataMap() {
        this.dataMap.clear();
        this.ocrSectionResultList.get().clear();
        this.searchedOcrSectionResultList.get().clear();
    }

    public synchronized void addData(String key, OcrSectionResult data) {
        String keyword = this.searchText.get();
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

            // 搜索列表
            int searchedIndex = this.searchedOcrSectionResultList.get().indexOf(oldData);
            if (searchedIndex != -1) {
                // 替换
                if (StringUtils.isEmpty(keyword)) {
                    this.searchedOcrSectionResultList.get().set(searchedIndex, data);
                    this.searchedOcrSectionResultList.get().remove(oldData);
                } else {
                    if (data.getName().contains(keyword)) {
                        this.searchedOcrSectionResultList.get().set(searchedIndex, data);
                        this.searchedOcrSectionResultList.get().remove(oldData);
                    }
                }
            } else {
                // 新增
                if (StringUtils.isEmpty(keyword)) {
                    this.searchedOcrSectionResultList.get().add(data);
                } else {
                    if (data.getName().contains(keyword)) {
                        this.searchedOcrSectionResultList.get().add(data);
                    }
                }
            }
        } else {
            // 首次新增
            this.ocrSectionResultList.get().add(data);

            // 搜索列表
            if (StringUtils.isEmpty(keyword)) {
                this.searchedOcrSectionResultList.get().add(data);
            } else {
                if (data.getName().contains(keyword)) {
                    this.searchedOcrSectionResultList.get().add(data);
                }
            }
        }
    }

    public synchronized void removeData(String key) {
        if (!this.dataMap.containsKey(key)) {
            return;
        }
        OcrSectionResult oldData = this.dataMap.remove(key);
        if (oldData != null) {
            this.ocrSectionResultList.get().remove(oldData);
            this.searchedOcrSectionResultList.get().remove(oldData);
        }
    }

    public void filterData() {
        String keyword = this.searchText.get();
        log.info("==== filterData ==== keyword = {}", keyword);
        if (StringUtils.isEmpty(keyword)) {
            this.searchedOcrSectionResultList.get().clear();
            this.searchedOcrSectionResultList.get().addAll(this.ocrSectionResultList.get());
        } else {
            FilteredList<OcrSectionResult> filteredList = this.ocrSectionResultList.get().filtered(t -> t.getName().contains(keyword));
            this.searchedOcrSectionResultList.get().clear();
            this.searchedOcrSectionResultList.get().addAll(filteredList);
        }
    }

    public void toggleSelectAllFlag() {
        boolean flag = this.selectAllFlag.get();
        log.info("==== toggleSelectAllFlag ==== flag = {}", flag);

        ObservableList<OcrSectionResult> list = this.ocrSectionResultList.get();
        for (OcrSectionResult osr : list) {
            log.info("==== toggleSelectAllFlag ==== {} {} ==> {}", osr.getName(), osr.getPosition(), flag);
            osr.setCheckedFlag(flag);
        }

        ObservableList<OcrSectionResult> searchList = this.searchedOcrSectionResultList.get();
        for (OcrSectionResult osr : searchList) {
            log.info("==== toggleSelectAllFlag ==== searchList {} {} ==> {}", osr.getName(), osr.getPosition(), flag);
            osr.setCheckedFlag(flag);
        }

        // toggle flag
        this.selectAllFlag.set(!flag);
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

    public ObservableList<OcrSectionResult> getSearchedOcrSectionResultList() {
        return searchedOcrSectionResultList.get();
    }

    public ObjectProperty<ObservableList<OcrSectionResult>> searchedOcrSectionResultListProperty() {
        return searchedOcrSectionResultList;
    }

    public void setSearchedOcrSectionResultList(ObservableList<OcrSectionResult> searchedOcrSectionResultList) {
        this.searchedOcrSectionResultList.set(searchedOcrSectionResultList);
    }

    public String getSearchText() {
        return searchText.get();
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText.set(searchText);
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
}
