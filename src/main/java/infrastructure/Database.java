package infrastructure;

import Repoistory.DB.DBError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:49
 **/
public class Database {
    private static String URL = "jdbc:mysql://localhost/FOG?serverTimezone=CET";
    private static String USER = "FOG";
    private static String  PASS = "pass";
    public Database() throws DBError {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DBError(e.getMessage());
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    public void closeConnection() throws SQLException{
        DriverManager.getConnection(URL, USER, PASS).close();
    }
}