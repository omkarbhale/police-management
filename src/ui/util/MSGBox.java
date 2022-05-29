package ui.util;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MSGBox {
    private MSGBox() {}

    public static void message(String title, String message) {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);

        Label label = new Label(message);
        Button button = new Button("Close");
        button.setOnAction(e -> stage.close() );

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
