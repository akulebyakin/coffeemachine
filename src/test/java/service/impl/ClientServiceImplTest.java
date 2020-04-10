package service.impl;

import com.beust.jcommander.internal.Lists;
import dao.impl.DrinkCompositionDAOImpl;
import dao.impl.IngredientDAOImpl;
import dao.impl.RecipeDAOImpl;
import dao.impl.SaleDAOImpl;
import model.DrinkComposition;
import model.Ingredient;
import model.Recipe;
import model.Sale;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.testng.Assert.assertEquals;

public class ClientServiceImplTest {
    @Mock
    RecipeDAOImpl recipeDAO;

    @Mock
    DrinkCompositionDAOImpl drinkCompositionDAO;

    @Mock
    IngredientDAOImpl ingredientDAO;

    @Mock
    SaleDAOImpl saleDAO;

    @InjectMocks
    ClientServiceImpl clientService;

    List<Recipe> mockRecipeList;
    List<DrinkComposition> mockDrinkCompositionList;
    List<Ingredient> mockIngredientList;
    List<Sale> saleDaoList;

    ClientServiceImplTest () {
        mockIngredientList = new ArrayList<>();
        mockIngredientList.add(new Ingredient(10, "Ingredient One", 1000, "un"));
        mockIngredientList.add(new Ingredient(20, "Ingredient Two", 1000, "un"));

        mockRecipeList = new ArrayList<>();
        mockRecipeList.add(new Recipe(100, "Test", 500, true, 42));

        mockDrinkCompositionList = new ArrayList<>();
        mockDrinkCompositionList.add(new DrinkComposition(100, 10, 40));
        mockDrinkCompositionList.add(new DrinkComposition(100, 20, 7));

        saleDaoList = new ArrayList<>();
        saleDaoList.add(new Sale(500, "Test", 1, 500, 500,
                0, new Date(1234567), "Test Client"));
    }

    @BeforeMethod
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);

        given(recipeDAO.getAll()).willReturn(mockRecipeList);
        given(recipeDAO.getByParameter("id", "100")).willReturn(mockRecipeList);
        given(drinkCompositionDAO.getByParameter("recipe_id", "100")).willReturn(mockDrinkCompositionList);
        given(ingredientDAO.get(10)).willReturn(mockIngredientList.get(0));
        given(ingredientDAO.get(20)).willReturn(mockIngredientList.get(1));
    }

    @Test
    public void testGetMenu() {
        List<String> serviceMenu = clientService.getMenu();
        assertEquals(serviceMenu.get(0), Lists.newArrayList("100. Test - 500 rub.").get(0));
    }

    @Test
    public void testGetAllDrinkCompositions() {
        List<String> serviceMenu = clientService.getAllDrinkCompositions();
        String expectedResult = "100. Test:\n" +
                "\tIngredient One - 40 un\n" +
                "\tIngredient Two - 7 un";
        assertEquals(serviceMenu.get(0), expectedResult);
    }

    @Test
    public void testGetMostPopularDrinks() {

    }

    @Test
    public void testMakeCoffee() {
    }

    @Test
    public void testGetPrice() {
    }
}