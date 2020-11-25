package Repoistory.User;

import domain.Users.User;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:45
 **/
public interface UserRepo extends UserFactory {
    User createUser(User user);
}
