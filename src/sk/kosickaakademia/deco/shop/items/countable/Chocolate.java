package sk.kosickaakademia.deco.shop.items.countable;

import sk.kosickaakademia.deco.shop.items.Item;
import sk.kosickaakademia.deco.shop.items.countable.CountItems;

public class Chocolate extends Item implements CountItems {
    private int count;

    public Chocolate(String name, double price, int count) {
        super(name, price);
        this.count=count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public double getItemPrice() {
        return (double) Math.round(count* getPrice()*100)/100;
    }

    public String toString() {
        return "Chocolate: "+getName()+", Price per item: "+getPrice()+
                ", Count: "+count+", Total price: "+getItemPrice();
    }
}
