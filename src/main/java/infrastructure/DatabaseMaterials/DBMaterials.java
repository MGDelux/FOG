package infrastructure.DatabaseMaterials;

import Repoistory.Materials.MaterialsRepo;
import domain.Materials.Materials;
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

public class DBMaterials {
    private final Database db;

    public DBMaterials(Database db) {
        this.db = db;
    }

    public List<Materials> getMaterial() throws SQLException {
        List<Materials> materials = new ArrayList<>();
        PreparedStatement preparedStatement;
        String SQL = "SELECT * FROM cartportmaterialer";
        Connection conn = db.connect();
        preparedStatement = conn.prepareStatement(SQL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                materials.add(parseMaterials(resultSet));

            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            db.closeConnection();
            preparedStatement.close();
        }
        return materials;
    }

    private Materials parseMaterials(ResultSet resultSet) throws SQLException {
        return new Materials(
                resultSet.getInt("CartPortMaterialer.Carportmateriale_Id"),
                resultSet.getString("CartPortMaterialer.Carportmateriale_Navn"),
                resultSet.getInt("CartPortMaterialer.Carportmateriale_Length"),
                1, resultSet.getString("CartPortMaterialer.materiale_Beskrivelse"),
                resultSet.getDouble("CartPortMaterialer.materiale_Pris"));
    }

    public Materials findMaterial(String s ) throws SQLException {
        PreparedStatement preparedStatement;
        String SQL = "SELECT * FROM cartportmaterialer WHERE Carportmateriale_Navn = ?";
        Connection conn = db.connect();
        preparedStatement = conn.prepareStatement(SQL);
        preparedStatement.setString(1,s);
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



}
