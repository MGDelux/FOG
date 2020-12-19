package infrastructure.DatabaseMaterials;

import domain.Materials.Materials;
import infrastructure.DatabaseConnector.Database;
import infrastructure.Exceptions.DBError;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CREATED BY mathias @ 17-12-2020 - 12:14
 **/
class DBMaterialsTest {

    @Test
    void getMaterial() throws SQLException, DBError {
        List<Materials> x = new ArrayList<>();
        Database database = new Database();
        DBMaterials dbMaterials = new DBMaterials(database);
        x.addAll(dbMaterials.getMaterial());
        for (Materials m :x){
            if (m.toString().contains("trykimprægneret Stolpe")){
                System.out.println("POGGIES " +m);
            }
        }
    }
}