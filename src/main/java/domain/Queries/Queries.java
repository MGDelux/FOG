package domain.Queries;

import domain.Carport.Carport;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 26-11-2020 - 10:50
 **/
public class Queries {
    private final int id;
    private final String Email;
    private Carport carport;
    private Shed shed;
    private String seller;
    private String orderDelete;

    public Queries(int id, String CustomerEmail, Carport carport, Shed shed, String seller, String orderDelete) {
        this.id = id;
        this.Email = CustomerEmail;
        this.carport = carport;
        this.shed = shed;
        this.seller = seller;
        this.orderDelete = orderDelete;
    }


    public String getOrderDelete() {
        return orderDelete;
    }

    public String getSeller() {
        return seller;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }


    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

    @Override
    public String toString() {
        return "Queries{" +
                "CustomerEmail='" + Email + '\'' +
                ", carport=" + carport +
                ", shed=" + shed +
                '}';
    }
}
