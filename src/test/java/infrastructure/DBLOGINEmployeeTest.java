package infrastructure;

import Repoistory.Employee.Exceptions.loginError;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.Exceptions.DBError;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 25-11-2020 - 12:51
 **/
public class DBLOGINEmployeeTest {


    @Test
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