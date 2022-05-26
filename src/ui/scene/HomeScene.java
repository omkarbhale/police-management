package ui.scene;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.SceneManager;

public class HomeScene extends Scene {
    public static HomeScene scene() {
        return new HomeScene(new BorderPane());
    }

    public HomeScene(BorderPane bp) {
        super(bp);

    }
}
