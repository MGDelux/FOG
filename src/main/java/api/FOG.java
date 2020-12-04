package api;
import Repoistory.Carporte.CarpoteRepo;
import Repoistory.Email.EmailRepo;
import Repoistory.Employee.Exceptions.EmployeeError;
import Repoistory.Materials.MaterialsRepo;
import Repoistory.Queries.QueriesRepo;
import domain.Email.Email;
import domain.Queries.Queries;
import Repoistory.Employee.EmployeeRepo;
import Repoistory.User.UserRepo;
import domain.Employees.Employee;
import domain.Users.User;
import Repoistory.Employee.Exceptions.loginError;

import javax.mail.MessagingException;
import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:46
 **/
public class FOG {
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;
    private final CarpoteRepo carpoteRepo;
    private final MaterialsRepo materialsRepo;
    private final EmailRepo emailRepo;
    private final QueriesRepo queriesRepo;
    //TODO: LOCKER<< FIX SQL SCRIPT SO WE CAN ENTER QURIES INTO DB WITH OUT CONSTRAINT ERRORS: -> CHECK / RUN FOGTestNewQuery TEST for error (TEST>API>FOGTestNewQuery>public void testNewQuery() )
    //TODO: !Important FIGURE OUT WHAT MATS ECT WE NEED TO MAKE A CARPORT OUT OF 'WHAT' MATERIALS AND UPDATE SQL SCRIPT TO ACCOMMODATE THESE CHANGES IF NEEDED. and the required logic
    /**  ^I cannot continue with the program until the SQL is fixed and i cannot figure it out ... the error is in our SQL script set - up via our constraints ^
     * -mbt */

    public FOG(EmployeeRepo employeeRepo, UserRepo userRepo, CarpoteRepo carpoteRepo, MaterialsRepo materialsRepo, QueriesRepo queriesRepo, EmailRepo emailRepo) {
        this.employeeRepo = employeeRepo;
        this.userRepo = userRepo;
        this.carpoteRepo = carpoteRepo;
        this.materialsRepo = materialsRepo;
        this.queriesRepo = queriesRepo;
        this.emailRepo = emailRepo;
    }

    public boolean checkEmployeeEmail(String email) throws SQLException
     {
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
                /**yes if theres no db connection we will get a NPE
                 * -mbt*/
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

    public Email newMail(String toAdress, String subject, String message) throws MessagingException {
        return emailRepo.newEmail(new Email(toAdress,subject,message));
    }
}