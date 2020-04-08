package model;

public class Ingredient {
    private int id;
    private String name;
    private int balance;
    private String unit;

    public Ingredient(int id, String name, int balance, String unit) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String getUnit() {
        return unit;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //    @Override
//    public String toString() {
//        return "id:\t" + id + "\n" +
//                "name:\t" + name + "\n" +
//                "balance:\t" + balance + " " + unit;
//    }
    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", balance: " + balance + " " + unit;
    }
}
