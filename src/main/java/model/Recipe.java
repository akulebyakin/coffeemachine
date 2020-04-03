package model;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private List<Integer> ingredient_ids;
    private List<Integer> ingredient_amount;
    private int price;
    private boolean available;
    private int totalSold;

    public Recipe(String name, List<Integer> ingredient_ids, List<Integer> ingredient_amount,
                  int price, boolean available) {
        this.name = name;
        this.ingredient_ids = ingredient_ids;
        this.ingredient_amount = ingredient_amount;
        this.price = price;
        this.available = available;
    }

    @Override
    public String toString() {
        return "id:\t" + id + "\n" +
                "name:\t" + name + "\n" +
                "ingredients:\t" + "ingredients" + "\n" +
                "price:\t" + price + "\n" +
                "available:\t" + (available ? "yes" : "no") + "\n" +
                "total sold:\t" + totalSold;
    }
}
