package domain.BOM;

import Repoistory.Bom.BomFactory;
import domain.BOM.Exceptions.BomException;
import domain.Carport.Carport;
import domain.Materials.Materials;
import domain.Shed.Shed;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseMaterials.DBMaterials;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CREATED BY mathias @ 17-12-2020 - 11:09
 **/
public class BomService implements BomFactory {
    private List<Materials> materials = new ArrayList<>();
    private List<Materials> BOM = new ArrayList<>();
    private Carport carport;
    private Shed shed;
    Database database = new Database();
    DBMaterials db = new DBMaterials(database);
//DETTE ER DEN MEST GHETTO WAY TO DO THIS OWO

    @Override
    public List<Materials> newBom(Carport carport, Shed shed) throws SQLException, BomException {
        materials.addAll((Collection<? extends Materials>) db.getAllMaterials());
        if (carport == null || shed == null) {
            //throw exception
            throw new BomException("carport / shed was null");
        }
        SetInfomation(carport, shed);
        if (carport.getRoof() == Carport.roofType.ANGLE) {
            calcuateAngleRoofParts();
        } else {
            calculateFlatRoofParts();
        }
        calucateCarportMaterials();
        calculateShedMaterials();
        return BOM;
    }

    private void calculateShedMaterials() {
    }

    public void calucateCarportMaterials()  {
        //stopler aka poles
        int poleCounter = 4;
        int extraPoles = 0;

        if (carport.getLength() <= 480) {
            poleCounter = 4;
        } else {
            for (int i = 240; i < carport.getLength(); i = i + 240) {
                extraPoles++;
            }
            poleCounter = poleCounter + extraPoles;
        }
        Materials pole = new Materials(1, "Trykimprægneret Stolpe", 300, poleCounter, "k. Stolper nedgraves 90 cm. i jord\t+ skråstiver", 60);
        BOM.add(pole);
        int rafter = 0;
        for (int i = 60; i <carport.getLength(); i = i + 60){
            rafter++;
        }
        Materials rafters = new Materials(2, "Spær", carport.getWidth(), rafter, "byg-selv spær (skal samles)", 60);
        BOM.add(rafters);

    }

    private void calculateFlatRoofParts() {

    }

    private void calcuateAngleRoofParts() {
    }

    private void SetInfomation(Carport carport, Shed shed) {
        this.carport = carport;
        this.shed = shed;

    }

}
