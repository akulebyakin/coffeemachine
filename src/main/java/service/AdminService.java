package service;

import model.Ingredient;
import model.Recipe;
import model.Sale;

import java.util.List;

public interface AdminService {
    // get
    List<Ingredient> getAllIngredients();
    List<Ingredient> getEndingIngredients(int minBalance);
    List<Recipe> getAllRecipes();
    List<Recipe> getAvailableRecipes();
    List<Recipe> getUnavailableRecipes();
    List<Sale> getAllSales();
    List<Sale> getSalesToday();

    // make
    int addIngredient(Ingredient ingredient);
    int addRecipe(Recipe recipe);
    int changeIngredientByName(int id, Ingredient ingredient);
    int changeRecipeByName(int id, Ingredient ingredient);
    int deleteIngredientByName(int id);
    int deleteRecipeByName(int id);
}
