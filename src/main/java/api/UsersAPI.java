package api;

import infrastructure.Exceptions.DBError;
import Repoistory.Employee.EmployeeRepo;
import Repoistory.User.UserRepo;
import domain.Employees.Employee;
import domain.Users.User;
import Repoistory.Employee.Exceptions.loginError;
import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:46
 **/
public class UsersAPI {
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;

    public UsersAPI(EmployeeRepo employeeRepo, UserRepo userRepo){
        this.employeeRepo = employeeRepo;
        this.userRepo = userRepo;
    }

    public Employee createSalesManEmployee(String email, String password) throws SQLException {
        byte[] salt = Employee.genereateSalt();
        return employeeRepo.createEmployee(new Employee(Employee.Role.SALESMAN,0,email,salt, Employee.calculateSecret(salt,password)));
    }
    public Employee  createAdminEmployee(String email, String password) throws SQLException {
        byte[] salt = Employee.genereateSalt();
        return employeeRepo.createEmployee(new Employee(Employee.Role.ADMIN,0,email,salt, Employee.calculateSecret(salt,password)));
    }


    //WIP
    public User AddCustomer(String email, int zip, String city, String adress, int phoneNr) {
       return userRepo.createUser(new User(0,email,zip,city,adress,phoneNr));
    }

    public Employee login(String email) throws DBError, loginError, SQLException {
       return employeeRepo.login(email);
    }
}
