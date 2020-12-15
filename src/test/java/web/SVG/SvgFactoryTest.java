package web.SVG;

import domain.Carport.Carport;
import domain.Shed.Shed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CREATED BY mathias @ 15-12-2020 - 22:52
 **/
class SvgFactoryTest {

    @Test
    void drawCarport() {
        Shed shed = new Shed(250, 360);
        Carport carport = new Carport(680, 780, Carport.roofType.FLAT, 90);
        SvgFactory factory = new rect(shed, carport);
        factory.drawCarport(carport,shed);
    }
}