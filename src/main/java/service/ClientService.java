package service;

public interface ClientService {
    // get
    void getMenu();
    void getDrinkComposition();
    void getMostPopularDrinks();

    // make
    boolean makeCoffee(String name);
}
