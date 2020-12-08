package api;

import domain.Customers.Customers;
import infrastructure.DatabaseCarport.DBCarport;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import junit.framework.TestCase;
import web.MailService.MailService;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 30-11-2020 - 16:25
 **/
public class FOGTestCustomers extends TestCase {

    public void testAddCustomer() {
        Database db = new Database();
        try {
            FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db), new MailService(System.getenv("EMAIL_PASSWORD")));
            FOG.addCustomer("mathias@gmail.com", 3400, "hillerød", "some where 24", 123432423);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void testcheckMail() throws SQLException {
        Database db = new Database();

        FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db),new MailService(System.getenv("EMAIL_PASSWORD")));
        FOG.checkIfNewCustomer("mailx");

    }
    public void  testgetAll() throws SQLException {
        Database db = new Database();
        DBUser  dbUser = new DBUser(db);
        for (Customers customers : dbUser.getAllUsers()) {
            System.out.println(customers.toString());
        }
    }
    public void  testGetAll2() throws SQLException {
        Database db = new Database();
        FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db),new MailService(System.getenv("EMAIL_PASSWORD")));
        System.out.println(FOG.getAllUsers());
    }


}