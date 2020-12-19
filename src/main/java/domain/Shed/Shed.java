package domain.Shed;

/**
 * CREATED BY mathias @ 07-12-2020 - 15:18
 **/
public class Shed {
    private final int width;
    private final int length;

    public Shed(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        String toString = "";
        if (width != 0 || length != 0) {
            toString = toString +
                    " Brede: " + width +
                    " Længde: " + length;
            return toString;
        }
        toString = " Intent skur";
        return toString;
    }
}
