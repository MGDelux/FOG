package infrastructure.DatabaseQuries;

import Repoistory.Queries.QueriesRepo;
import domain.Carport.Carport;
import domain.Employees.Employee;
import domain.Queries.Queries;
import domain.Customers.Customers;
import domain.Shed.Shed;
import infrastructure.DatabaseConnector.Database;

import java.sql.*;
import java.util.ArrayList;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:59
 **/
public class DBQueries implements QueriesRepo {
    private final Database db;

    public DBQueries(Database db) {
        this.db = db;
    }

    @Override
    public synchronized Queries newQuery(Customers customers, Carport carport, Shed shed) throws SQLException {

        try (Connection connection = db.connect()) {
            String sql = "INSERT INTO forespørgsler (kunde,Carport_Bredde,Carport_Længde,Tag_Type,has_shed,shed_width,shed_length,assigned_seller) VALUES (?,?,?,?,?,?,?,?)";

            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customers.getEmail());
            preparedStatement.setInt(2, carport.getLength());
            preparedStatement.setInt(3, carport.getWidth());
            preparedStatement.setString(4, carport.getRoof().toString());
            preparedStatement.setBoolean(5, shed != null);
            if (shed != null) {
                preparedStatement.setInt(6, shed.getWidth());
                preparedStatement.setInt(7, shed.getLength());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
                preparedStatement.setNull(7, Types.INTEGER);
            }
            preparedStatement.setString(8,"TBA");
            preparedStatement.executeUpdate();
        } finally {
            db.closeConnection();
        }

        return null; //fix
    }
    @Override
    public Queries assignSellerToQuery(int getQueryValue, Employee employee) {
        try (Connection connection = db.connect()) {
            String sql = "UPDATE forespørgsler SET assigned_seller = ? WHERE Order_Id = "+getQueryValue;
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
       preparedStatement.setString(1,employee.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    @Override
    public Queries getSpecificQueryByQueryId(int id) throws SQLException {
        String SQL = "SELECT * FROM forespørgsler WHERE forespørgsler.Order_Id = ? ";
        PreparedStatement preparedStatement;
        Connection connection = db.connect();
        preparedStatement = connection.prepareStatement(SQL);
        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ParseQueries(resultSet);
            }

        } finally {
            connection.close();
            preparedStatement.close();
        }

        return null;
    }

    private Queries ParseQueries(ResultSet set) throws SQLException {
        Shed shed = null;
        if (set.getBoolean("forespørgsler.has_shed")) {
            shed = new Shed(
                    set.getInt("forespørgsler.shed_width"),
                    set.getInt("forespørgsler.shed_length"));
        }
        Carport carport = null;
        carport = new Carport(
                set.getInt("forespørgsler.Carport_Bredde"),
                set.getInt("forespørgsler.Carport_Længde"),
               Carport.roofType.valueOf(set.getString("forespørgsler.Tag_Type")), 90);
        return new Queries(set.getInt("forespørgsler.Order_Id"),  set.getString("forespørgsler.kunde"), carport, shed, set.getString("forespørgsler.assigned_seller"));

    }

    @Override

    public Iterable<Queries> getAllQuires() {
        ArrayList<Queries> queries = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM forespørgsler");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                queries.add(ParseQueries(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return queries;
    }


    @Override
    public Queries deleteSpecificQuire(int id) {
        return null;
    }

    @Override
    public synchronized Queries getLatestQuery() throws SQLException {
        System.out.println("GET LATEST Q");
        String SQL = "SELECT * FROM fog.forespørgsler ORDER  BY Order_Id DESC limit 1";
        PreparedStatement preparedStatement;
        Connection connection = db.connect();
        preparedStatement = connection.prepareStatement(SQL);
        try {
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()){
                return ParseQueries(set);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connection.close();
            preparedStatement.close();
        }
        return null;
    }


}
