package cc.wangzijie.ui.model;

import javafx.beans.property.SimpleStringProperty;
import org.springframework.stereotype.Component;

@Component
public class MainWindowModel {

    private final SimpleStringProperty welcomeText = new SimpleStringProperty("");

    private final SimpleStringProperty mousePosition = new SimpleStringProperty("");

    public String getWelcomeText() {
        return welcomeText.get();
    }

    public SimpleStringProperty welcomeTextProperty() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText.set(welcomeText);
    }

    public String getMousePosition() {
        return mousePosition.get();
    }

    public SimpleStringProperty mousePositionProperty() {
        return mousePosition;
    }

    public void setMousePosition(String mousePosition) {
        this.mousePosition.set(mousePosition);
    }
}
