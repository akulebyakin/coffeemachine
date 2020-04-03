import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;

public class Main {
    private static IngredientDAOImpl ingredientDAO;
    private static RecipeDAOImpl recipeDAO;

    public static void main(String[] args) {
        try {
            ingredientDAO = new IngredientDAOImpl();
//            List<Ingredient> list = ingredientDAO.getAll();
//            for (Ingredient ingredient : list) {
//                System.out.println(ingredient);
//            }
            System.out.println(ingredientDAO.getByParameter("name", "Sugar"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            recipeDAO = new RecipeDAOImpl();
//            System.out.println(recipeDAO.get(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        recipeDAO = new RecipeDAOImpl();
//        List<Recipe> list = recipeDAO.getAll();
//        for (Recipe recipe : list) {
//            System.out.println(recipe);
//        }


    }
}
