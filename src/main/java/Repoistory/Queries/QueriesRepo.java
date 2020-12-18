package Repoistory.Queries;

import domain.Employees.Employee;
import domain.Materials.Materials;
import domain.Queries.Queries;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesRepo extends QueriesFactory {

    Iterable<Queries> getAllQuires();

    Queries getSpecificQueryByUserID(int id) throws SQLException;

    Queries getLatestQuery() throws SQLException;

    Queries getSpecificQueryByQueryId(int id) throws SQLException;

    Queries assignSellerToQuery(int getQueryValue, Employee employee);

    void deleteOrderById(int id) throws SQLException;

}
