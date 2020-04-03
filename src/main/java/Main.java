import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import model.Ingredient;

import java.util.List;

public class Main {
    private static IngredientDAOImpl ingredientDAO;
    private static RecipeDAOImpl recipeDAO;

    public static void main(String[] args) {
        try {
            ingredientDAO = new IngredientDAOImpl();
            List<Ingredient> list = ingredientDAO.getAll();
            for (Ingredient ingredient : list) {
                System.out.println(ingredient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            recipeDAO = new RecipeDAOImpl();
//            System.out.println(recipeDAO.get(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
