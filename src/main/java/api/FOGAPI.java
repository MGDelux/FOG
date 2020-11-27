package api;

import Repoistory.Carporte.CarpoteRepo;
import Repoistory.Materials.MaterialsRepo;
import Repoistory.Queries.QueriesRepo;
import domain.Queries.Queries;
import Repoistory.Employee.EmployeeRepo;
import Repoistory.User.UserRepo;
import domain.Employees.Employee;
import domain.Users.User;
import Repoistory.Employee.Exceptions.loginError;

import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:46
 **/
public class FOGAPI {
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;
    private final CarpoteRepo carpoteRepo;
    private final MaterialsRepo materialsRepo;
    private final QueriesRepo queriesRepo;

    public FOGAPI(EmployeeRepo employeeRepo, UserRepo userRepo, CarpoteRepo carpoteRepo, MaterialsRepo materialsRepo, QueriesRepo queriesRepo) {
        this.employeeRepo = employeeRepo;
        this.userRepo = userRepo;
        this.carpoteRepo = carpoteRepo;
        this.materialsRepo = materialsRepo;
        this.queriesRepo = queriesRepo;
    }


    public  Employee createSalesManEmployee(String email, String password) throws SQLException {
        byte[] salt = Employee.genereateSalt();
        return employeeRepo.createEmployee(new Employee(Employee.Role.SALESMAN,0,email,salt, Employee.calculateSecret(salt,password)));
    }
    public  Employee  createAdminEmployee(String email, String password) throws SQLException {
        byte[] salt = Employee.genereateSalt();
        return employeeRepo.createEmployee(new Employee(Employee.Role.ADMIN,0,email,salt, Employee.calculateSecret(salt,password)));
    }

    //WIP
    public  User addCustomer(String email, int zip, String city, String adress, int phoneNr) {
       return userRepo.addNewCustomer(new User(0,email,zip,city,adress,phoneNr));
    }

    public Employee login(String email, String password) throws loginError, SQLException {
       Employee tempEmployee = employeeRepo.login(email);
       if (tempEmployee.isPasswordCorrect(password)){
           return tempEmployee;
       }else {
           throw new loginError("password incorrect");

       }
    }
    public Queries newQuery(User user, int carPortWidth, int cartPortLength, String roofType, int shedWidth, int shedLength) throws SQLException {
        return queriesRepo.newQuery(user, carPortWidth, cartPortLength, roofType, shedWidth, shedLength);
    }
}
