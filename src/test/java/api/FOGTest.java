package api;

import Repoistory.Employee.Exceptions.EmployeeError;
import Repoistory.Employee.Exceptions.loginError;
import infrastructure.DatabaseCarport.DBCarport;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 27-11-2020 - 12:17
 **/
public class FOGTest extends TestCase {

    public void testCreateAdminEmployee() throws EmployeeError, SQLException {
        Database db = new Database();
        try {
            FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db));
            FOG.createAdminEmployee("mathias@gmail.com", "password");
        } catch (EmployeeError e) {
            e.printStackTrace();
        }
    }

    public void testLogin() throws loginError, EmployeeError, SQLException {
        Database db = new Database();
        FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db));
        FOG.login("mathias@gmail.com", "password");
    }
}