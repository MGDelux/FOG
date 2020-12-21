package Repoistory.Queries;

import domain.Carport.Carport;
import domain.Employees.Employee;
import domain.Queries.Queries;
import domain.Shed.Shed;

import java.sql.SQLException;
import java.util.List;

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

    void updateQuery(int id, Carport carport, Shed shed);

    List<Queries> getQueryByEmail(String email) throws SQLException;
}
