package ui.scene;

import auth.Authentication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.SceneManager;
import ui.util.MSGBox;

public class SignUpScene extends Scene {
    Label title;
    Label loginHeading;
    Label usernameLabel;
    Label passLabel1;
    Label passLabel2;

    TextField usernameField;
    PasswordField passField1;
    PasswordField passField2;

    Button loginButton;
    Button signupButton;

    public static SignUpScene scene() {
        return new SignUpScene(new BorderPane());
    }

    public SignUpScene(BorderPane bp) {
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
        gp.add(passLabel1, 0, 2);
        gp.add(passField1, 1, 2);
        gp.add(passLabel2, 0, 3);
        gp.add(passField2, 1, 3);
        gp.add(loginButton, 0, 4);
        gp.add(signupButton, 1, 4);
        gp.setStyle("-fx-background-color: #212121;");
        gp.setMaxHeight(200);
        gp.setMaxWidth(600);

        bp.setTop(hb);
        bp.setCenter(gp);

        getStylesheets().add("./ui/style.css");
    }

    private void initialize() {
        usernameLabel = new Label("Username:");
        passLabel1 = new Label("Password:");
        passLabel2 = new Label("Repeat password:");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passField1 = new PasswordField();
        passField1.setPromptText("Password");
        passField2 = new PasswordField();
        passField2.setPromptText("Again :)");

        loginButton = new Button("Log in instead");
        loginButton.setOnAction(e -> logIn() );

        signupButton = new Button("Sign up");
        signupButton.setOnAction(e -> signUp(usernameField.getText(), passField1.getText(), passField2.getText()));

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

    private void logIn() {
        SceneManager.getInstance().loadScene(LoginScene.scene());
    }

    private  void signUp(String username, String pass1, String pass2) {
        if(username.equals("") || pass1.equals("") || pass2.equals("")) {
            MSGBox.message("Error", "Fields cannot be empty");
            return;
        }
        if(!pass1.equals(pass2)) {
            MSGBox.message("Error", "Passwords do not match!");
            return;
        }
        if(Authentication.getInstance().createUser(username, pass1)) {
            MSGBox.message("Success", "Created successfully. Try log in now");
            clearFields();
        } else {
            MSGBox.message("Error", "Some error occurred");
        }
    }

    private void clearFields() {
        usernameField.setText(null);
        passField1.setText(null);
        passField2.setText(null);
    }
}
