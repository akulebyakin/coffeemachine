package service.impl;

import dao.impl.DrinkCompositionDAOImpl;
import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import model.DrinkComposition;
import model.Ingredient;
import model.Recipe;
import service.ClientService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    // output example: "1. Espresso - 105 rub."
    @Override
    public List<String> getMenu() {
        List<String> menu = new ArrayList<>();
        try {
            for (Recipe recipe : new RecipeDAOImpl().getAll()) {
                menu.add(recipe.getId() + ". " + recipe.getName() + " - " + recipe.getPrice() + " rub. " +
                        (recipe.isAvailable() ? "" : "Not available!"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    /*  output example:
        1. Espresso:
            Water - 40 ml
            Coffee - 7 g
     */
    @Override
    public List<String> getAllDrinkCompositions() {
        List<String> listResult = new ArrayList<>();
        try {
            DrinkCompositionDAOImpl drinkCompositionDAO = new DrinkCompositionDAOImpl();
            RecipeDAOImpl recipeDAO = new RecipeDAOImpl();
            IngredientDAOImpl ingredientDAO = new IngredientDAOImpl();

            for (Recipe recipe : recipeDAO.getAll()) {
                String compositionItem = recipe.getId() + ". " + recipe.getName() + ":";

                List<DrinkComposition> drinkCompositionList = drinkCompositionDAO
                        .getByParameter("recipe_id", String.valueOf(recipe.getId()));

                for (DrinkComposition drinkComposition : drinkCompositionList) {
                    int ingredient_id = drinkComposition.getIngredientId();
                    int amount = drinkComposition.getAmount();
                    Ingredient ingredient = ingredientDAO.get(ingredient_id);
                    compositionItem += "\n\t" + ingredient.getName() +
                            " - " + amount + " " + ingredient.getUnit();
                }
                listResult.add(compositionItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    @Override
    public List<String> getMostPopularDrinks() {
        return null;
    }

    @Override
    public int makeCoffee(String coffeeName, String clientName, String payMethod) {
//        try {
//            SaleDAOImpl saleDAO = new SaleDAOImpl();
//            RecipeDAOImpl recipeDAO = new RecipeDAOImpl();
//            IngredientDAOImpl ingredientDAO = new IngredientDAOImpl();
//
//            Recipe recipe = recipeDAO.getByParameter("name", coffeeName);
//            Sale sale = new Sale(coffeeName, 1, clientName);
//            int price = recipe.getPrice();
//
//            // minus consumables
//            int[] ingredient_ids = parseSQLArrayToInteger(recipe.getIngredient_ids());
//            int[] ingredient_amount = parseSQLArrayToInteger(recipe.getIngredient_amount());
//
//            for (int i = 0; i < ingredient_ids.length; i++) {
//                Ingredient ingredient = ingredientDAO.get(ingredient_ids[i]);
//                int newBalance = ingredient.getBalance() - ingredient_amount[i];
//                if (newBalance >= 0) {
//                    ingredient.setBalance(newBalance);
//                    ingredientDAO.update(ingredient.getId(), ingredient);
//                } else {
//                    System.out.println("Sorry! Can't make '" + coffeeName + "', not enough '" + ingredient.getName() + "' ");
//                    recipe.setAvailable(false);
//                    recipeDAO.update(recipe.getId(), recipe);
//                    return -1;
//                }
//            }
//
//            // set paid_by_cash or paid_by_card
//            sale.setTotalPrice(price);
//            if (payMethod.toLowerCase().equals("card")) {
//                sale.setPaidByCard(price);
//            } else {
//                sale.setPaidByCash(price);
//            }
//
//            // total_sold++
//            recipe.setTotalSold(recipe.getTotalSold() + 1);
//            recipeDAO.update(recipe.getId(), recipe);
//
//            return saleDAO.insert(sale);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return -1;
    }
}
