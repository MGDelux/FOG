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

import javax.mail.MessagingException;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 23-11-2020 - 15:46
 **/
public class FOG {
    private final EmployeeRepo employeeRepo;
    private final CustomerRepo customerRepo;
    private final EmailRepo emailRepo;
    private final QueriesRepo queriesRepo;
    private final MaterialsRepo materialRepo;
    //TODO: !Important FIGURE OUT WHAT MATS ECT WE NEED TO MAKE A CARPORT OUT OF 'WHAT' MATERIALS AND UPDATE SQL SCRIPT TO ACCOMMODATE THESE CHANGES IF NEEDED. and the required logic

    public FOG(EmployeeRepo employeeRepo, CustomerRepo customerRepo, EmailRepo emailRepo, QueriesRepo queriesRepo, MaterialsRepo materialRepo) {
        this.employeeRepo = employeeRepo;
        this.customerRepo = customerRepo;
        this.emailRepo = emailRepo;
        this.queriesRepo = queriesRepo;
        this.materialRepo = materialRepo;
    }

    /**
     * ^I cannot continue with the program until the SQL is fixed and i cannot figure it out ... the error is in our SQL script set - up via our constraints ^
     * -mbt
     */


    public boolean checkEmployeeEmail(String email) throws SQLException {//this is the same as checkIfNewCustomer lol
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
        return customerRepo.checkIfUsersIsInSystem(email);
    }

    /**
     * adds a costumer to the database if it does not exist already.
     *
     * @param email
     * @param zip
     * @param city
     * @param adress
     * @param phoneNr
     * @return
     * @throws SQLException
     */
    public Customers addCustomer(String email, int zip, String city, String adress, int phoneNr) throws SQLException {
        if (!checkIfNewCustomer(email)) { //checking if user is in our system by taking the mail and finding if it is in the db already if so just get the infomation instead of making it again
            int countIds = 1; //temp and basic way of assigning "id" to users (only in the app tho does not apply it db , db does it automaticly 'auto_increment')
            try {
                for (Customers customers : getAllUsers()) {
                    countIds++; //for every user we have in our db we count +1
                }
//TODO: Får vi nogensiden nullpointerException, hvorfor?, burde vi få det?
                /**yes if theres no db connection we will get a NPE
                 * -mbt*/
            } catch (NullPointerException e) {
                e.getMessage();
            }
            return customerRepo.addNewCustomer(new Customers(countIds, email, zip, city, adress, phoneNr));
        } else {
            return customerRepo.getExistingUserInfomation(email); //note ca. @10:11- what is more interesting? results will not surprise you: WoW > project
        }
    }

    public Customers getExistingUserInfomation(String email) throws SQLException {
        return customerRepo.getExistingUserInfomation(email);
    }

    public Iterable<Customers> getAllUsers() throws SQLException {
        return customerRepo.getAllUsers();
    }

    public Employee login(String email, String password) throws loginError, SQLException, EmployeeError {
        Employee tempEmployee = employeeRepo.login(email);
        if (tempEmployee.isPasswordCorrect(password)) {
            return tempEmployee;
        } else {
            throw new loginError("password incorrect");


        }
    }

    public void deleteQurey(int id) throws SQLException {
        queriesRepo.deleteOrderById(id);
    }

    public Customers getExistingCustomerInfomationById(int id) throws SQLException {
        return customerRepo.getExistingUserInfomationById(id);
    }

    public Queries newQuery(Customers customers, Carport carport, Shed shed) throws SQLException {
        return queriesRepo.newQuery(customers, carport, shed);
    }


    public Iterable<Queries> getAllQueries() {
        return queriesRepo.getAllQuires();
    }

    public void deleteQuery(int id) throws SQLException {
        queriesRepo.deleteOrderById(id);
    }

    public Email newMail(String toAdress, String subject, String message) throws MessagingException {
        return emailRepo.newEmail(new Email(toAdress, subject, message));
    }

    public Queries getLatestQuery() throws SQLException {
        return queriesRepo.getLatestQuery();
    }

    public Queries getQueryById(int id) throws SQLException {
        return queriesRepo.getSpecificQueryByQueryId(id);
    }


    public Queries assignSellerToQuery(int getQueryValue, Employee employee) {
        return queriesRepo.assignSellerToQuery(getQueryValue, employee);
    }

    public void updateMaterials(Materials materials, int id) throws SQLException {
        materialRepo.updateCarportMaterial(materials, id);
    }

    public void addCarportMaterials(Materials materials, int id) throws SQLException {
        materialRepo.addCarportMaterial(materials, id);
    }

    public void deleteCarportMaterial(int id) throws SQLException {
        materialRepo.deleteCarportMaterial(id);
    }

    public Iterable<Materials> getAllMaterials() {
        return  materialRepo.getAllMaterials();
     }
}
