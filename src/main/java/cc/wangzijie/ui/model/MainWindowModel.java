package cc.wangzijie.ui.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

@Component
public class MainWindowModel {

    private final ObjectProperty<Image> mainWindowLogoImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> closeWindowButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> maximizeWindowButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> minimizeWindowButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> openSettingsWindowButtonImage = new SimpleObjectProperty<>();

    private final ObjectProperty<Image> reloadMainWindowButtonImage = new SimpleObjectProperty<>();

    private final SimpleStringProperty welcomeText = new SimpleStringProperty("");

    private final SimpleStringProperty mousePosition = new SimpleStringProperty("");

    public Image getMainWindowLogoImage() {
        return mainWindowLogoImage.get();
    }

    public ObjectProperty<Image> mainWindowLogoImageProperty() {
        return mainWindowLogoImage;
    }

    public void setMainWindowLogoImage(Image mainWindowLogoImage) {
        this.mainWindowLogoImage.set(mainWindowLogoImage);
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

    public Image getMaximizeWindowButtonImage() {
        return maximizeWindowButtonImage.get();
    }

    public ObjectProperty<Image> maximizeWindowButtonImageProperty() {
        return maximizeWindowButtonImage;
    }

    public void setMaximizeWindowButtonImage(Image maximizeWindowButtonImage) {
        this.maximizeWindowButtonImage.set(maximizeWindowButtonImage);
    }

    public Image getMinimizeWindowButtonImage() {
        return minimizeWindowButtonImage.get();
    }

    public ObjectProperty<Image> minimizeWindowButtonImageProperty() {
        return minimizeWindowButtonImage;
    }

    public void setMinimizeWindowButtonImage(Image minimizeWindowButtonImage) {
        this.minimizeWindowButtonImage.set(minimizeWindowButtonImage);
    }

    public Image getOpenSettingsWindowButtonImage() {
        return openSettingsWindowButtonImage.get();
    }

    public ObjectProperty<Image> openSettingsWindowButtonImageProperty() {
        return openSettingsWindowButtonImage;
    }

    public void setOpenSettingsWindowButtonImage(Image openSettingsWindowButtonImage) {
        this.openSettingsWindowButtonImage.set(openSettingsWindowButtonImage);
    }

    public Image getReloadMainWindowButtonImage() {
        return reloadMainWindowButtonImage.get();
    }

    public ObjectProperty<Image> reloadMainWindowButtonImageProperty() {
        return reloadMainWindowButtonImage;
    }

    public void setReloadMainWindowButtonImage(Image reloadMainWindowButtonImage) {
        this.reloadMainWindowButtonImage.set(reloadMainWindowButtonImage);
    }

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
