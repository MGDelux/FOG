package infrastructure.DatabaseConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 23-11-2020 - 15:49
 **/
public class Database {
    private final static String URL = "jdbc:mysql://localhost/FOG?serverTimezone=CET";
    private final static String USER = "FOG";
    private final static String  PASS = "xY1xf45c1#";
    public Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException().toString());
        }
    }
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    public void closeConnection() throws SQLException{
        DriverManager.getConnection(URL, USER, PASS).close();
    }
}