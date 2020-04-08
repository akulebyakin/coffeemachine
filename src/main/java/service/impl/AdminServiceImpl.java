package service.impl;

import dao.impl.DrinkCompositionDAOImpl;
import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import dao.impl.SaleDAOImpl;
import model.Ingredient;
import model.Recipe;
import model.Sale;
import service.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private IngredientDAOImpl ingredientDAO;
    private RecipeDAOImpl recipeDAO;
    private DrinkCompositionDAOImpl drinkCompositionDAO;
    private SaleDAOImpl saleDAO;

    public AdminServiceImpl() throws SQLException {
        ingredientDAO = new IngredientDAOImpl();
        recipeDAO = new RecipeDAOImpl();
        drinkCompositionDAO = new DrinkCompositionDAOImpl();
        saleDAO = new SaleDAOImpl();
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        try {
            allIngredients = ingredientDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allIngredients;
    }

    @Override
    public List<Ingredient> getEndingIngredients(int minBalance) {
        return null;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try {
            recipes = recipeDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
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
        List<Sale> sales = new ArrayList<>();
        try {
            sales = saleDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesToday() {
        return null;
    }

    @Override
    public int addIngredient(Ingredient ingredient) {
        try {
            return ingredientDAO.insert(ingredient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
