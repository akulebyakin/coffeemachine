package service.impl;

import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import dao.impl.SaleDAOImpl;
import model.Ingredient;
import model.Recipe;
import model.Sale;
import service.ClientService;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    // output example: "1. Espresso - 105 rub."
    @Override
    public List<String> getMenu() {
        List<String> menu = new ArrayList<>();
        for (Recipe recipe : new RecipeDAOImpl().getAll()) {
            menu.add(recipe.getId() + ". " + recipe.getName() + " - " + recipe.getPrice() + " rub. " +
                    (recipe.isAvailable() ? "" : "Not available!"));
        }
        return menu;
    }

    /*  output example:
        1. Espresso:
            Water - 40 ml
            Coffee - 7 g
     */
    @Override
    public List<String> getDrinkComposition() {
        List<String> drinkCompositionList = new ArrayList<>();
        RecipeDAOImpl recipeDAO = new RecipeDAOImpl();
        IngredientDAOImpl ingredientDAO = new IngredientDAOImpl();

        for (Recipe recipe : recipeDAO.getAll()) {
            String compositionItem = recipe.getId() + ". " + recipe.getName() + ":\n";
            String[] ingredient_ids = recipe.getIngredient_ids().split(",");
            String[] ingredient_amount = recipe.getIngredient_amount().split(",");

            for (int i = 0; i < ingredient_ids.length; i++) {
                int id = Integer.parseInt(ingredient_ids[i].replaceAll("\\D", ""));
                int amount = Integer.parseInt(ingredient_amount[i].replaceAll("\\D", ""));
                Ingredient ingredient = ingredientDAO.get(id);
                compositionItem += "\t" + ingredient.getName() +
                        " - " + amount + " " + ingredient.getUnit() + "\n";
            }
            drinkCompositionList.add(compositionItem);
        }
        return drinkCompositionList;
    }

    @Override
    public List<String> getMostPopularDrinks() {
        return null;
    }

    @Override
    public int makeCoffee(String coffeeName, String clientName, String payMethod) {
        SaleDAOImpl saleDAO = new SaleDAOImpl();
        Recipe recipe = new RecipeDAOImpl().getByParameter("name", coffeeName);
        Sale sale = new Sale(coffeeName, 1, clientName);
        int price = recipe.getPrice();

        sale.setTotalPrice(price);
        if (payMethod.toLowerCase().equals("card")) {
            sale.setPaidByCard(price);
        } else {
            sale.setPaidByCash(price);
        }

        return saleDAO.insert(sale);
    }
}
