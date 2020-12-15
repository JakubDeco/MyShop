package sk.kosickaakademia.deco.shop.items.weightable;

import sk.kosickaakademia.deco.shop.items.Item;

public class Peanuts extends Item implements WeightItems {
    private double weight;

    public Peanuts(String name, double price, double weight) {
        super(name, price);
        this.weight=weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getItemPrice() {
        return (double) Math.round(weight* getPrice()*100)/100;
    }

    public String toString() {
        return "Peanuts: "+getName()+", Price per kg: "+getPrice()+
                ", Weight: "+weight+", Total price: "+getItemPrice();
    }
}
