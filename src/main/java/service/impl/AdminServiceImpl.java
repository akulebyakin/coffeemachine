package service.impl;

import model.Ingredient;
import model.Recipe;
import model.Sale;
import service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<Ingredient> getAllIngredients() {
        return null;
    }

    @Override
    public List<Ingredient> getEndingIngredients(int minBalance) {
        return null;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return null;
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
        return null;
    }

    @Override
    public List<Sale> getSalesToday() {
        return null;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return false;
    }

    @Override
    public boolean changeIngredientByName(int id, Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean changeRecipeByName(int id, Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean deleteIngredientByName(int id) {
        return false;
    }

    @Override
    public boolean deleteRecipeByName(int id) {
        return false;
    }
}
