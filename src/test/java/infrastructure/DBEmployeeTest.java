package infrastructure;

import Repoistory.Employee.Exceptions.EmployeeError;
import domain.Employees.Employee;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.Exceptions.DBError;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 25-11-2020 - 12:28
 **/
public class DBEmployeeTest  {


    @Test
    public void testCreateEmployee() throws DBError, SQLException {
        try {
            byte[] salt = Employee.genereateSalt();
            Employee employee1 = new Employee(Employee.Role.SALESMAN, 0, "employee@mail.com", salt, Employee.calculateSecret(salt, "password"));
            Employee employee2 = new Employee(Employee.Role.ADMIN, 1, "admin@mail.com", salt, Employee.calculateSecret(salt, "password"));
            Database db = new Database();
            DBEmployee dbEmployee = new DBEmployee(db);
            dbEmployee.createEmployee(employee1);
            dbEmployee.createEmployee(employee2);
        }catch (SQLException | EmployeeError e){
            System.out.println(e);
        }
    }

}