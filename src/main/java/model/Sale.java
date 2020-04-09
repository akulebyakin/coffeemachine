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
    private final String clientName;

    public Sale(int id, String name, int quantity, int totalPrice, int paidByCash, int paidByCard, Date date, String clientName) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paidByCash = paidByCash;
        this.paidByCard = paidByCard;
        this.date = date;
        this.clientName = clientName;
    }

    public Sale(String name, int quantity, String clientName) {
        this.name = name;
        this.quantity = quantity;
        this.clientName = clientName;
    }

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

    public String getClientName() {
        return clientName;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaidByCash(int paidByCash) {
        this.paidByCash = paidByCash;
    }

    public void setPaidByCard(int paidByCard) {
        this.paidByCard = paidByCard;
    }

    @Override
    public String toString() {
        return id + ". " + name + ". quantity: " + quantity + ", total price: " +
                totalPrice + ", paid by cash: " + paidByCash + ", paid by card: " + paidByCard + ", date: " + date +
                ", client: " + clientName;
    }
}
