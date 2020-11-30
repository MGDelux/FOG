package api;

import domain.Users.User;
import infrastructure.DatabaseCarport.DBCarport;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 30-11-2020 - 16:25
 **/
public class FOGAPITestUser extends TestCase {

    public void testAddCustomer() {
        Database db = new Database();
        try {
            FOGAPI fogapi = new FOGAPI(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db));
            fogapi.addCustomer("mail", 3400, "hillerød", "some where 32", 123432423);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void  testgetAll() throws SQLException {
        Database db = new Database();
        DBUser  dbUser = new DBUser(db);
        for (User user : dbUser.getAllUsers()) {
            System.out.println(user.toString());
        }
    }
    public void  testGetAll2() throws SQLException {
        Database db = new Database();
        FOGAPI fogapi = new FOGAPI(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db));
        System.out.println(fogapi.getAllUsers());
    }

}