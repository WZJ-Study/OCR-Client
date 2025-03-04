package cc.wangzijie.ui.utils;

import cc.wangzijie.server.entity.OcrSectionResult;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomTextFieldTableCell extends TableCell<OcrSectionResult, String> {

    private TextField textField;

    public void startEdit() {
        log.info("==== 单元格开始编辑: 尝试进入编辑状态 ====");
        if (this.isEditable() && this.getTableView().isEditable() && this.getTableColumn().isEditable()) {
            log.info("==== 单元格开始编辑: 允许编辑，进入编辑状态 ====");
            super.startEdit();

            if (this.isEditing()) {
                log.info("==== 单元格开始编辑: 进入编辑状态成功 ====");

                this.createTextField();
                this.setText(null);
                this.textField.setText(this.getItemText());
                this.setGraphic(this.textField);

                this.textField.selectAll();
                this.textField.requestFocus();
            }
        }
    }

    public void cancelEdit() {
        log.info("==== 单元格取消编辑 ====");
        super.cancelEdit();

        this.setText(this.getItemText());
        this.setGraphic(null);
    }

    public void commitEdit(String newValue) {
        super.commitEdit(newValue);
        log.info("==== 单元格结束编辑 ==== 提交内容：{}", newValue);

        this.setItem(newValue);
        this.setGraphic(null);
        this.setText(this.getItemText());
    }

    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        log.info("==== 单元格更新文本 ==== 内容：{} empty: {}", item, empty);

        if (this.isEmpty()) {
            this.setText(null);
            this.setGraphic(null);
        } else {
            if (isEditing()) {
                if (this.textField != null) {
                    this.textField.setText(this.getItemText());
                }
                setText(null);
                setGraphic(this.textField);
            } else {
                setText(this.getItemText());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        if (this.textField != null) {
            log.info("==== 单元格创建TextField ==== 已存在，跳过！");
            return;
        }
        log.info("==== 单元格创建TextField ====");
        this.textField = new TextField(this.getItemText());
        this.textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        this.textField.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue) {
                        commitEdit(this.textField.getText());
                    }
                });
        this.textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                commitEdit(this.textField.getText());
            } else if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
            event.consume();
        });
        this.getStyleClass().add("text-field-table-cell");
    }

    private String getItemText() {
        return this.getItem() == null ? "" : this.getItem();
    }


}
