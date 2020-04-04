package service;

import java.util.List;

public interface ClientService {
    // get
    List<String> getMenu();
    List<String> getDrinkComposition();
    List<String> getMostPopularDrinks();

    // make
    int makeCoffee(String coffeeName, String clientName, String payMethod);
}