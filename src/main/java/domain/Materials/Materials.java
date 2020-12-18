package domain.Materials;

/**
 * CREATED BY mathias @ 17-12-2020 - 11:05
 **/
public class Materials {
    private final int id;
    private final String name;
    private final int length;
    private final int amount;
    private final String description;
    private final double price;

    public Materials(int id, String name, int length, int amount, String description, double price) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Materials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
