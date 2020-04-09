package model;

public class Recipe {
    private int id;
    private String name;
    private int price;
    private boolean available;
    private int totalSold;

    public Recipe(int id, String name, int price, boolean available, int total_sold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.totalSold = total_sold;
    }

    public Recipe(String name, int price) {
        this.name = name;
        this.price = price;

        this.id = -1;
        this.available = true;
        this.totalSold = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
        return "id: " + id + ", name: " + name + ", price: " + price +
                ", available: " + (available ? "yes" : "no") + ", total sold: " + totalSold;
    }
}
