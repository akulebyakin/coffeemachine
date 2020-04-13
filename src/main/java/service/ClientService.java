package service;

import java.util.List;

public interface ClientService {
    // get
    List<String> getMenu();
    List<String> getAllDrinkCompositions();
    List<String> getMostPopularDrinks(int limit);
    int getCoffeePrice(String drinkName);

    // make
    int makeCoffee(String coffeeName, String clientName, String payMethod) throws ClientServiceException;

    class ClientServiceException extends Exception {
        public ClientServiceException(String errorMessage) {
            super(errorMessage);
        }
    }
}
