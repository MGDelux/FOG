package infrastructure.DatabaseCarport;

import Repoistory.Carporte.CarpoteRepo;
import infrastructure.DatabaseConnector.Database;

/**
 * CREATED BY mathias @ 26-11-2020 - 11:35
 **/
public class DBCarport implements CarpoteRepo {
    private final Database db;

    public DBCarport(Database db) {
        this.db = db;
    }
}
