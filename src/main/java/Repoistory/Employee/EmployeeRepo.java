package Repoistory.Employee;

import domain.Employee;
import domain.loginError;

import java.sql.SQLException;

/**
 * CREATED BY mathi @ 25-11-2020 - 11:09
 **/
public interface EmployeeRepo extends EmployeeFactory {
    Iterable<Employee> getAllEmployees() throws SQLException;
    boolean checkMail(String mail) throws SQLException;
    Employee login(String email) throws loginError, SQLException;
}
