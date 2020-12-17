package domain.Materials;

/**
 * CREATED BY mathias @ 17-12-2020 - 11:05
 **/
public class Materials {
    private int id;
    private  String name;
    private int length;
    private int amount;
    private String description;
    private double price;

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
    public String test(String s){
        if (name.contains(s)){
            return name;
        }
        else {
            return null;
        }
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
