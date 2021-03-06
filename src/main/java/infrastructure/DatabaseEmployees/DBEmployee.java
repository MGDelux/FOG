package infrastructure.DatabaseEmployees;

import Repoistory.Employee.EmployeeRepo;
import domain.Employees.Employee;
import Repoistory.Employee.Exceptions.loginError;
import infrastructure.DatabaseConnector.Database;

import java.sql.*;
import java.util.ArrayList;

/**
 * CREATED BY mathi @ 23-11-2020 - 16:38
 **/
public class DBEmployee implements EmployeeRepo {
    private final Database db;

    public DBEmployee(Database db) {
        this.db = db;
    }


    @Override
    public Iterable<Employee> getAllEmployees() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medarbejder");
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
    public synchronized Employee login(String email) throws loginError, SQLException {
        PreparedStatement preparedStatement;
        String sql = "SELECT * FROM medarbejder WHERE email = ?";
        Connection conn = db.connect();
        preparedStatement = conn.prepareStatement(sql);
        try {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return parseEmployees(resultSet);
            } else {
                throw new loginError("Fejl i login-processen, prøv igen. check password og mail.");
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
        String sql = "INSERT INTO medarbejder (Email,Role,salt,secret) VALUES (?,?,?,?)";

     try (Connection connection = db.connect()) {
         var smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         smt.setString(1,employee.getEmail());
         smt.setString(2,employee.getRole().toString());
         smt.setBytes(3,employee.getSalt());
         smt.setBytes(4,employee.getSecret());
         smt.executeUpdate();
     } catch (SQLException e){
         e.printStackTrace();
     }finally {
         db.closeConnection();
     }
     return employee;
    }
}
