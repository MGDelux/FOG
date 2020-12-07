package Repoistory.Queries;

import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Users.User;
import domain.Shed.Shed;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesFactory {
    Queries newQuery(User user, Carport carport, Shed shed) throws SQLException;
}
