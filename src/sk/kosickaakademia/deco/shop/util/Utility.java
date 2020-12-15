package sk.kosickaakademia.deco.shop.util;

public class Utility {
    public static double convertEurToSkk(double value){
        return formatPrice(value*30.1260);
    }
    public static double formatPrice(double price){
        price=(double) Math.round(price*100)/100;
        return price;
    }
}
