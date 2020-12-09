package web.SVG;

/**
 * CREATED BY mathias @ 07-12-2020 - 14:13
 **/
public class svg {
    private static String backGroundWidth = "800";
    private static String backGroundLength = "600";
    private  int carportWidth;
    private  int carportLength;
    private  int redskabsRumWidth;
    private  int redskabRumLength;

    public svg(int carportWidth, int carportLength, int redskabsRumWidth, int redskabRumLength) {
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.redskabsRumWidth = redskabsRumWidth;
        this.redskabRumLength = redskabRumLength;
    }
}
