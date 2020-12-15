package sk.kosickaakademia.deco.shop;

import sk.kosickaakademia.deco.shop.coupon.Coupon;
import sk.kosickaakademia.deco.shop.coupon.Reader;
import sk.kosickaakademia.deco.shop.items.*;
import sk.kosickaakademia.deco.shop.items.countable.Chocolate;
import sk.kosickaakademia.deco.shop.items.countable.Water;
import sk.kosickaakademia.deco.shop.items.services.Delivery;
import sk.kosickaakademia.deco.shop.items.weightable.Apple;
import sk.kosickaakademia.deco.shop.items.weightable.Peanuts;
import sk.kosickaakademia.deco.shop.receipt.XmlReceipt;
import sk.kosickaakademia.deco.shop.util.Utility;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cart cart=new Cart();
        Item item1=new Apple("Golden",1.20,1.5);
        Item item2=new Water("Budis",0.78,1);
        Item item3=new Peanuts("Zambia",0.99,2.30);
        Item item4=new Chocolate("Orion",0.89,5);
        Item item5=new Delivery(2.50);

        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        cart.addItem(item4);
        cart.addItem(item5);


        /*System.out.print("Do you have a coupon? (y/n)");
        Scanner scanner=new Scanner(System.in);
        String coupon=scanner.nextLine().toLowerCase();

        if (coupon.charAt(0)=='y'){
            System.out.print("Enter coupon code: ");
            coupon=scanner.next();
            List<Coupon> couponList= Reader.getCouponsFromFile();
            for (Coupon i: couponList) {
                if (i.getCode().equals(coupon)){
                    System.out.println("Bingo");
                }
            }
        }*/



        cart.printCart();
        System.out.println("total price: "+cart.getTotalPrice()+"€");
        System.out.println("Total price in SKK: "+ Utility.convertEurToSkk(cart.getTotalPrice()));

        XmlReceipt.buildReceiptDoc(cart);

    }
    //private static checkout()
}
