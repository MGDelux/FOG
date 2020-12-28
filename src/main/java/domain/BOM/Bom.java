package domain.BOM;
import domain.Materials.Materials;
import java.util.List;

/**
 * CREATED BY mathias @ 17-12-2020 - 11:03
 **/
public class Bom {
    private final List<Materials> bomMaterials;

    public Bom(List<Materials> bomMaterials) {
        this.bomMaterials = bomMaterials;
    }

    @Override
    public String toString() {
        return "Bom{" +
                "bomMaterials=" + bomMaterials +
                '}';
    }
}
