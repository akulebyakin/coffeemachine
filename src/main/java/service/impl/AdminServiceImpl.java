package service.impl;

import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import dao.impl.SaleDAOImpl;
import model.Ingredient;
import model.Recipe;
import model.Sale;
import service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<Ingredient> getAllIngredients() {
        return new IngredientDAOImpl().getAll();
    }

    @Override
    public List<Ingredient> getEndingIngredients(int minBalance) {
        return null;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return new RecipeDAOImpl().getAll();
    }

    @Override
    public List<Recipe> getAvailableRecipes() {
        return null;
    }

    @Override
    public List<Recipe> getUnavailableRecipes() {
        return null;
    }

    @Override
    public List<Sale> getAllSales() {
        return new SaleDAOImpl().getAll();
    }

    @Override
    public List<Sale> getSalesToday() {
        return null;
    }

    @Override
    public int addIngredient(Ingredient ingredient) {
        IngredientDAOImpl ingredientDAO = new IngredientDAOImpl();
        return ingredientDAO.insert(ingredient);
    }

    @Override
    public int addRecipe(Recipe recipe) {
        return -1;
    }

    @Override
    public int changeIngredientByName(int id, Ingredient ingredient) {
        return -1;
    }

    @Override
    public int changeRecipeByName(int id, Ingredient ingredient) {
        return -1;
    }

    @Override
    public int deleteIngredientByName(int id) {
        return -1;
    }

    @Override
    public int deleteRecipeByName(int id) {
        return -1;
    }
}
