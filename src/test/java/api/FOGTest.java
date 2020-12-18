package api;

import Repoistory.Employee.Exceptions.EmployeeError;
import Repoistory.Employee.Exceptions.loginError;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import web.MailService.MailService;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 27-11-2020 - 12:17
 **/
class FOGTest {

    FOG FOG;

    @BeforeEach
    public void setupFOG () {
        Database db = new Database();
        FOG = new FOG(new DBEmployee(db), new DBUser(db),  new MailService("hello"),new DBQueries(db), new DBMaterials(db));

    }


    @Disabled("Disabled until test db setup")
    @Test
    void testCreateAdminEmployee() throws EmployeeError, SQLException {
        try {
            FOG.createAdminEmployee("mathias@gmail.com", "password");
        } catch (EmployeeError e) {
            e.printStackTrace();
        }
    }

    @Disabled("Disabled until test db setup")
    @Test
    void testLogin() throws loginError, EmployeeError, SQLException {
        FOG.login("mathias@gmail.com", "password");
    }
}