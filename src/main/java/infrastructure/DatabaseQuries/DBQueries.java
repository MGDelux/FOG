package infrastructure.DatabaseQuries;

import Repoistory.Queries.QueriesRepo;
import domain.Queries.Queries;
import domain.Users.User;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:59
 **/
public class DBQueries implements QueriesRepo {
    @Override
    public Queries newQuerie(User user, int carPortWidth, int cartPortLength, String roofType, int shedWidth, int shedLength) {
        // we only need the Users ID (INT) nothing else from 'user' tyvm
        return null;
    }
}
