package auth;

public class Authentication {
    private static Authentication instance;

    public static Authentication instance() {
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
        System.out.println("Trying with: "+username+" "+password);
        // Check for auth here
        if(false) {
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
}
