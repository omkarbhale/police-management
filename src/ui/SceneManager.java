package ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;

    public static SceneManager getInstance() {
        if(SceneManager.instance == null) {
            SceneManager.instance = new SceneManager();
        }
        return SceneManager.instance;
    }

    public Scene currentScene;
    private Stage stage;

    private SceneManager() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadScene(Scene s) {
        currentScene = s;
        stage.hide();
        stage.setScene(s);
        stage.setMaximized(true);
        stage.show();
    }
}
