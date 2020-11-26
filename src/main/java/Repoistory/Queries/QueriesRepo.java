package Repoistory.Queries;

import domain.Queries.Queries;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesRepo extends QueriesFactory {

    Iterable<Queries> getAllQuires();

    Queries getSpecificQuire(int id);

    Queries deleteSpecificQuire(int id);
}
