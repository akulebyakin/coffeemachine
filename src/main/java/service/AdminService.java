package service;

import model.DrinkComposition;
import model.Ingredient;
import model.Recipe;
import model.Sale;

import java.util.List;

public interface AdminService {
    // get
    List<Ingredient> getAllIngredients();
    List<Ingredient> getEndingIngredients(int minBalance);
    List<Recipe> getAllRecipes();
    List<Recipe> getUnavailableRecipes();
    List<Sale> getAllSales();
    List<Sale> getSalesToday();
    int getRecipeIdByName(String name);
    int getIngredientIdByName(String name);

    // make
    int addIngredient(Ingredient ingredient);
    int addRecipe(Recipe recipe);
    int addDrinkCompositions(List<DrinkComposition> drinkCompositionList);
    int deleteIngredientByName(int id);
    int deleteRecipeByName(int id);
}
