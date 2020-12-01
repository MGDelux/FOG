package api;
import Repoistory.Carporte.CarpoteRepo;
import Repoistory.Employee.Exceptions.EmployeeError;
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

    public boolean checkEmployeeEmail(String email) throws SQLException {
        return employeeRepo.checkMail(email);
    }

    public Employee createSalesManEmployee(String email, String password) throws SQLException, EmployeeError {
        byte[] salt = Employee.genereateSalt();
        return employeeRepo.createEmployee(new Employee(Employee.Role.SALESMAN, 0, email, salt, Employee.calculateSecret(salt, password)));
    }

    public Employee createAdminEmployee(String email, String password) throws SQLException, EmployeeError {
        byte[] salt = Employee.genereateSalt(); // magic space code stuff
        return employeeRepo.createEmployee(new Employee(Employee.Role.ADMIN, 0, email, salt, Employee.calculateSecret(salt, password)));
    }

    //WIP
    public boolean checkIfNewCustomer(String email) throws SQLException {
        return userRepo.checkIfUsersIsInSystem(email);
    }

    /**
     * adds a costumer to the database if it does not exist already.
     * @param email
     * @param zip
     * @param city
     * @param adress
     * @param phoneNr
     * @return
     * @throws SQLException
     */
    public User addCustomer(String email, int zip, String city, String adress, int phoneNr) throws SQLException {
        if (!checkIfNewCustomer(email)) { //checking if user is in our system by taking the mail and finding if it is in the db already if so just get the infomation instead of making it again
            int countIds = 1; //temp and basic way of assigning "id" to users (only in the app tho does not apply it db , db does it automaticly 'auto_increment')
            try {
                for (User user : getAllUsers()) {
                    countIds++; //for every user we have in our db we count +1
                }
//TODO: Får vi nogensiden nullpointerException, hvorfor?, burde vi få det?
            } catch (NullPointerException e) {
                e.getMessage();
            }
            return userRepo.addNewCustomer(new User(countIds, email, zip, city, adress, phoneNr));
        }
        else {
            return userRepo.getExistingUserInfomation(email); //note ca. @10:11- what is more interesting? results will not surprise you: WoW > project
        }
    }

    public Iterable<User> getAllUsers() throws SQLException {
            return userRepo.getAllUsers();
    }

    public Employee login(String email, String password) throws loginError, SQLException, EmployeeError {
        Employee tempEmployee = employeeRepo.login(email);
        if (tempEmployee.isPasswordCorrect(password)) {
            return tempEmployee;
        } else {
            throw new loginError("password incorrect");

        }
    }

    public Queries newQuery(User user, int carPortWidth, int cartPortLength, String roofType, int shedWidth, int shedLength) throws SQLException {
        return queriesRepo.newQuery(user, carPortWidth, cartPortLength, roofType, shedWidth, shedLength);
    }

    public Queries getQuery(int id) throws SQLException {
        return queriesRepo.getSpecificQuire(id);
    }

    public Iterable<Queries> getAllQueries() {
        return queriesRepo.getAllQuires();
    }

    public void deleteQurey(int id) {
        queriesRepo.deleteSpecificQuire(id);
    }
}
