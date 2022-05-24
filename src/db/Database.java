package db;

import java.sql.*;

public class Database {

    /*******************************************
     * Singleton code
    *******************************************/
    private static Database instance = null;
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }


    /*******************************************
     * Instance variables
     *******************************************/

    private Connection connection;


    /*******************************************
     * Constructor and methods
     *******************************************/

    private Database() {
        connect();
    }

    private void connect() {
        /*******************************************
         * Database constants
         *******************************************/
        final String URL = "jdbc:mysql://localhost:3306/";
        final String DBNAME = "policedb";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        // Make connection
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery(String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
