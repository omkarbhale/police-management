package ui.scene;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.SceneManager;
import ui.scene.component.Sidebar;
import ui.scene.component.WelcomePane;

public class HomeScene extends Scene {
    BorderPane bp;
    public static HomeScene scene() {
        return new HomeScene(new BorderPane());
    }

    public HomeScene(BorderPane bp) {
        super(bp);

        bp.setLeft(new Sidebar(this));
        bp.setCenter(new WelcomePane());

        getStylesheets().add("./ui/style.css");
        this.bp=bp;
    }

    public void setContent(Node n) {
        bp.setCenter(n);
    }
}
