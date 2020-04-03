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
    boolean addIngredient(Ingredient ingredient);
    boolean addRecipe(Recipe recipe);
    boolean changeIngredientByName(int id, Ingredient ingredient);
    boolean changeRecipeByName(int id, Ingredient ingredient);
    boolean deleteIngredientByName(int id);
    boolean deleteRecipeByName(int id);
}
