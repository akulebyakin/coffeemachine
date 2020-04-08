package service;

import java.util.List;

public interface ClientService {
    // get
    List<String> getMenu();
    List<String> getAllDrinkCompositions();
    List<String> getMostPopularDrinks();
    int getPrice(String drinkName);

    // make
    int makeCoffee(String coffeeName, String clientName, String payMethod);
}
