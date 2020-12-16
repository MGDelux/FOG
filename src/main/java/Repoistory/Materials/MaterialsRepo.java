package Repoistory.Materials;

import domain.Materials.Materials;

/**
 * CREATED BY mathias @ 25-11-2020 - 13:52
 **/
public interface MaterialsRepo {
    Materials findMaterial(String s);
}
