package infrastructure.DatabaseQuries;

import Repoistory.Queries.QueriesRepo;
import domain.Queries.Queries;
import domain.Users.User;
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
    public synchronized Queries newQuery(User user, int carPortWidth, int carPortLength, String roofType, int shedWidth, int shedLength) throws SQLException {
        // we only need the Users ID (INT) nothing else from 'user' tyvm

        try (Connection connection = db.connect()) {
            String sql = "INSERT INTO forespørgsler (kunde,Carport_Bredde,Carport_Længde,Tag_Type,Redskabsrum_Bredde,Redskabsrum_Længde) VALUES (?,?,?,?,?,?)";
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, carPortWidth);
            preparedStatement.setInt(3, carPortLength);
            preparedStatement.setString(4, roofType);
            preparedStatement.setInt(5, shedWidth);
            preparedStatement.setInt(6, shedLength);
            preparedStatement.executeUpdate();
        }finally {
            db.closeConnection();
        }

            return null; //fix
    }
    private Queries ParseQueries(ResultSet set) throws SQLException{
        return new Queries(
                set.getInt("forespørgsler.kunde"),
                set.getInt("forespørgsler.Carport_Bredde"),
                set.getInt("forespørgsler.Carport_Længde"),
                set.getString("forespørgsler.Tag_Type"),
                set.getInt("forespørgsler.Redskabsrum_Bredde"),
                set.getInt("forespørgsler.Redskabsrum_Længde"));
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
    public Queries getSpecificQuire(int id) throws SQLException {
        String SQL = "SELECT * FROM forespørgsler WHERE ForeSpørglse_Id = ? ";
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
