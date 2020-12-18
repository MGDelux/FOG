package Repoistory.Queries;

import domain.Employees.Employee;
import domain.Queries.Queries;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesRepo extends QueriesFactory {

    Iterable<Queries> getAllQuires();

    Queries deleteOrderById(int id) throws SQLException;

    Queries getLatestQuery() throws SQLException;

    Queries assignSellerToQuery(int getQueryValue, Employee employee);

    Queries getSpecificQueryByQueryId(int id) throws SQLException;

    //void deletespecific quire(int id) throws SQLException;
}
