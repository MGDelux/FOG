package infrastructure;

import domain.Employees.Employee;
import infrastructure.Exceptions.DBError;
import junit.framework.TestCase;

import java.sql.SQLException;


/**
 * CREATED BY mathias @ 25-11-2020 - 12:42
 **/
public class DBGETEmployeeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testGetAllEmployees() throws DBError, SQLException {
        Database db = new Database();
        DBEmployee dbEmployee = new DBEmployee(db);
        for (Employee employee: dbEmployee.getAllEmployees()){
            System.out.println(employee.toString());
        }
    }
}