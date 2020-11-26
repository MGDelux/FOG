package infrastructure.DatabaseQuries;

import Repoistory.Queries.QueriesRepo;
import domain.Queries.Queries;
import domain.Users.User;
import infrastructure.DatabaseConnector.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:59
 **/
public class DBQueries implements QueriesRepo {
    private final Database db;
    public DBQueries(Database db) {
        this.db = db;
    }

    @Override
    public Queries newQuerie(User user, int carPortWidth, int carPortLength, String roofType, int shedWidth, int shedLength) throws SQLException {
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

            return null;
    }

}
