import javafx.application.Application;
import javafx.stage.Stage;
import ui.scene.LoginScene;
import ui.SceneManager;

public class Main extends Application {

    SceneManager sceneManager;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager.getInstance().setStage(primaryStage);

        initializeStage(primaryStage);

        // Load login scene first
        SceneManager.getInstance().loadScene(LoginScene.scene());

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void initializeStage(Stage stage) {
        stage.setTitle("Police Management System");
    }
}
