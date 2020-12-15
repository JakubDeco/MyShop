package sk.kosickaakademia.deco.shop;

import sk.kosickaakademia.deco.shop.items.Item;
import sk.kosickaakademia.deco.shop.items.countable.CountItems;
import sk.kosickaakademia.deco.shop.items.services.Service;
import sk.kosickaakademia.deco.shop.items.weightable.WeightItems;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> cart;

    public Cart(){
        cart=new ArrayList<>();
    }

    public List<Item> getCart() {
        return cart;
    }

    public void addItem(Item newItem){
        if (newItem.getPrice()>=0)
            if (newItem instanceof CountItems && ((CountItems) newItem).getCount()>0) {
                for (Item i : cart) {
                    if (newItem.getName().equals(i.getName()) && newItem.getPrice() == i.getPrice()) {
                        ((CountItems) i).setCount(((CountItems) i).getCount()+((CountItems) newItem).getCount());
                    }
                }
                cart.add(newItem);
            }
            if (newItem instanceof WeightItems && ((WeightItems) newItem).getWeight()>0)
                cart.add(newItem);
            if (newItem instanceof Service)
                cart.add(newItem);
    }

    public double getTotalPrice(){
        double total=0;
        for (Item i : cart) {
            total += i.getItemPrice();
        }
        return (double) Math.round(total*100)/100;
    }

    public int getCount() {
        return cart.size();
    }
    
    public void printCart(){
        System.out.println("Cart contains "+getCount()+" items:");
        for (Item i:cart) {
            System.out.println("...."+i.toString());
        }
    }
}
