package ui.scene.component;

import auth.Authentication;
import javafx.geometry.Insets;
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
    Button logoutButton;

    // Holds one of the above button
    Button selectedButton;

    public Sidebar(HomeScene scene) {
        super();

        parentScene = scene;

        FIRButton = createSidebarButton("FIR");
        criminalButton = createSidebarButton("Criminal");
        policeButton = createSidebarButton("Police");
        logoutButton = createSidebarButton("Log out");

        // Button event handlers
        FIRButton.setOnAction(e -> {
            selectButton(FIRButton);
            parentScene.setContent(new FIRPane());
        });
        criminalButton.setOnAction(e -> {
            selectButton(criminalButton);
        });
        policeButton.setOnAction(e -> {
            selectButton(policeButton);
        });
        logoutButton.setOnAction(e -> logOut() );

        imageView = policeIcon();

        setPadding(new Insets(70, 10, 10, 10));
        setSpacing(10);
        getChildren().addAll(imageView, FIRButton, criminalButton, policeButton, logoutButton);
        setStyle("-fx-background-color: #292929");
    }

    private Button createSidebarButton(String text) {
        Button b = new Button(text);
        b.setMinWidth(250);
        b.setStyle("-fx-font-size: 28pt; ");
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
    }

    private void logOut() {
        System.out.println("Logging out");
        Authentication.getInstance().logOut();
    }
}
