package Repoistory.Queries;

import domain.Queries.Queries;
import domain.Users.User;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesFactory {
    Queries newQuerie(User user, int carPortWidth,  int cartPortLength, String roofType,int shedWidth,int shedLength);
}
