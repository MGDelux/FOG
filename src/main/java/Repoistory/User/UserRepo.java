package Repoistory.User;

import domain.Employees.Employee;
import domain.Users.User;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 23-11-2020 - 15:45
 **/
public interface UserRepo extends UserFactory {
    Iterable<User> getAllUsers() throws SQLException;
    boolean checkIfUsersIsInSystem(String mail) throws SQLException;
    User getExistingUserInfomation(String email) throws SQLException;
    User getExistingUserInfomationById(int id) throws SQLException;
}
