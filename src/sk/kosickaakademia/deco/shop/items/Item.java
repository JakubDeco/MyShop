package sk.kosickaakademia.deco.shop.items;

public abstract class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract double getItemPrice();

    @Override
    public abstract String toString();
}
