package domain.BOM;

import domain.Materials.Materials;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY mathias @ 16-12-2020 - 10:16
 **/
public class Bom {
    private final List<BomItem> items = new ArrayList<>();

    public void add(BomItem item) {
        items.add(item);
    }

    public int getPrice() {
        // TODO calculate sum of items.
        return 20;
    }


    public static class BomItem {
        private final Materials material;

        public BomItem(Materials material) {
            this.material = material;
        }
    }

}
