package cc.wangzijie.ui.view;


import cc.wangzijie.constants.Constants;
import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.ui.helper.StageManager;
import cc.wangzijie.ui.model.HistoryDataWindowModel;
import cc.wangzijie.ui.model.SettingsWindowModel;
import cc.wangzijie.ui.utils.CustomComboBoxTableCell;
import cc.wangzijie.ui.utils.CustomTextFieldTableCell;
import cc.wangzijie.ui.utils.ImageLoader;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import jfxtras.scene.control.LocalDateTimeTextField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Slf4j
@Component
public class HistoryDataWindowView implements Initializable {

    @Resource
    private StageManager stageManager;

    @Resource
    private HistoryDataWindowModel historyDataWindowModel;


    @FXML
    private ImageView closeWindowButtonImage;



    @FXML
    private TableView<OcrSectionResult> historyDataTable;

    @FXML
    private TableColumn<OcrSectionResult, Boolean> varChecked;
    @FXML
    private TableColumn<OcrSectionResult, String> varName;
    @FXML
    private TableColumn<OcrSectionResult, String> varPosition;
    @FXML
    private TableColumn<OcrSectionResult, String> varType;
    @FXML
    private TableColumn<OcrSectionResult, String> varValue;
    @FXML
    private TableColumn<OcrSectionResult, String> varCollectTime;


    @FXML
    public LocalDateTimeTextField collectTimeBeginSearchInput;
    @FXML
    public LocalDateTimeTextField collectTimeEndSearchInput;
    @FXML
    public TextField nameSearchInput;


    private double offsetX;
    private double offsetY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 绑定FXML组件与model属性 - 标题栏右侧窗口按钮
        closeWindowButtonImage.imageProperty().bindBidirectional(historyDataWindowModel.closeWindowButtonImageProperty());

        // 绑定FXML组件与model属性 - 历史数据列表
        historyDataTable.itemsProperty().bindBidirectional(historyDataWindowModel.historyDataListProperty());
        varChecked.setCellValueFactory(new PropertyValueFactory<>("checkedFlag"));
        varName.setCellValueFactory(new PropertyValueFactory<>("name"));
        varPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        varType.setCellValueFactory(new PropertyValueFactory<>("type"));
        varValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        varCollectTime.setCellValueFactory(new PropertyValueFactory<>("collectTime"));

        varChecked.setCellFactory(tableColumn -> new CheckBoxTableCell<>(index -> {
            final OcrSectionResult r = historyDataTable.getItems().get(index);
            boolean checked = r.getCheckedFlag() != null && r.getCheckedFlag();
            ObservableValue<Boolean> property = new SimpleBooleanProperty(r, "checkedFlag", checked);
            property.addListener((observable, oldValue, newValue) -> r.setCheckedFlag(newValue));
            return property;
        }));


        // 绑定FXML组件与model属性 - 右侧搜索区域
        collectTimeBeginSearchInput.textProperty().bindBidirectional(historyDataWindowModel.collectTimeBeginSearchTextProperty());
        collectTimeEndSearchInput.textProperty().bindBidirectional(historyDataWindowModel.collectTimeEndSearchTextProperty());
        nameSearchInput.textProperty().bindBidirectional(historyDataWindowModel.nameSearchTextProperty());

        collectTimeBeginSearchInput.setDateTimeFormatter(Constants.DT_FORMAT);
        collectTimeBeginSearchInput.setParseErrorCallback(e -> {
            log.error("==== collectTimeBeginSearchInput.ParseError ====", e);
            collectTimeBeginSearchInput.setText(LocalDateTime.now().format(Constants.DT_FORMAT));
            return null;
        });

        collectTimeEndSearchInput.setDateTimeFormatter(Constants.DT_FORMAT);
        collectTimeEndSearchInput.setParseErrorCallback(e -> {
            log.error("==== collectTimeEndSearchInput.ParseError ====", e);
            collectTimeEndSearchInput.setText(LocalDateTime.now().format(Constants.DT_FORMAT));
            return null;
        });


        // 初始化数据列表
        historyDataWindowModel.setHistoryDataList(FXCollections.observableArrayList());

        // 处理model属性 - 标题栏右侧窗口按钮
        historyDataWindowModel.setCloseWindowButtonImage(ImageLoader.load(Constants.CLOSE_IMAGE_PATH));
    }


    @FXML
    protected void onHistoryDataWindowMousePressed(MouseEvent event) {
        this.offsetX = event.getSceneX();
        this.offsetY = event.getSceneY();
    }

    @FXML
    protected void onHistoryDataWindowMouseDragged(MouseEvent event) {
        Stage stage = stageManager.getHistoryDataWindowStage();
        if (null != stage) {
            Platform.runLater(() -> {
                stage.setX(event.getScreenX() - offsetX);
                stage.setY(event.getScreenY() - offsetY);
            });
        }
    }

    @FXML
    protected void onCloseWindowButtonClick() {
        Stage stage = stageManager.getHistoryDataWindowStage();
        if (null != stage) {
            Platform.runLater(stage::hide);
        }
    }


    @FXML
    protected void onLatestHourSearch() {
        log.info("==== onLatestHourSearch ==== 设置搜索时间范围：最近1小时！");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime begin = now.minusHours(1);
        historyDataWindowModel.setCollectTimeBeginSearchText(begin.format(Constants.DT_FORMAT));
        historyDataWindowModel.setCollectTimeEndSearchText(now.format(Constants.DT_FORMAT));
    }

    @FXML
    protected void onLatestDaySearch() {
        log.info("==== onLatestDaySearch ==== 设置搜索时间范围：最近1日！");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime begin = now.minusDays(1);
        historyDataWindowModel.setCollectTimeBeginSearchText(begin.format(Constants.DT_FORMAT));
        historyDataWindowModel.setCollectTimeEndSearchText(now.format(Constants.DT_FORMAT));
    }

    @FXML
    protected void onLatestWeekSearch() {
        log.info("==== onLatestWeekSearch ==== 设置搜索时间范围：最近1周！");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime begin = now.minusWeeks(1);
        historyDataWindowModel.setCollectTimeBeginSearchText(begin.format(Constants.DT_FORMAT));
        historyDataWindowModel.setCollectTimeEndSearchText(now.format(Constants.DT_FORMAT));
    }

    @FXML
    protected void onLatestMonthSearch() {
        log.info("==== onLatestMonthSearch ==== 设置搜索时间范围：最近1月！");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime begin = now.minusWeeks(1);
        historyDataWindowModel.setCollectTimeBeginSearchText(begin.format(Constants.DT_FORMAT));
        historyDataWindowModel.setCollectTimeEndSearchText(now.format(Constants.DT_FORMAT));
    }

    @FXML
    protected void onLatestYearSearch() {
        log.info("==== onLatestYearSearch ==== 设置搜索时间范围：最近1年！");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime begin = now.minusYears(1);
        historyDataWindowModel.setCollectTimeBeginSearchText(begin.format(Constants.DT_FORMAT));
        historyDataWindowModel.setCollectTimeEndSearchText(now.format(Constants.DT_FORMAT));
    }

    @FXML
    protected void onSearchButtonClick() {
        log.info("==== onSearchButtonClick ==== 点击【搜索】按钮！");
        historyDataWindowModel.doSearch();
    }


}
