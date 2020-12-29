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
//DETTE ER DEN MEST GHETTO WAY TO DO THIS BUT TIME IS RUNNING OUT so.. yeah

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
        calculateCarportMaterials();
        calculateShedMaterials();
        setMinimumScrews();
        return BOM;
    }

    private void setMinimumScrews() {
        int screws = 0;
        for (int i = 0; i < carport.getLength(); i = i + 360) {
            screws++;

        }
        screws = screws * 8;
        Materials lengthScrews = new Materials(13, "Skruer", 0, screws, "Pakke skruer", 120);
        BOM.add(lengthScrews);
    }

    private void calculateShedMaterials() {
        calculateLengthShedPlanks();
        calculateWidthShedPlanks();
    }

    private void calculateWidthShedPlanks() {
        int shedPlanks = 0;
        int remainingLength = shed.getWidth(); //starting off with length
        if (remainingLength >= 420) {
            shedPlanks = 2; //
            remainingLength = remainingLength - 360;
            Materials shedSternBoards360 = new Materials(10, "Bræt", 360, shedPlanks, "Sternbrædder til siderne Skur(Brede)", 560);
            BOM.add(shedSternBoards360);
        } else if (remainingLength > 360) {
            for (int i = 0; i < shed.getLength(); i = i + 360) {
                shedPlanks++;
                remainingLength = remainingLength - i;
            }
            shedPlanks = shedPlanks * 2;
            Materials shedSternBoards360 = new Materials(11, "Bræt", 360, shedPlanks, "Sternbrædder til siderne Skur(Brede)", 560);
            BOM.add(shedSternBoards360);
        }
        if (remainingLength <= 0) {
            return;
        }
        shedPlanks = 2;
        Materials customCutSSternBoard = new Materials(12, "Bræt", remainingLength, shedPlanks, "Sternbrædder til siderne Skur(Brede) (Factory cut)", 750);
        BOM.add(customCutSSternBoard);
    }

    private void calculateLengthShedPlanks() {
        int shedPlanks = 0;
        int remainingLength = shed.getLength(); //starting off with length
        if (remainingLength >= 420) {
            shedPlanks = 2; //
            remainingLength = remainingLength - 360;
            Materials shedSternBoards360 = new Materials(10, "Bræt", 360, shedPlanks, "Sternbrædder til siderne Skur(Længde)", 400);
            BOM.add(shedSternBoards360);
            if (remainingLength > 0) {
                shedPlanks = 2;
                Materials customCutSSternBoard = new Materials(12, "Bræt", remainingLength, shedPlanks, "Sternbrædder til siderne Skur(Længde) (Factory cut)", 780);
                BOM.add(customCutSSternBoard);
            }
        } else {
            if (remainingLength > 360) {
                for (int i = 0; i < shed.getLength(); i = i + 360) {
                    shedPlanks++;
                    remainingLength = remainingLength - i;
                }
                shedPlanks = shedPlanks * 2;
                Materials shedSternBoards360 = new Materials(11, "Bræt", 360, shedPlanks, "Sternbrædder til siderne Skur(Længde)", 550);
                BOM.add(shedSternBoards360);
            }
            if (remainingLength > 0) {
                shedPlanks = 2;
                Materials customCutSSternBoard = new Materials(12, "Bræt", remainingLength, shedPlanks, "Sternbrædder til siderne Skur(Længde) (Factory cut)", 900);
                BOM.add(customCutSSternBoard);
            }
        }
    }

    public void calculateCarportMaterials() {
        calcuatePoles();
        calcualteRafters();
        calculateLengthSternBoards();
        calculateWidthSternBoards();

    }

    private void calculateWidthSternBoards() {
        //oversternbrædder default length 360 or 240 for length
        int remainingLength = carport.getWidth();
        int sternBoards = 0;
        //start by getting 360cm boards max carport længde er 780cm så uansert hvad kan der max være 2 gang 360
        if (carport.getWidth() >= 720) {
            sternBoards = 4; // 4 for 2 på hver side af lænden af carporten
            remainingLength = remainingLength - 360 * 2;
            Materials sternBoard360 = new Materials(6, "Bræt", 360, sternBoards, "'oversternbrædder til siderne (Brede)", 550);
            BOM.add(sternBoard360);
        } else if (carport.getWidth() > 240) {
            sternBoards = 2; //  2 på hver side af lænden af carporten
            remainingLength = remainingLength - 360;
            Materials sternBoard360 = new Materials(7, "Bræt", 360, sternBoards, "'oversternbrædder til siderne (Brede)", 550);
            BOM.add(sternBoard360);
        } // who likes readable code anyway?
        if (remainingLength >= 240) {
            sternBoards = 0;
            for (int i = 0; i < remainingLength; i = i + 240) {
                remainingLength = remainingLength - 240;
                System.out.println(remainingLength);
                sternBoards++;
            }
            sternBoards = sternBoards * 2;
            Materials sternBoard240 = new Materials(8, "Bræt", 240, sternBoards, "'oversternbrædder til siderne (Brede)", 350);
            BOM.add(sternBoard240);
        }
        if (remainingLength > 0) { //'FINE CUT' oversternbrædder very expensive idk
            Materials fineCutSternBoards = new Materials(9, "Bræt", remainingLength, 2, "'oversternbrædder til siderne (Factory cut) (Brede)", 640);
            BOM.add(fineCutSternBoards);
        }
    }

    private void calculateLengthSternBoards() {
        //oversternbrædder default length 360 or 240 for length
        int remainingLength = carport.getLength();
        int sternBoards = 0;
        //start by getting 360cm boards max carport længde er 780cm så uansert hvad kan der max være 2 gang 360
        if (carport.getLength() >= 720) {
            sternBoards = 4; // 4 for 2 på hver side af lænden af carporten
            remainingLength = remainingLength - 360 * 2;
            Materials sternBoard360 = new Materials(3, "Bræt", 360, sternBoards, "'oversternbrædder til siderne (længde)", 800);
            BOM.add(sternBoard360);
        } else if (carport.getLength() > 240) {
            sternBoards = 2; //  2 på hver side af lænden af carporten
            remainingLength = remainingLength - 360;
            Materials sternBoard360 = new Materials(3, "Bræt", 360, sternBoards, "'oversternbrædder til siderne (længde)", 900);
            BOM.add(sternBoard360);
        } // who likes readable code anyway?
        if (remainingLength >= 240) {
            sternBoards = 0;
            for (int i = 0; i < remainingLength; i = i + 240) {
                remainingLength = remainingLength - 240;
                System.out.println(remainingLength);
                sternBoards++;
            }
            sternBoards = sternBoards * 2;
            Materials sternBoard240 = new Materials(4, "Bræt", 240, sternBoards, "'oversternbrædder til siderne (længde)", 250);
            BOM.add(sternBoard240);
        }
        if (remainingLength > 0) { //'FINE CUT' oversternbrædder very expensive idk
            Materials fineCutSternBoards = new Materials(5, "Bræt", remainingLength, 2, "'oversternbrædder til siderne (Factory cut) (længde)   ", 500);
            BOM.add(fineCutSternBoards);
        }
    }

    private void calcualteRafters() {
        int rafter = 0;
        for (int i = 60; i < carport.getLength(); i = i + 60) {
            rafter++;
        }
        Materials rafters = new Materials(2, "Spær", carport.getWidth(), rafter, "byg-selv spær (skal samles)", 240);
        BOM.add(rafters);
    }

    private void calcuatePoles() {
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
            if (poleCounter % 2 != 0) {
                poleCounter = poleCounter + 1;
            }
        }
        Materials pole = new Materials(1, "Trykimprægneret Stolpe", 300, poleCounter, "k. Stolper nedgraves 90 cm. i jord\t+ skråstiver", 250);
        BOM.add(pole);
    }
    //todo:
    private void calculateFlatRoofParts() {
        //Plastmo	Ecolite	blåtone

    }
//todo:
    private void calcuateAngleRoofParts() {
        //taglægte gavl
    }

    private void SetInfomation(Carport carport, Shed shed) {
        this.carport = carport;
        this.shed = shed;

    }

}
