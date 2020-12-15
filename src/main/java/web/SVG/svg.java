package web.SVG;

import domain.Carport.Carport;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 07-12-2020 - 14:13
 **/
public class svg {
    private Shed shed;
    private Carport carport;
    private int width = carport.getWidth()+10;
    private int length = carport.getLength()+10;

    public svg(Shed shed, Carport carport) {
        this.shed = shed;
        this.carport = carport;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Shed getShed() {
        return shed;
    }

    public Carport getCarport() {
        return carport;
    }
}
