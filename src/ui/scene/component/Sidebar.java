package ui.scene.component;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import ui.scene.HomeScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sidebar extends VBox {
    HomeScene parentScene;

    ImageView imageView;
    Button FIRButton;
    Button criminalButton;
    Button policeButton;
    Button selectedButton;

    public Sidebar(HomeScene scene) {
        super();

        parentScene = scene;

        FIRButton = createSidebarButton("FIR");
        criminalButton = createSidebarButton("Criminal");
        policeButton = createSidebarButton("Police");
        imageView = policeIcon();

        setPadding(new Insets(70, 10, 10, 10));
        setSpacing(10);
        getChildren().addAll(imageView, FIRButton, criminalButton, policeButton);
        setStyle("-fx-background-color: #292929");
    }

    private Button createSidebarButton(String text) {
        Button b = new Button(text);
        b.setMinWidth(250);
        b.setStyle("-fx-font-size: 28pt; ");
        b.setOnAction(e -> selectButton(b));
        return b;
    }

    private ImageView policeIcon() {
        try {
            Image image = new Image(new FileInputStream(".\\src\\ui\\police-icon.jpg"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(230);
            imageView.setFitHeight(230);
            return imageView;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearClasses() {
        FIRButton.getStyleClass().remove("selected");
        criminalButton.getStyleClass().remove("selected");
        policeButton.getStyleClass().remove("selected");
    }

    private void selectButton(Button b) {
        clearClasses();

        selectedButton = b;
        selectedButton.getStyleClass().add("selected");

        // Write code to change main content
        if(b == FIRButton) {

        } else if(b == criminalButton) {

        } else if(b == policeButton) {

        }
    }
}
