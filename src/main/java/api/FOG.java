package api;

import Repoistory.Customer.CustomerRepo;
import Repoistory.Email.EmailRepo;
import Repoistory.Employee.Exceptions.EmployeeError;
import Repoistory.Materials.MaterialsRepo;
import Repoistory.Queries.QueriesRepo;
import domain.Carport.Carport;
import domain.Customers.Customers;
import domain.Email.Email;
import domain.Materials.Materials;
import domain.Queries.Queries;
import Repoistory.Employee.EmployeeRepo;
import domain.Employees.Employee;
import Repoistory.Employee.Exceptions.loginError;
import domain.Shed.Shed;
import infrastructure.DatabaseUser.Execptions.CustomerExecption;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

/**
 * CREATED BY mathias @ 23-11-2020 - 15:46
 * API should handle all our methods from Servlet(s)
 **/
public class FOG {
    private final EmployeeRepo employeeRepo;
    private final CustomerRepo customerRepo;
    private final EmailRepo emailRepo;
    private final QueriesRepo queriesRepo;
    private final MaterialsRepo materialRepo;

    //Constructor -
    public FOG(EmployeeRepo employeeRepo, CustomerRepo customerRepo, EmailRepo emailRepo, QueriesRepo queriesRepo, MaterialsRepo materialRepo) {
        this.employeeRepo = employeeRepo;
        this.customerRepo = customerRepo;
        this.emailRepo = emailRepo;
        this.queriesRepo = queriesRepo;
        this.materialRepo = materialRepo;
    }

    /**
     * Creates a Employee with the Salesman role
     *
     * @param email employees email
     * @param password employees selected password
     * @throws SQLException  sql
     * @throws EmployeeError employee
     */
    public void createSalesManEmployee(String email, String password) throws SQLException, EmployeeError {
        byte[] salt = Employee.genereateSalt(); // magic space code stuff
        employeeRepo.createEmployee(new Employee(Employee.Role.SALESMAN, 0, email, salt, Employee.calculateSecret(salt, password)));
    }

    /**
     * Creates a Employee with the admin role
     *
     * @param email employees email
     * @param password  employees selected password
     * @throws SQLException exception
     * @throws EmployeeError exception
     */
    public void createAdminEmployee(String email, String password) throws SQLException, EmployeeError {
        byte[] salt = Employee.genereateSalt(); // magic space code stuff
        employeeRepo.createEmployee(new Employee(Employee.Role.ADMIN, 0, email, salt, Employee.calculateSecret(salt, password)));
    }

    /**
     * Checks if the Customer is in the database
     *
     * @param email email
     * @return domain.Customers
     * @throws SQLException exception
     */

    public boolean checkIfNewCustomer(String email) throws SQLException {
        return customerRepo.checkIfUsersIsInSystem(email);
    }

    /**
     * adds a Customer to the database if it does not exist already.
     *
     * @param email Customers email
     * @param zip Customers zip
     * @param city Customers city
     * @param adress Customers adress
     * @param phoneNr Customers phonenr
     * @return domain.Customers
     * @throws SQLException exception
     */
    public Customers addCustomer(String email, int zip, String city, String adress, int phoneNr) throws SQLException {
        if (!checkIfNewCustomer(email)) { //checking if user is in our system by taking the mail and finding if it is in the db already if so just get the infomation instead of making it again
            int countIds = 1; //temp and basic way of assigning "id" to users (only in the app tho does not apply it db , db does it automaticly 'auto_increment')
            try {
                for (Customers ignored : getAllUsers()) {
                    countIds++; //for every user we have in our db we count +1
                }
            } catch (CustomerExecption e) {
                throw new CustomerExecption("error: " + e);
            }
            return customerRepo.addNewCustomer(new Customers(countIds, email, zip, city, adress, phoneNr));
        } else {
            return customerRepo.getExistingUserInfomation(email); //basic implementation should replace adress phone nr ect if new, might be the same custemor but at a new adress
        }
    }

    /**
     * Gets user infomation based on the email
     *
     * @param email Customers email
     * @return domain.Customers
     * @throws SQLException exception
     */
    public Customers getExistingCustomerInfomation(String email) throws SQLException {
        return customerRepo.getExistingUserInfomation(email);
    }

    /**
     * Returns all users in the database
     *
     * @return Iterable<Customers>
     * @throws SQLException exception
     */
    public Iterable<Customers> getAllUsers() throws SQLException {
        return customerRepo.getAllUsers();
    }

