package domain.BOM;

import Repoistory.Materials.MaterialsRepo;
import domain.Carport.Carport;
import domain.Materials.Materials;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 16-12-2020 - 10:15
 **/
public class BomService {
   private final MaterialsRepo repo;

    public BomService(MaterialsRepo repo) {
        this.repo = repo;
    }

    public Bom calculateBom(Carport carport, Shed shed){
        // Right now we assume. Wood exist in all sizes.
        // they can figure out screws themselves.

       Bom b = new Bom();
       b.add(new Bom.BomItem(repo.findMaterial("")));
       return b;
   }
}
