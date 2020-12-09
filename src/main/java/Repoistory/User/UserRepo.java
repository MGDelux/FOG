package Repoistory.User;

import domain.Customers.Customers;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 23-11-2020 - 15:45
 **/
public interface UserRepo extends UserFactory {
    Iterable<Customers> getAllUsers() throws SQLException;
    boolean checkIfUsersIsInSystem(String mail) throws SQLException;
    Customers getExistingUserInfomation(String email) throws SQLException;
    Customers getExistingUserInfomationById(int id) throws SQLException;
}
