package infrastructure;

import com.google.protobuf.Enum;
import domain.Employee;
import domain.loginError;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:38
 **/
public class DBEmployee {
    private final Database db;

    public DBEmployee(Database db) {
        this.db = db;
    }

    private Employee.Role role;

    public boolean checkMail(String mail) throws SQLException {
        Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement("SELECT * From medarbejder where email = ?");
        try {
            ps.setString(1, mail);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            db.closeConnection();
            ps.close();
        }

    }

    public Employee login(String email) throws loginError, SQLException {
        Employee employee;
        PreparedStatement preparedStatement;
        String SQL = "SELET * FROM medarbejder WHERE email = ?";
        Connection conn = db.connect();
        preparedStatement = conn.prepareStatement(SQL);
        try {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("Medarbejder_id");
                role = Employee.Role.valueOf(resultSet.getString("Role"));
                byte[] salt = resultSet.getBytes("salt");
                byte[] secret = resultSet.getBytes("secret");
                employee = new Employee(role, id, email, salt, secret);
            } else {
                throw new loginError("Login incorrect");
            }
            return employee;

        } catch (SQLException e) {
            throw new loginError("SQL ERROR");
        } finally {
            db.closeConnection();
            preparedStatement.close();
        }
    }

}
