package sk.kosickaakademia.deco.shop.coupon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private static String fileName="resources/coupons.txt";

    public static List<Coupon> getCouponsFromFile(){
        List<Coupon> couponList=new ArrayList<>();
        try {
            File file=new File(fileName);
            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                String[] array=scanner.nextLine().split(" ");
                couponList.add(new Coupon(array[0],Integer.parseInt(array[1])));
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return couponList;
    }
}
