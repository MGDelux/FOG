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
import org.junit.Before;
import web.MailService.MailService;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 27-11-2020 - 12:17
 **/
public class FOGTest extends TestCase {

    FOG FOG;

    @Before
    public void setupFOG () {
        Database db = new Database();
        FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db), new MailService("hello"));
    }

    public void testCreateAdminEmployee() throws EmployeeError, SQLException {
        try {
            FOG.createAdminEmployee("mathias@gmail.com", "password");
        } catch (EmployeeError e) {
            e.printStackTrace();
        }
    }

    public void testLogin() throws loginError, EmployeeError, SQLException {
        FOG.login("mathias@gmail.com", "password");
    }
}