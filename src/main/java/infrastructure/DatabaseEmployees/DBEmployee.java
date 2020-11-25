package infrastructure.DatabaseEmployees;

import Repoistory.Employee.EmployeeRepo;
import domain.Employees.Employee;
import Repoistory.Employee.Exceptions.loginError;
import infrastructure.DatabaseConnector.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:38
 **/
public class DBEmployee implements EmployeeRepo {
    private final Database db;

    public DBEmployee(Database db) {
        this.db = db;
    }

    private Employee.Role role;

    @Override
    public Iterable<Employee> getAllEmployees() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medarbejder ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(parseEmployees(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.closeConnection();
        }
        return employees;
    }

    private Employee parseEmployees(ResultSet resultSet) throws SQLException {
        return new Employee(
                Employee.Role.valueOf(resultSet.getString("medarbejder.Role")),
                resultSet.getInt("medarbejder.Medarbejder_id"),
                resultSet.getString("medarbejder.Email"),
                resultSet.getBytes("medarbejder.salt"),
                resultSet.getBytes("medarbejder.secret"));
    }

    @Override
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

    @Override
    public Employee login(String email) throws loginError, SQLException {
        PreparedStatement preparedStatement;
        String SQL = "SELECT * FROM medarbejder WHERE email = ?";
        Connection conn = db.connect();
        preparedStatement = conn.prepareStatement(SQL);
        try {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return parseEmployees(resultSet);
            } else {
                throw new loginError("Login Error ");
            }

        } catch (SQLException e) {
            throw new loginError("SQL ERROR: " +e);
        } finally {
            db.closeConnection();
            preparedStatement.close();
        }
    }


    @Override
    public Employee createEmployee(Employee employee) throws SQLException {
     try (Connection connection = db.connect()) {
         String SQL = "INSERT INTO medarbejder (Email,Role,salt,secret) VALUES (?,?,?,?)";
         var smt = connection.prepareStatement(SQL,PreparedStatement.RETURN_GENERATED_KEYS);
         smt.setString(1,employee.getEmail());
         smt.setString(2,employee.getRole().toString());
         smt.setBytes(3,employee.getSalt());
         smt.setBytes(4,employee.getSecret());
         smt.executeUpdate();
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }
     return employee;
    }
}
