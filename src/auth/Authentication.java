package auth;

import ui.SceneManager;
import ui.scene.LoginScene;

public class Authentication {
    private static Authentication instance;

    public static Authentication getInstance() {
        if(Authentication.instance == null) {
            Authentication.instance = new Authentication();
        }
        return instance;
    }

    public String username;
    private String password;
    public boolean isAuthenticated;

    private Authentication() {
        username = null;
        password = null;
        isAuthenticated = false;
    }

    public boolean authenticate(String username, String password) {
        // Check for auth here
        if(true) {
            this.username = username;
            this.password = password;
            this.isAuthenticated = true;
            return true;
        } else {
            this.username = null;
            this.password = null;
            this.isAuthenticated = false;
            return false;
        }
    }

    public void logOut() {
        if(!isAuthenticated) return;
        isAuthenticated = false;
        this.username = null;
        this.password = null;
        SceneManager.getInstance().loadScene(LoginScene.scene());
    }
}
