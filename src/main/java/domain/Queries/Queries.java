package domain.Queries;

import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:50
 **/
public class Queries {
    private int userId;
    private int carPortWidth;
    private int cartPortLength;
    private String roofType;
    private Shed shed;

    public Queries(int useriD, int carPortWidth, int cartPortLength, String roofType, Shed shed) {
        this.userId = useriD;
        this.carPortWidth = carPortWidth;
        this.cartPortLength = cartPortLength;
        this.roofType = roofType;
        this.shed = shed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarPortWidth() {
        return carPortWidth;
    }

    public void setCarPortWidth(int carPortWidth) {
        this.carPortWidth = carPortWidth;
    }

    public int getCartPortLength() {
        return cartPortLength;
    }

    public void setCartPortLength(int cartPortLength) {
        this.cartPortLength = cartPortLength;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public Shed getShed() {
        return shed;
    }

    @Override
    public String toString() {
        return "Queries{" +
                "userId=" + userId +
                ", carPortWidth=" + carPortWidth +
                ", cartPortLength=" + cartPortLength +
                ", roofType='" + roofType + '\'' +
                ", shed=" + shed +
                '}';
    }
}
