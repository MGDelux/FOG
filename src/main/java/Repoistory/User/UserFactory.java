package Repoistory.User;

import domain.Users.User;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:44
 **/
public interface UserFactory {
    User addNewCustomer(int id,String email, int zipCode, String city, String adress, int phoneNR);
}
