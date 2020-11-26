package domain.Queries;

import domain.Users.User;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:50
 **/
public class Queries {
    private User user;
    private int carPortWidth;
    private int cartPortLength;
    private String roofType;
    private int shedWidth;
    private int shedLength;

    public Queries(User user, int carPortWidth, int cartPortLength, String roofType, int shedWidth, int shedLength) {
        this.user = user;
        this.carPortWidth = carPortWidth;
        this.cartPortLength = cartPortLength;
        this.roofType = roofType;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "user=" + user +
                ", carPortWidth=" + carPortWidth +
                ", cartPortLength=" + cartPortLength +
                ", roofType='" + roofType + '\'' +
                ", shedWidth=" + shedWidth +
                ", shedLength=" + shedLength +
                '}';
    }
}
