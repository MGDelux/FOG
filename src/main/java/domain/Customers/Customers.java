package domain.Customers;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:18
 **/
public class Customers {
    private int id;
    private String email;
    private int zipCode;
    private String city;
    private String address;
    private int phoneNr;

    public Customers(int id, String email, int zipCode, String city, String address, int phoneNr) {
        this.id = id;
        this.email = email;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.phoneNr = phoneNr;
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public int getZipCode() {
        return zipCode;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }
    public int getPhoneNr() {
        return phoneNr;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phoneNr=" + phoneNr +
                '}';
    }
}

