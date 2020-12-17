package domain.BOM;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CREATED BY mathias @ 17-12-2020 - 13:15
 **/
class BomServiceTest {

    @Test
    void calucateCarportMaterials() {
        int poleCounter = 4;
        int extraPoles = 0;
        int carportl = 520;
        if (carportl<= 480){
            poleCounter = 4;
        }else {
            for (int i = 240; i < carportl; i = i + 240) {
                extraPoles++;
            }
            poleCounter = poleCounter + extraPoles;

        }
        System.out.println(poleCounter);
    }
}