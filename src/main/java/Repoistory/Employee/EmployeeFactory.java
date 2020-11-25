package Repoistory.Employee;

import domain.Employee;

import java.sql.SQLException;

/**
 * CREATED BY mathi @ 25-11-2020 - 11:09
 **/
public interface EmployeeFactory {
    Employee createEmployee(Employee employee) throws SQLException;

}
