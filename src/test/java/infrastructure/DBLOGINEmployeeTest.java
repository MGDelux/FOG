package infrastructure;

import Repoistory.Employee.Exceptions.loginError;
import infrastructure.Database.DBEmployee;
import infrastructure.Database.Database;
import infrastructure.Exceptions.DBError;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 25-11-2020 - 12:51
 **/
public class DBLOGINEmployeeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testLogin() throws DBError, loginError, SQLException {
        Database db = new Database();
        DBEmployee employee = new DBEmployee(db);
        try {
            employee.login("mathias@mail.com");
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}