package model;

import java.sql.Date;

public class Sale {
    private int id;
    private String name;
    private int quantity;
    private int totalPrice;
    private int paidByCash;
    private int paidByCard;
    private Date date;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getPaidByCash() {
        return paidByCash;
    }

    public int getPaidByCard() {
        return paidByCard;
    }

    public Date getDate() {
        return date;
    }

    public Sale(int id, String name, int quantity, int totalPrice, int paidByCash, int paidByCard, Date date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paidByCash = paidByCash;
        this.paidByCard = paidByCard;
        this.date = date;
    }

    @Override
    public String toString() {
        return "id:\t" + id + "\n" +
                "name:\t" + name + "\n" +
                "quantity:\t" + quantity + "\n" +
                "total price:\t" + totalPrice + "\n" +
                "paid by cash:\t" + paidByCash + "\n" +
                "paid by card:\t" + paidByCard + "\n" +
                "date:\t" + date;
    }
}
