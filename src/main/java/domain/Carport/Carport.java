package domain.Carport;

/**
 * CREATED BY mathias @ 25-11-2020 - 14:11
 **/
public class Carport {
    private final int width;
    private final int length;
    private final roofType roof;
    private int roofAngle;

    public Carport(int width, int length, roofType roof, int roofAngle) {
        this.width = width;
        this.length = length;
        this.roof = roof;
        this.roofAngle = roofAngle;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public roofType getRoof() {
        return roof;
    }

    public int getRoofAngle() {
        return roofAngle;
    }

    public void setRoofAngle(int roofAngle) {
        this.roofAngle = roofAngle;
    }

    public static enum roofType{
        FLAT,
        ANGLE
    }
}
