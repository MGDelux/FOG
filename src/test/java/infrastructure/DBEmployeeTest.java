package infrastructure;

import domain.Employees.Employee;
import infrastructure.Database.DBEmployee;
import infrastructure.Database.Database;
import infrastructure.Exceptions.DBError;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 25-11-2020 - 12:28
 **/
public class DBEmployeeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateEmployee() throws DBError, SQLException {
        try {


            byte[] salt = Employee.genereateSalt();
            Employee employee1 = new Employee(Employee.Role.SALESMAN, 0, "mathias@mail.com", salt, Employee.calculateSecret(salt, "1234"));
            Employee employee2 = new Employee(Employee.Role.ADMIN, 1, "admin@mail.com", salt, Employee.calculateSecret(salt, "kage"));
            Database db = new Database();
            DBEmployee dbEmployee = new DBEmployee(db);
            dbEmployee.createEmployee(employee1);
            dbEmployee.createEmployee(employee2);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}