package infrastructure.DatabaseUser;
import Repoistory.User.UserRepo;
import domain.Users.User;
import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:37
 **/
public class DBUser implements UserRepo {


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
