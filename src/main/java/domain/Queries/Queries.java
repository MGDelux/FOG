package domain.Queries;

import domain.Users.User;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:50
 **/
public class Queries {
    private int userId;
    private int carPortWidth;
    private int cartPortLength;
    private String roofType;
    private int shedWidth;
    private int shedLength;

    public Queries(int useriD, int carPortWidth, int cartPortLength, String roofType, int shedWidth, int shedLength) {
        this.userId = useriD;
        this.carPortWidth = carPortWidth;
        this.cartPortLength = cartPortLength;
        this.roofType = roofType;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
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

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    @Override
    public String toString() {
        return "Queries{" +
                "user=" + userId +
                ", carPortWidth=" + carPortWidth +
                ", cartPortLength=" + cartPortLength +
                ", roofType='" + roofType + '\'' +
                ", shedWidth=" + shedWidth +
                ", shedLength=" + shedLength +
                '}';
    }
}
