package domain.BOMX;

import Repoistory.Employee.Exceptions.EmployeeError;
import infrastructure.BOM.BomService;
import domain.BOM.Exceptions.BomException;
import domain.Carport.Carport;
import domain.Shed.Shed;
import infrastructure.Exceptions.DBError;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 17-12-2020 - 13:29
 **/
class BomServiceTest {

    @Test
    void newBom() throws EmployeeError, SQLException, BomException, DBError {
        BomService bomService = new BomService();
        Carport carport = new Carport(420,690, Carport.roofType.FLAT,10);
        Shed shed = new Shed(240,400);
        bomService.newBom(carport,shed);
    }
}