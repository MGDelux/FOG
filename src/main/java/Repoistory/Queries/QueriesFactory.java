package Repoistory.Queries;

import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Customers.Customers;
import domain.Shed.Shed;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:51
 **/
public interface QueriesFactory {
    Queries newQuery(Customers customers, Carport carport, Shed shed) throws SQLException;
}
