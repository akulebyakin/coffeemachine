package model;

import java.util.Objects;

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

    public Ingredient(String name, int balance, String unit) {
        this.name = name;
        this.balance = balance;
        this.unit = unit;

        this.id = -1;
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
        return id + ". " + name + " - " + balance + " " + unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id &&
                balance == that.balance &&
                Objects.equals(name, that.name) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance, unit);
    }
}
