package auth;

import db.query.Condition;
import db.query.InsertQuery;
import db.query.SelectQuery;
import ui.SceneManager;
import ui.scene.LoginScene;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        SelectQuery selectQuery = new SelectQuery(
                "credentials",
                null,
                Condition.AND(
                        new Condition("username = '" + username.toLowerCase() + "'"),
                        new Condition("password = '" + hash(password) + "'")
                )
        );
        ResultSet rs;
        try {
            rs = selectQuery.execute();
            if(!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        this.username = username;
        this.password = password;
        this.isAuthenticated = true;
        return true;
    }

    public boolean createUser(String username, String password) {
        InsertQuery insertQuery = new InsertQuery(
                "credentials",
                List.of(username.toLowerCase(), hash(password))
        );
        try {
            insertQuery.execute();
            return true;
        } catch (SQLException e) {
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

    private String hash(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(bytes);
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
