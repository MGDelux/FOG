package api;

import domain.Users.User;
import infrastructure.DatabaseCarport.DBCarport;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 01-12-2020 - 13:42
 **/
public class FOGTestNewQuery extends TestCase {

    public void testNewQuery() throws SQLException { // run me for error
        Database db = new Database();
        FOG FOG = new FOG(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db));
        User testUser = new User(0,"test@test.com",3400,"KBH","Who cares 42",53103241);
        FOG.newQuery(testUser,680,450,"flat",120,240);
    // !BLOCKER:! WILL PRODUCE ERROR: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`fog`.`forespørgsler`, CONSTRAINT `forespørgsler_ibfk_2` FOREIGN KEY (`ForeSpørglse_Id`) REFERENCES `forespørgsel_indhold` (`forespørgsel_Indhold_Id`))
    }
}