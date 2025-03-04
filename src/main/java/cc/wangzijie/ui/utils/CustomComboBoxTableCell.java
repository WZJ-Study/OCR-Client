package cc.wangzijie.ui.utils;

import cc.wangzijie.server.entity.OcrSectionResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class CustomComboBoxTableCell extends ComboBoxTableCell<OcrSectionResult, String> {


    public CustomComboBoxTableCell(List<String> items) {
        super(FXCollections.observableArrayList(items));
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(item);
        }
    }

}
