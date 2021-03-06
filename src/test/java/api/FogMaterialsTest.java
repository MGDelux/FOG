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
import infrastructure.MailService.MailService;

import java.sql.SQLException;

/**
 * CREATED BY Janus @ 2020-12-18 - 11:58
 **/
class FogMaterialsTest {
    Fog FOG;

    @BeforeEach
    public void setupFOG () throws DBError {
        Database db = new Database();
        FOG = new Fog(new DBEmployee(db), new DBUser(db),  new MailService("hello"),new DBQueries(db), new DBMaterials(db));
    }

    @Test
    void updateMaterials() throws SQLException {
        Materials materials = new Materials(1,"impregneret træ", 25,4,"Det er træ",25.55);
        FOG.updateMaterials(materials,1);
    }

}