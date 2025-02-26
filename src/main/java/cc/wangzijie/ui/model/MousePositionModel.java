package cc.wangzijie.ui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MousePositionModel {

    private final DoubleProperty x = new SimpleDoubleProperty(0);
    private final DoubleProperty y = new SimpleDoubleProperty(0);
    private final DoubleProperty z = new SimpleDoubleProperty(0);
    private final DoubleProperty screenX = new SimpleDoubleProperty(0);
    private final DoubleProperty screenY = new SimpleDoubleProperty(0);
    private final DoubleProperty sceneX = new SimpleDoubleProperty(0);
    private final DoubleProperty sceneY = new SimpleDoubleProperty(0);
    private final StringProperty displayText = new SimpleStringProperty("");

    public void refresh(MouseEvent event) {
        if (null == event) {
            return;
        }
        x.set(event.getX());
        x.set(event.getX());
        y.set(event.getY());
        z.set(event.getZ());
        screenX.set(event.getScreenX());
        screenY.set(event.getScreenY());
        sceneX.set(event.getSceneX());
        sceneY.set(event.getSceneY());
        String text = String.format("abs{%s, %s, %s} screen(%s, %s) scene[%s, %s]",
                x.getValue(), y.getValue(), z.getValue(), screenX.getValue(), screenY.getValue(), sceneX.getValue(), sceneY.getValue());
        log.info("==== MousePositionModel refresh ==== {}", text);
        displayText.set(text);
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public double getZ() {
        return z.get();
    }

    public DoubleProperty zProperty() {
        return z;
    }

    public void setZ(double z) {
        this.z.set(z);
    }

    public double getScreenX() {
        return screenX.get();
    }

    public DoubleProperty screenXProperty() {
        return screenX;
    }

    public void setScreenX(double screenX) {
        this.screenX.set(screenX);
    }

    public double getScreenY() {
        return screenY.get();
    }

    public DoubleProperty screenYProperty() {
        return screenY;
    }

    public void setScreenY(double screenY) {
        this.screenY.set(screenY);
    }

    public double getSceneX() {
        return sceneX.get();
    }

    public DoubleProperty sceneXProperty() {
        return sceneX;
    }

    public void setSceneX(double sceneX) {
        this.sceneX.set(sceneX);
    }

    public double getSceneY() {
        return sceneY.get();
    }

    public DoubleProperty sceneYProperty() {
        return sceneY;
    }

    public void setSceneY(double sceneY) {
        this.sceneY.set(sceneY);
    }

    public String getDisplayText() {
        return displayText.get();
    }

    public StringProperty displayTextProperty() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText.set(displayText);
    }
}
