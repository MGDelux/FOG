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
import java.util.List;

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
            preparedStatement.setInt(2, carport.getWidth());
            preparedStatement.setInt(3, carport.getLength());
            preparedStatement.setString(4, carport.getRoof().toString());
            preparedStatement.setBoolean(5, shed != null);
            if (shed != null) {
                preparedStatement.setInt(6, shed.getWidth());
                preparedStatement.setInt(7, shed.getLength());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
                preparedStatement.setNull(7, Types.INTEGER);
            }
            preparedStatement.setString(8, "Ingen sælger tildelt endnu");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; //fix
    }

    @Override
    public Queries assignSellerToQuery(int getQueryValue, Employee employee) {
        try (Connection connection = db.connect()) {
            String sql = "UPDATE forespørgsler SET assigned_seller = ? WHERE Order_Id = " + getQueryValue;
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    @Override
    public void deleteOrderById(int id) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "DELETE FROM forespørgsler WHERE Order_Id = ?;";
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void updateQuery(int id, Carport carport, Shed shed) {
        try (Connection connection = db.connect()) {
            String SQL = "UPDATE forespørgsler SET Carport_Bredde = ?,  Carport_Længde = ?, Tag_Type = ?, has_shed = ?, shed_width = ?, shed_length = ? WHERE Order_Id = ? ";
            var preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, carport.getWidth());
            preparedStatement.setInt(2, carport.getLength());
            preparedStatement.setString(3, carport.getRoof().toString());
            preparedStatement.setBoolean(4, shed != null);
            if (shed != null) {
                preparedStatement.setInt(5, shed.getWidth());
                preparedStatement.setInt(6, shed.getLength());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
                preparedStatement.setNull(6, Types.INTEGER);
            }
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Queries> getQueryByEmail(String email) throws SQLException {
        String SQL = "SELECT * FROM forespørgsler WHERE forespørgsler.kunde = ? ";
        PreparedStatement preparedStatement;
        ArrayList<Queries> queries = new ArrayList<>();
        Connection connection = db.connect();
        preparedStatement = connection.prepareStatement(SQL);
        try {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                queries.add(ParseQueries(resultSet));
            }

        } finally {
            connection.close();
            preparedStatement.close();
        }

        return queries;
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
        return new Queries(set.getInt("forespørgsler.Order_Id"), set.getString("forespørgsler.kunde"), carport, shed, set.getString("forespørgsler.assigned_seller"), "");

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
    public Queries getSpecificQueryByUserID(int id) throws SQLException {
        return null;
    }


    @Override
    public synchronized Queries getLatestQuery() throws SQLException {
        System.out.println("GET LATEST Q");
        String SQL = "SELECT * FROM FOG.forespørgsler ORDER  BY Order_Id DESC limit 1";
        PreparedStatement preparedStatement;
        Connection connection = db.connect();
        preparedStatement = connection.prepareStatement(SQL);
        try {
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                return ParseQueries(set);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.close();
            preparedStatement.close();
        }
        return null;
    }


}
