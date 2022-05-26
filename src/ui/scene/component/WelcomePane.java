package ui.scene.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WelcomePane extends BorderPane {

    public WelcomePane() {
        super();
        Label message = new Label("Welcome to Police Management");
        message.setFont(Font.font("", FontWeight.BOLD, 32));
        setCenter(message);
    }
}
