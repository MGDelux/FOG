package infrastructure;

import domain.Employees.Employee;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.Exceptions.DBError;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


/**
 * CREATED BY mathias @ 25-11-2020 - 12:42
 **/
public class DBGETEmployeeTest {


    @Test
    public void testGetAllEmployees() throws DBError, SQLException {
        Database db = new Database();
        DBEmployee dbEmployee = new DBEmployee(db);
        for (Employee employee: dbEmployee.getAllEmployees()){
            System.out.println(employee.toString());
        }
    }
}