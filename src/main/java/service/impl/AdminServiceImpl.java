package service.impl;

import dao.impl.DrinkCompositionDAOImpl;
import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import dao.impl.SaleDAOImpl;
import model.DrinkComposition;
import model.Ingredient;
import model.Recipe;
import model.Sale;
import service.AdminService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Ingredient> endingIngredientsList = new ArrayList<>();
        try {
            for (Ingredient ingredient : ingredientDAO.getAll()) {
                if (ingredient.getBalance() <= minBalance)
                    endingIngredientsList.add(ingredient);
            }
            endingIngredientsList.sort((Comparator.comparingInt(Ingredient::getBalance)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endingIngredientsList;
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
    public List<Recipe> getUnavailableRecipes() {
        List<Recipe> availableRecipeList = new ArrayList<>();
        try {
            availableRecipeList = recipeDAO.getByParameter("available", "0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableRecipeList;
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
        List<Sale> saleList = new ArrayList<>();
        try {
            saleList = saleDAO.getAll();
            saleList.removeIf(o -> {
                SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
                Date sqlDate = o.getDate();
                Date currentDate = new Date();

                return !(formatter.format(sqlDate).equals(formatter.format(currentDate)));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleList;
    }

    @Override
    public int getRecipeIdByName(String name) {
        try {
            return recipeDAO.getByParameter("name", name).get(0).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getIngredientIdByName(String name) {
        try {
            return ingredientDAO.getByParameter("name", name).get(0).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int addIngredient(Ingredient ingredient) {
        try {
            List<Ingredient> check = ingredientDAO.getByParameter("name", ingredient.getName());
            if (check.isEmpty())
                return ingredientDAO.insert(ingredient);
            else
                return ingredientDAO.update(check.get(0).getId(), ingredient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int addRecipe(Recipe recipe) {
        try {
            return recipeDAO.insert(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int addDrinkCompositions(List<DrinkComposition> drinkCompositionList) {
        try {
            for (DrinkComposition drinkComposition : drinkCompositionList) {
                drinkCompositionDAO.insert(drinkComposition);
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteRecipeByName(String name) throws AdminServiceException {
        try {
            List<Recipe> recipeList = recipeDAO.getByParameter("name", name);
            if (recipeList.isEmpty()) {
                throw new AdminServiceException("There is no recipe with that name.");
            }
            for (Recipe recipe : recipeList) {
                recipeDAO.delete(recipe.getId());
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
