package web.SVG;

import domain.Carport.Carport;
import domain.Shed.Shed;
import org.junit.jupiter.api.Test;

/**
 * CREATED BY mathias @ 15-12-2020 - 22:52
 **/
class SvgFactoryTest {

    @Test
    void drawCarport() {
        Shed shed = new Shed(120, 120);
        Carport carport = new Carport(245, 460, Carport.roofType.FLAT, 90);
        SvgFactory factory = new svgDraw();

    }
}