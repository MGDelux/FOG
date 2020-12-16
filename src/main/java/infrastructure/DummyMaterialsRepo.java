package infrastructure;

import Repoistory.Materials.MaterialsRepo;
import domain.Materials.Materials;

/**
 * CREATED BY mathias @ 16-12-2020 - 10:31
 **/
public class DummyMaterialsRepo implements MaterialsRepo {


    @Override
    public Materials findMaterial(String name) {
        return new Materials(1,"TRÆ",25,5,"some description",25);
    }
}
