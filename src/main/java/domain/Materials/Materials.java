package domain.Materials;

/**
 * CREATED BY mathias @ 25-11-2020 - 14:11
 **/
public class Materials {
    private int id;
    private String name;
    private int length;
    private int amount;
    private String description;
    private  double price;

    public Materials(int id, String name, int length, int amount, String description, double price) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
