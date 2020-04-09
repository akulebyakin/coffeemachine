package service.impl;

import dao.impl.DrinkCompositionDAOImpl;
import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import dao.impl.SaleDAOImpl;
import model.DrinkComposition;
import model.Ingredient;
import model.Recipe;
import model.Sale;
import service.ClientService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private IngredientDAOImpl ingredientDAO;
    private RecipeDAOImpl recipeDAO;
    private DrinkCompositionDAOImpl drinkCompositionDAO;
    private SaleDAOImpl saleDAO;

    public ClientServiceImpl() throws SQLException {
        ingredientDAO = new IngredientDAOImpl();
        recipeDAO = new RecipeDAOImpl();
        drinkCompositionDAO = new DrinkCompositionDAOImpl();
        saleDAO = new SaleDAOImpl();
    }

    // output example: "1. Espresso - 105 rub."
    @Override
    public List<String> getMenu() {
        List<String> menu = new ArrayList<>();
        try {
            for (Recipe recipe : recipeDAO.getAll()) {
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
    public List<String> getMostPopularDrinks(int limit) {
        List<String> mostPopularDrinksList = new ArrayList<>();
        try {
            List<Recipe> recipeList = recipeDAO.getAll();
            recipeList.sort(Comparator.comparingInt(Recipe::getTotalSold).reversed());
            for (Recipe recipe : recipeList) {
                mostPopularDrinksList.add(recipe.getName() + " (Total sold: " + recipe.getTotalSold() + ")");
            }
            mostPopularDrinksList = mostPopularDrinksList.subList(0, limit + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostPopularDrinksList;
    }

    @Override
    public int makeCoffee(String coffeeName, String clientName, String payMethod) throws ClientServiceException {
        String needMoreGold = "Need more gold! I mean not enough consumables: ";
        try {
            // we have only one recipe with unique name
            Recipe recipe = recipeDAO.getByParameter("name", coffeeName).get(0);
            List<DrinkComposition> drinkCompositionList = drinkCompositionDAO
                    .getByParameter("recipe_id", String.valueOf(recipe.getId()));

            // check if its enough consumables on warehouse
            List<Ingredient> ingredientList = new ArrayList<>();
            boolean notEnoughGold = false;
            for (DrinkComposition drinkComposition : drinkCompositionList) {
                int ingredient_id = drinkComposition.getIngredientId();
                Ingredient ingredient = ingredientDAO.get(ingredient_id);
                if (ingredient.getBalance() < drinkComposition.getAmount()) {
                    notEnoughGold = true;
                    needMoreGold += " " + ingredient.getName();
                }
                ingredientList.add(ingredient);
            }
            if (notEnoughGold) {
                recipe.setAvailable(false);
                recipeDAO.update(recipe.getId(), recipe);
                throw new ClientServiceException(needMoreGold);
            } else {
                recipe.setAvailable(true);
                recipeDAO.update(recipe.getId(), recipe);
            }

            // minus consumables
            for (int i = 0; i < ingredientList.size(); i++) {
                int newBalance = ingredientList.get(i).getBalance() - drinkCompositionList.get(i).getAmount();
                ingredientList.get(i).setBalance(newBalance);
                ingredientDAO.update(ingredientList.get(i).getId(), ingredientList.get(i));
            }

            // set paid_by_cash or paid_by_card
            Sale sale = new Sale(coffeeName, 1, clientName);
            int price = recipe.getPrice();
            sale.setTotalPrice(price);
            if (payMethod.toLowerCase().equals("card")) {
                sale.setPaidByCard(price);
            } else {
                sale.setPaidByCash(price);
            }

            // total_sold++
            recipe.setTotalSold(recipe.getTotalSold() + 1);
            recipeDAO.update(recipe.getId(), recipe);

            return saleDAO.insert(sale);
        } catch (ClientServiceException e) {
            throw new ClientServiceException(e.getMessage());
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int getPrice(String drinkName) {
        try {
            return recipeDAO.getByParameter("name", drinkName).get(0).getPrice();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}