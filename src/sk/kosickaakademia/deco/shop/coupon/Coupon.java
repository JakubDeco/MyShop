package sk.kosickaakademia.deco.shop.coupon;


public class Coupon {
    private String code;
    private int value;

    public Coupon(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }
}
