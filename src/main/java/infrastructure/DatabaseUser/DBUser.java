package infrastructure.DatabaseUser;

import Repoistory.User.UserRepo;
import domain.Users.User;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:37
 **/
public class DBUser implements UserRepo {

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User addNewCustomer(int id, String email, int zipCode, String city, String adress, int phoneNR) {
        return null;
    }
}
