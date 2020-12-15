package sk.kosickaakademia.deco.shop.items.weightable;

import sk.kosickaakademia.deco.shop.items.Item;
import sk.kosickaakademia.deco.shop.util.Utility;

public class Apple extends Item implements WeightItems {
    private double weight;

    public Apple(String name, double price,double weight) {
        super(name, price);
        this.weight=weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getItemPrice() {
        return Utility.formatPrice(weight* getPrice());
    }

    @Override
    public String toString() {
        return "Apple: "+getName()+", Price per kg: "+getPrice()+
                ", Weight: "+weight+", Total price: "+getItemPrice();
    }
}
