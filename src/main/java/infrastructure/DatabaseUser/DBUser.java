package infrastructure.DatabaseUser;

import Repoistory.Customer.CustomerRepo;
import domain.Customers.Customers;
import infrastructure.DatabaseConnector.Database;
import infrastructure.Exceptions.UnexpectedDbError;

import java.sql.*;
import java.util.ArrayList;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:37
 **/
public class DBUser implements CustomerRepo {
    private final Database db;

    public DBUser(Database db) {
        this.db = db;
    }

    @Override
    public synchronized Customers addNewCustomer(Customers customers) {
        try (Connection connection = db.connect()) {
            String sql = "INSERT INTO kunder (Email,Post_Nummer,City,Adresse,TlfNr) VALUES (?,?,?,?,?)";
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customers.getEmail());
            preparedStatement.setInt(2, customers.getZipCode());
            preparedStatement.setString(3, customers.getCity());
            preparedStatement.setString(4, customers.getAddress());
            preparedStatement.setInt(5, customers.getPhoneNr());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    @Override
    public Iterable<Customers> getAllUsers() throws SQLException {
        ArrayList<Customers> customers = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kunder");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(parseUsers(resultSet));
            }
        }
        return customers;
    }

    private Customers parseUsers(ResultSet set) throws SQLException {
        return new Customers(
                set.getInt("kunder.Kunde_Id"),
                set.getString("kunder.Email"),
                set.getInt("kunder.Post_Nummer"),
                set.getString("kunder.City"),
                set.getString("kunder.Adresse"),
                set.getInt("kunder.TlfNr")
        );
    }

    @Override
    public boolean checkIfUsersIsInSystem(String mail) {
        try {
            String SQL = "SELECT * FROM kunder WHERE Email = ?";
            PreparedStatement preparedStatement;
            Connection connection = db.connect();
            preparedStatement = connection.prepareStatement(SQL);
            try {
                preparedStatement.setString(1, mail);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } finally {
                preparedStatement.close();
                connection.close();
            }

        } catch (SQLException throwables) {
            throw new UnexpectedDbError(throwables);
        }
    }

    @Override
    public Customers getExistingUserInfomation(String email) throws SQLException {
        String SQL = "SELECT * FROM kunder WHERE Email = ?";
        PreparedStatement preparedStatement;
        Connection connection = db.connect();
        preparedStatement = connection.prepareStatement(SQL);
        try {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return parseUsers(resultSet);
            } else {
                return null;
            }
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public Customers getExistingUserInfomationById(int id) throws SQLException {
        String SQL = "SELECT * FROM kunder WHERE Kunde_Id = ?";
        PreparedStatement preparedStatement;
        Connection connection = db.connect();
        preparedStatement = connection.prepareStatement(SQL);
        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return parseUsers(resultSet);
            } else {
                return null;
            }
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
