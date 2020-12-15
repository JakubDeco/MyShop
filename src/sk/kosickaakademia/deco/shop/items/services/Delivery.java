package sk.kosickaakademia.deco.shop.items.services;

import sk.kosickaakademia.deco.shop.items.Item;

public class Delivery extends Item implements Service{
    public Delivery(double price) {
        super("delivery", price);
    }

    @Override
    public double getItemPrice() {
        return getPrice();
    }

    @Override
    public String toString() {
        return "Delivery, Price: "+getPrice();
    }
}
