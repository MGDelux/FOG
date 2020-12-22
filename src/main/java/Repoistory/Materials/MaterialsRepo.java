package Repoistory.Materials;

import domain.Materials.Materials;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 25-11-2020 - 13:52
 **/
public interface MaterialsRepo {

    Iterable<Materials> getAllMaterials();

    Materials findMaterial(String s) throws SQLException;

    void updateCarportMaterial(Materials materials, int id) throws SQLException;

    void addCarportMaterial(Materials materials,int id) throws SQLException;

    void deleteCarportMaterial(int id) throws SQLException;

    void updateFittingsAndScrews(Materials materials, int id) throws SQLException;

    void addFittingsAndScrews(Materials materials, int id) throws SQLException;

    void deleteFittingsAndScrews(int id) throws SQLException;

    Iterable<Materials> getAllScrews();



}
