package web.SVG;

import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Shed.Shed;
import org.junit.jupiter.api.Test;

/**
 * CREATED BY mathias @ 15-12-2020 - 22:52
 **/
class SvgFactoryTest {

    @Test
    void drawCarport() {
        Shed shed = new Shed(240, 240);
        Carport carport = new Carport(350, 780, Carport.roofType.FLAT, 90);
        Queries queries = new Queries(1,"emai",carport,shed,"goodgey");
        SvgFactory factory = new svgDraw();
      //  factory.updateDrawCarport(carport,shed);
        factory.drawCarport(queries);

    }
}