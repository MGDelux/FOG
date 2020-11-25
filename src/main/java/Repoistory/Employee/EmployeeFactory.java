package Repoistory.Employee;

import domain.Employee;

/**
 * CREATED BY mathi @ 25-11-2020 - 11:09
 **/
public interface EmployeeFactory {
    Employee createEmployee(Employee.Role role,int id, String email, byte[] salt, byte[] secret);

}
