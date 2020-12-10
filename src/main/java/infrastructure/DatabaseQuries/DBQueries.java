package infrastructure.DatabaseQuries;

import Repoistory.Queries.QueriesRepo;
import domain.Carport.Carport;
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
        // we only need the Users ID (INT) nothing else from 'user' tyvm

        try (Connection connection = db.connect()) {
            String sql = "INSERT INTO forespørgsler (kunde,Carport_Bredde,Carport_Længde,Tag_Type,has_shed,shed_width,shed_length) VALUES (?,?,?,?,?,?,?)";

            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, customers.getId());
            preparedStatement.setInt(2, carport.getLength());
            preparedStatement.setInt(3, carport.getWidth());
            preparedStatement.setString(4, carport.getRoof().toString());
            preparedStatement.setBoolean(5, shed != null);
            if (shed != null) {
                preparedStatement.setInt(6, shed.getWidth());
                preparedStatement.setInt(7, shed.getLength());
            } else {
                preparedStatement.setNull(6, Types.INTEGER );
                preparedStatement.setNull(7, Types.INTEGER );
            }
            preparedStatement.executeUpdate();
        }finally {
            db.closeConnection();
        }

            return null; //fix
    }
    private Queries ParseQueries(ResultSet set) throws SQLException{
        Shed shed = null;
        if (set.getBoolean("forespørgsler.has_shed")) {
            shed = new Shed(
                    set.getInt("forespørgsler.shed_width"),
                    set.getInt("forespørgsler.shed_height"));
        }

        return new Queries(
                set.getInt("forespørgsler.kunde"),
                set.getInt("forespørgsler.Carport_Bredde"),
                set.getInt("forespørgsler.Carport_Længde"),
                set.getString("forespørgsler.Tag_Type"),
                shed);
    }

    @Override
    public Iterable<Queries> getAllQuires() {
        ArrayList<Queries> queries = new ArrayList<>();
        try (Connection connection = db.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM forespørgsler");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                queries.add(ParseQueries(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return queries;
    }


    @Override
    public Queries getSpecificQueryByUserID(int id) throws SQLException {
        String SQL = "SELECT * FROM forespørgsler WHERE forespørgsler.Order_Id = ? ";
        PreparedStatement preparedStatement;
        Connection connection= db.connect(); preparedStatement = connection.prepareStatement(SQL);
        try{
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ParseQueries(resultSet);
            }

        }finally {
            connection.close();
            preparedStatement.close();
        }
        return null;

    }

    @Override
    public Queries deleteSpecificQuire(int id) {
        return null;
    }
}
