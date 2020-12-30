package web.pages;

import Repoistory.Employee.Exceptions.EmployeeError;
import domain.Employees.Employee;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 16-12-2020 - 14:26
 **/
@WebServlet({"/hackerman", "/hackerman/*"})

public class HackerMan extends BaseServlet {
    @Override
//temp should be deleted...
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        byte[] salt = Employee.genereateSalt();
        Employee employee1 = null;
        try {
            employee1 = new Employee(Employee.Role.SALESMAN, 0, "employee@mail.com", salt, Employee.calculateSecret(salt, "password"));
        } catch (EmployeeError employeeError) {
            employeeError.printStackTrace();
        }
        Employee employee2 = null;
        try {
            employee2 = new Employee(Employee.Role.ADMIN, 1, "admin@mail.com", salt, Employee.calculateSecret(salt, "password"));
        } catch (EmployeeError employeeError) {
            employeeError.printStackTrace();
        }
        assert employee1 != null;
        System.out.println(employee1.toString());
        assert employee2 != null;
        System.out.println(employee2.toString());
        Database db = new Database();
        DBEmployee dbEmployee = new DBEmployee(db);
        try {
            dbEmployee.createEmployee(employee1);
            dbEmployee.createEmployee(employee2);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
