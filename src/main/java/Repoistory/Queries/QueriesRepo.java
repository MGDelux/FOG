package Repoistory.Queries;

import domain.Queries.Queries;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesRepo extends QueriesFactory {

    Iterable<Queries> getAllQuires();

    Queries getSpecificQueryByUserID(int id) throws SQLException;

    Queries deleteSpecificQuire(int id);
}
