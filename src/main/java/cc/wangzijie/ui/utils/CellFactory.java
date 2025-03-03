package cc.wangzijie.ui.utils;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class CellFactory {

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> tableCheckBoxColumn(
            Callback<Integer, ObservableValue<Boolean>> paramCallback) {
        return tableCheckBoxColumn(paramCallback, null);
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> tableCheckBoxColumn(
            final Callback<Integer, ObservableValue<Boolean>> getSelectedProperty,
            final StringConverter<T> converter) {
        return paramTableColumn -> new CheckBoxTableCell<>(getSelectedProperty, converter);
    }

}
