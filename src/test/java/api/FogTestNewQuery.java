package api;

import domain.Customers.Customers;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import infrastructure.Exceptions.DBError;
import org.junit.jupiter.api.Test;
import web.MailService.MailService;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 01-12-2020 - 13:42
 **/
public class FogTestNewQuery {

    @Test
    public void testNewQuery() throws SQLException, DBError { // run me for error
        Database db = new Database();
        Fog FOG = new Fog(new DBEmployee(db), new DBUser(db),  new MailService("hello"),new DBQueries(db), new DBMaterials(db));
        Customers testCustomers = new Customers(3,"mathias@gmail.com", 3400, "hillerød", "some where 24", 123432423);
    }
}