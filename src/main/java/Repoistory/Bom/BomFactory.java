package Repoistory.Bom;
import Repoistory.Employee.Exceptions.EmployeeError;
import domain.BOM.Exceptions.BomException;
import domain.Carport.Carport;
import domain.Materials.Materials;
import domain.Shed.Shed;
import java.sql.SQLException;
import java.util.List;

/**
 * CREATED BY mathias @ 17-12-2020 - 11:12
 **/
public interface BomFactory {
    List<Materials> newBom(Carport carport, Shed shed) throws EmployeeError, SQLException, BomException;
}
