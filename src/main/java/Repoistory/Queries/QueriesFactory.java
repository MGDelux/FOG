package Repoistory.Queries;

import domain.Queries.Queries;
import domain.Users.User;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesFactory {
    Queries newQuery(User user, int carPortWidth, int cartPortLength, String roofType, int shedWidth, int shedLength) throws SQLException;
}
