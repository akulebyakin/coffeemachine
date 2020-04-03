package model;

public class Recipe {
    private int id;
    private String name;
    private String ingredient_ids;
    private String ingredient_amount;
    private int price;
    private boolean available;
    private int totalSold;

    public Recipe(int id, String name, String ingredient_ids, String ingredient_amount,
                  int price, boolean available, int total_sold) {
        this.id = id;
        this.name = name;
        this.ingredient_ids = ingredient_ids;
        this.ingredient_amount = ingredient_amount;
        this.price = price;
        this.available = available;
        this.totalSold = total_sold;
    }
    public Recipe(String name, String ingredient_ids, String ingredient_amount,
                  int price, boolean available) {
        this.name = name;
        this.ingredient_ids = ingredient_ids;
        this.ingredient_amount = ingredient_amount;
        this.price = price;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredient_ids() {
        return ingredient_ids;
    }

    public String getIngredient_amount() {
        return ingredient_amount;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getTotalSold() {
        return totalSold;
    }

    //    @Override
//    public String toString() {
//        return "id:\t" + id + "\n" +
//                "name:\t" + name + "\n" +
//                "ingredients:\t" + "ingredients" + "\n" +
//                "price:\t" + price + "\n" +
//                "available:\t" + (available ? "yes" : "no") + "\n" +
//                "total sold:\t" + totalSold;
//    }
    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", ingredient_ids: " + ingredient_ids +
                ", ingredient_amount: " + ingredient_amount + ", price: " + price +
                ", available: " + (available ? "yes" : "no") + ", total sold: " + totalSold;
    }
}