    /**
     * logs a user in if password is correct via the database.
     *
     * @param email entered email
     * @param password entered email
     * @return Employee
     * @throws loginError, SQLException, EmployeeError
     */
    public Employee login(String email, String password) throws loginError, SQLException, EmployeeError {
        Employee tempEmployee = employeeRepo.login(email);
        if (tempEmployee.isPasswordCorrect(password)) {
            return tempEmployee;
        } else {
            throw new loginError("password incorrect");


        }
    }

    /**
     * Adds a new Query (forespørgelse) into the database
     *
     * @param customers customer infomation
     * @param carport carport infomation
     * @param shed shed infomation
     * @throws SQLException, sql
     */
    public void newQuery(Customers customers, Carport carport, Shed shed) throws SQLException {
        queriesRepo.newQuery(customers, carport, shed);
    }

    /**
     * gets all Queries from the DB
     *
     * @return Iterable<Queries>
     */
    public Iterable<Queries> getAllQueries() {
        return queriesRepo.getAllQuires();
    }

    /**
     * Deletes db query based on its Id.
     *
     * @param id entered id
     * @throws SQLException exception
     */
    public void deleteQuery(int id) throws SQLException {
        queriesRepo.deleteOrderById(id);
    }

    /**
     * Sends a email based on the parameters
     *
     * @param toAdress reciver of email
     * @param subject  subject of mail
     * @param message  message of mail
     * @return Email
     * @throws MessagingException exception
     */
    public Email newMail(String toAdress, String subject, String message) throws MessagingException {
        return emailRepo.newEmail(new Email(toAdress, subject, message));
    }

    /**
     * gets the most recent Query
     *
     * @return Queries
     * @throws SQLException exception
     */
    public synchronized Queries getLatestQuery() throws SQLException {
        return queriesRepo.getLatestQuery();
    }

    /**
     * returns query based on id
     *
     * @param id entered id
     * @return Queries
     * @throws SQLException exception
     */
    public Queries getQueryById(int id) throws SQLException {
        return queriesRepo.getSpecificQueryByQueryId(id);
    }

    /**
     * Assigns seller to a query based on the parameters
     *
     * @param getQueryValue selected id
     * @param employee      employee that selected the id
     */
    public void assignSellerToQuery(int getQueryValue, Employee employee) {
         queriesRepo.assignSellerToQuery(getQueryValue, employee);
    }

    /**
     * updates specific material
     *
     * @param materials material infomation
     * @param id        id of db collum
     * @throws SQLException exception
     */
    public void updateMaterials(Materials materials, int id) throws SQLException {
        materialRepo.updateCarportMaterial(materials, id);
    }

    /**
     * updates specific "carport" material
     *
     * @param materials material infomation
     * @param id        id of db collum
     * @throws SQLException exception
     */
    public void addCarportMaterials(Materials materials, int id) throws SQLException {
        materialRepo.addCarportMaterial(materials, id);
    }

    /**
     * deletes specific "carport" material
     *
     * @param id id of db collum
     * @throws SQLException exception
     */
    public void deleteCarportMaterial(int id) throws SQLException {
        materialRepo.deleteCarportMaterial(id);
    }

    /**
     * gets all materials from the db
     *
     * @return Iterable<Materials>
     */
    public Iterable<Materials> getAllMaterials() {
        return materialRepo.getAllMaterials();
    }

    /**
     * ypdates specific query based on id
     * @param id query id
     * @param carport carport infomation
     * @param shed shed infomation
     */
    public void updateQuery(int id, Carport carport, Shed shed) {
        queriesRepo.updateQuery(id, carport, shed);
    }

    /**
     * ypdates specific material based on id
     * @param id id in db
     * @param materials material infomation
     * @throws SQLException exception
     */
    public void updateFittingsAndScrews(Materials materials, int id) throws SQLException {
        materialRepo.updateFittingsAndScrews(materials, id);
    }
    /**
     * adds specific material.
     * @param id id to db
     * @param materials  material infomation
     * @throws SQLException exception
     */
    public void addFittingsAndScrews(Materials materials, int id) throws SQLException {
        materialRepo.addFittingsAndScrews(materials, id);
    }
    /**
     * deletes specific material based on id
     * @param id db id
     * @throws SQLException exception
     */
    public void deleteFittingsAndScrews(int id) throws SQLException {
        materialRepo.deleteFittingsAndScrews(id);
    }
    /**
     * gets all querys based on email
     * @param email customer email
     * @return Iterable<Materials>
     * @throws SQLException exception
     */
    public List<Queries> getQueryByEmail(String email) throws SQLException {
        return queriesRepo.getQueryByEmail(email);
    }
    /**
     * gets all mertials "screws"
     * @return Iterable<Materials>
     */
    public Iterable<Materials> findScrews() {
        return materialRepo.getAllScrews();
    }
}
