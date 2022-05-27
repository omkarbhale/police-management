package ui.scene;

import auth.Authentication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ui.SceneManager;

public class LoginScene extends Scene {
    Label title;
    Label loginHeading;
    Label usernameLabel;
    Label passLabel;

    TextField usernameField;
    TextField passField;

    Button loginButton;
    Button signupButton;

    public static LoginScene scene() {
        return new LoginScene(new BorderPane());
    }

    public LoginScene(BorderPane bp) {
        super(bp);

        initialize();

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(title);
        hb.setPadding(new Insets(30, 0, 0, 0));

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.TOP_CENTER);
        gp.setPadding(new Insets(50, 50, 50, 50));
        gp.setVgap(10);
        gp.setHgap(20);
        gp.add(loginHeading, 0, 0);
        gp.add(usernameLabel, 0, 1);
        gp.add(usernameField, 1, 1);
        gp.add(passLabel, 0, 2);
        gp.add(passField, 1, 2);
        gp.add(signupButton, 0, 3);
        gp.add(loginButton, 1, 3);
        gp.setStyle("-fx-background-color: #212121;");
        gp.setMaxHeight(200);
        gp.setMaxWidth(600);

        bp.setTop(hb);
        bp.setCenter(gp);

        getStylesheets().add("./ui/style.css");
    }

    private void initialize() {
        usernameLabel = new Label("Username:");

        passLabel = new Label("Password:");

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setOnKeyTyped(e -> {
            highlightRed(usernameField, false);
            highlightRed(passField, false);
        });
        usernameField.focusedProperty().addListener((obs, oldVal, newVal) -> highlightRed(usernameField, false));

        passField = new TextField();
        passField.setPromptText("Password");
        passField.setOnKeyTyped(e -> {
            highlightRed(usernameField, false);
            highlightRed(passField, false);
        });
        passField.focusedProperty().addListener((obs, oldVal, newVal) -> highlightRed(passField, false));

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> logIn(usernameField.getText(), passField.getText()) );

        signupButton = new Button("Sign up");

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(1);
        dropShadow.setOffsetY(1);


        //Adding text and DropShadow effect to it
        title = new Label("Police Management System");
        title.setFont(Font.font("Courier New", FontWeight.BOLD, 52));
        title.setEffect(dropShadow);

        loginHeading = new Label("Log in");
        loginHeading.setFont(Font.font(null, FontWeight.BOLD, 26));
    }

    private void logIn(String username, String password) {
        if(username.equals("")) {
            highlightRed(usernameField, true);
            return;
        }
        if(password.equals("")) {
            highlightRed(passField, true);
            return;
        }
        boolean isAuthenticated = Authentication.getInstance().authenticate(username, password);
        if(isAuthenticated) {
            SceneManager.getInstance().loadScene(HomeScene.scene());
            return;
        }
        highlightRed(usernameField, true);
        highlightRed(passField, true);
    }

    private void highlightRed(TextField field, boolean set) {
        if(set) {
            field.getStyleClass().add("highlight-red");
        } else {
            field.getStyleClass().remove("highlight-red");
        }
    }
}
