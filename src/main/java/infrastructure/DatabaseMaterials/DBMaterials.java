package infrastructure.DatabaseMaterials;

import Repoistory.Materials.MaterialsRepo;
import domain.Materials.Materials;
import infrastructure.DatabaseConnector.Database;

/**
 * CREATED BY mathias @ 26-11-2020 - 11:35
 **/
public class DBMaterials implements MaterialsRepo {
    private final Database db;

    public DBMaterials(Database db) {
        this.db = db;
    }

    @Override
    public Materials findMaterial(String s) {
        return null;
    }
}
