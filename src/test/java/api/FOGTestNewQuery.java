package api;

import domain.Customers.Customers;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import org.junit.jupiter.api.Test;
import web.MailService.MailService;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 01-12-2020 - 13:42
 **/
public class FOGTestNewQuery {

    @Test
    public void testNewQuery() throws SQLException { // run me for error
        Database db = new Database();
        FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBQueries(db), new MailService(System.getenv("EMAIL_PASSWORD")));
        Customers testCustomers = new Customers(3,"mathias@gmail.com", 3400, "hillerød", "some where 24", 123432423);
    }
}