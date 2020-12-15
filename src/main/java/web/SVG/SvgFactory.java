package web.SVG;

import domain.Carport.Carport;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 15-12-2020 - 22:19
 **/
public interface SvgFactory {
    void drawCarport(Carport carport, Shed shed);
}
