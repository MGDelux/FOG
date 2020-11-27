package infrastructure.DatabaseUser;
import Repoistory.User.UserRepo;
import domain.Users.User;
import infrastructure.DatabaseConnector.Database;

import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:37
 **/
public class DBUser implements UserRepo {
    private final Database db;

    public DBUser(Database db) {
        this.db = db;
    }

    @Override
    public User addNewCustomer(User user) {
        return null;
    }

    @Override
    public Iterable<User> getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public boolean checkIfUsersIsInSystem(String mail) throws SQLException {
        return false;
    }
}
