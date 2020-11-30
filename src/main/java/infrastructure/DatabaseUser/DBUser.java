package infrastructure.DatabaseUser;

import Repoistory.User.UserRepo;
import domain.Users.User;
import infrastructure.DatabaseConnector.Database;

import java.sql.*;
import java.util.ArrayList;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:37
 **/
public class DBUser implements UserRepo {
    private final Database db;

    public DBUser(Database db) {
        this.db = db;
    }

    @Override
    public User addNewCustomer(User user) {
        try (Connection connection = db.connect()) {
            String sql = "INSERT INTO kunder (Email,Post_Nummer,City,Adresse,TlfNr) VALUES (?,?,?,?,?)";
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, user.getZipCode());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setInt(5, user.getPhoneNr());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public Iterable<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kunder");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(parseUsers(resultSet));
            }
        }
        return users;
    }

    private User parseUsers(ResultSet set) throws SQLException {
        return new User(
                set.getInt("kunder.Kunde_Id"),
                set.getString("kunder.Email"),
                set.getInt("kunder.Post_Nummer"),
                set.getString("kunder.City"),
                set.getString("kunder.Adresse"),
                set.getInt("kunder.TlfNr")
        );
    }

    @Override
    public boolean checkIfUsersIsInSystem(String mail) throws SQLException {
        return false;
    }
}
