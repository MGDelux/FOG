package infrastructure.DatabaseMaterials;

import Repoistory.Materials.MaterialsRepo;
import domain.Materials.Materials;
import domain.Queries.Queries;
import infrastructure.DatabaseConnector.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY mathias @ 26-11-2020 - 11:35
 **/
//<editor-fold desc="Materials">
//</editor-fold>

public class DBMaterials implements MaterialsRepo {
    private final Database db;

    public DBMaterials(Database db) {
        this.db = db;
    }


    private Materials parseMaterials(ResultSet resultSet) throws SQLException {
        return new Materials(
                resultSet.getInt("CartPortMaterialer.Carportmateriale_Id"),
                resultSet.getString("CartPortMaterialer.Carportmateriale_Navn"),
                resultSet.getInt("CartPortMaterialer.Carportmateriale_Length"),
                resultSet.getInt("CartPortMaterialer.Carportmateriale_antal"), resultSet.getString("CartPortMaterialer.materiale_Beskrivelse"),
                resultSet.getDouble("CartPortMaterialer.materiale_Pris"));
    }

    private Materials parseScrews(ResultSet resultSet) throws SQLException {
        return new Materials(
                resultSet.getInt("BeslagOgSkruer.BeslagOgSkruer_Id"),
                resultSet.getString("BeslagOgSkruer.BeslagOgSkruer_Navn"),
                resultSet.getInt("BeslagOgSkruer.BeslagOgSkruer_Length"),
                resultSet.getInt("BeslagOgSkruer.BeslagOgSkruer_antal"), resultSet.getString("BeslagOgSkruer.BeslagOgSkruer_Beskrivelse"),
                resultSet.getDouble("BeslagOgSkruer.BeslagOgSkruer_Pris"));
    }

    @Override
    public Iterable<Materials> getAllMaterials() {
        ArrayList<Materials> materials = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cartportmaterialer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                materials.add(parseMaterials(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return materials;
    }

    public Materials findMaterial(String s) throws SQLException {
        PreparedStatement preparedStatement;
        String SQL = "SELECT * FROM cartportmaterialer WHERE Carportmateriale_Navn = ?";
        Connection conn = db.connect();
        preparedStatement = conn.prepareStatement(SQL);
        preparedStatement.setString(1, s);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return parseMaterials(resultSet);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            db.closeConnection();
            preparedStatement.close();
        }
        return null;
    }

    @Override
    public void updateCarportMaterial(Materials materials, int id) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "UPDATE CartPortMaterialer SET Carportmateriale_Navn = ?, Carportmateriale_Length= ?,Carportmateriale_antal= ?, materiale_Beskrivelse= ?, materiale_Pris= ?  WHERE Carportmateriale_Id = ?";
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, materials.getName());
            preparedStatement.setInt(2, materials.getLength());
            preparedStatement.setInt(3, materials.getAmount());
            preparedStatement.setString(4, materials.getDescription());
            preparedStatement.setDouble(5, materials.getPrice());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void addCarportMaterial(Materials materials) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "INSERT INTO CartPortMaterialer (Carportmateriale_Navn, Carportmateriale_Length, Carportmateriale_antal, materiale_Beskrivelse, materiale_Pris) VALUE(?,?,?,?,?) ";
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, materials.getName());
            preparedStatement.setInt(2, materials.getLength());
            preparedStatement.setInt(3, materials.getAmount());
            preparedStatement.setString(4, materials.getDescription());
            preparedStatement.setDouble(5, materials.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void deleteCarportMaterial(int id) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "DELETE FROM CartPortMaterialer WHERE Carportmateriale_Id = ?";
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
    public void updateFittingsAndScrews(Materials materials, int id) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "UPDATE BeslagOgSkruer SET BeslagOgSkruer_Navn = ?, BeslagOgSkruer_Length= ?,BeslagOgSkruer_antal= ?, BeslagOgSkruer_Beskrivelse= ?, BeslagOgSkruer_Pris= ?  WHERE BeslagOgSkruer_Id = ?";
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, materials.getName());
            preparedStatement.setInt(2, materials.getLength());
            preparedStatement.setInt(3, materials.getAmount());
            preparedStatement.setString(4, materials.getDescription());
            preparedStatement.setDouble(5, materials.getPrice());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void addFittingsAndScrews(Materials materials, int id) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "INSERT INTO BeslagOgSkruer (BeslagOgSkruer_Navn, BeslagOgSkruer_Length, BeslagOgSkruer_antal, BeslagOgSkruer_Beskrivelse, BeslagOgSkruer_Pris) VALUE(?,?,?,?,?) ";
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, materials.getName());
            preparedStatement.setInt(2, materials.getLength());
            preparedStatement.setInt(3, materials.getAmount());
            preparedStatement.setString(4, materials.getDescription());
            preparedStatement.setDouble(5, materials.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void deleteFittingsAndScrews(int id) throws SQLException {
        try (Connection conn = db.connect()) {
            String sql = "DELETE FROM BeslagOgSkruer WHERE BeslagOgSkruer_Id = ?";
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
    public Iterable<Materials> getAllScrews() {
        ArrayList<Materials> screw = new ArrayList<>();
        try (Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BeslagOgSkruer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                screw.add(parseScrews(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return screw;
    }
}




