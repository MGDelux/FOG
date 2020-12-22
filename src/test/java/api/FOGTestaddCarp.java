package api;

import domain.Materials.Materials;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import infrastructure.Exceptions.DBError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.MailService.MailService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CREATED BY Janus @ 2020-12-18 - 12:59
 **/
class FOGTestaddCarp {
    FOG FOG;

    @BeforeEach
    public void setupFOG () throws DBError {
        Database db = new Database();
        FOG = new FOG(new DBEmployee(db), new DBUser(db),  new MailService("hello"),new DBQueries(db), new DBMaterials(db));
    }
    @Test
    void addCarportMaterials() throws SQLException {
        Materials materials = new Materials(1,"smt træ", 17,2,"Det er  langt træ",2.24);
        FOG.addCarportMaterials(materials);
        FOG.deleteCarportMaterial(5);
    }
}