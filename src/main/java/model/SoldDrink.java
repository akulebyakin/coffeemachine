package model;

import java.sql.Date;

public class SoldDrink {
    private int id;
    private String name;
    private int quantity;
    private int total_price;
    private int paid_by_cash;
    private int paid_by_card;
    private Date date;

    public SoldDrink(String name, int quantity, int total_price, int paid_by_cash, int paid_by_card) {
        this.name = name;
        this.quantity = quantity;
        this.total_price = total_price;
        this.paid_by_cash = paid_by_cash;
        this.paid_by_card = paid_by_card;
    }

    @Override
    public String toString() {
        return "id:\t" + id + "\n" +
                "name:\t" + name + "\n" +
                "quantity:\t" + quantity + "\n" +
                "total price:\t" + total_price + "\n" +
                "paid by cash:\t" + paid_by_cash + "\n" +
                "paid by card:\t" + paid_by_card + "\n" +
                "date:\t" + date;
    }
}
