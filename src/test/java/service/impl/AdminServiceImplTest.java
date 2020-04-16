package service.impl;

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
import service.AdminService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

public class AdminServiceImplTest {

    @Mock
    RecipeDAOImpl recipeDAO;

    @Mock
    DrinkCompositionDAOImpl drinkCompositionDAO;

    @Mock
    IngredientDAOImpl ingredientDAO;

    @Mock
    SaleDAOImpl saleDAO;

    @InjectMocks
    AdminServiceImpl adminService;

    List<Recipe> mockRecipeList;
    List<DrinkComposition> mockDrinkCompositionList;
    List<Ingredient> mockIngredientList;
    List<Sale> mockSaleList;

    AdminServiceImplTest() {
        mockIngredientList = new ArrayList<>();
        mockIngredientList.add(new Ingredient(10, "Ingredient One", 1000, "un"));
        mockIngredientList.add(new Ingredient(20, "Ingredient Two", 1000, "un"));

        mockRecipeList = new ArrayList<>();
        mockRecipeList.add(new Recipe(100, "Test", 500, true, 42));
        mockRecipeList.add(new Recipe(200, "Unavailable recipe", 300, false, 10));

        mockDrinkCompositionList = new ArrayList<>();
        mockDrinkCompositionList.add(new DrinkComposition(100, 10, 40));
        mockDrinkCompositionList.add(new DrinkComposition(100, 20, 7));

        mockSaleList = new ArrayList<>();
        mockSaleList.add(new Sale(500, "Test", 1, 500, 500,
                0, new Date(1234567), "Test Client"));
    }

    @BeforeMethod
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllIngredients() throws SQLException {
        when(ingredientDAO.getAll()).thenReturn(mockIngredientList);

        assertEquals(adminService.getAllIngredients().size(), 2);
    }

    @Test
    public void testGetEndingIngredientsEmpty() throws SQLException {
        when(ingredientDAO.getAll()).thenReturn(mockIngredientList);

        assertEquals(adminService.getEndingIngredients(10).size(), 0);
    }

    @Test
    public void testGetEndingIngredientsNotEmpty() throws SQLException {
        when(ingredientDAO.getAll()).thenReturn(mockIngredientList);

        assertEquals(adminService.getEndingIngredients(1000).size(), 2);
    }

    @Test
    public void testGetAllRecipes() throws SQLException {
        when(recipeDAO.getAll()).thenReturn(mockRecipeList);

        assertEquals(adminService.getAllRecipes().size(), 2);
    }

    @Test
    public void testGetUnavailableRecipes() throws SQLException {
        when(recipeDAO.getByParameter("available", "0"))
                .thenReturn(mockRecipeList.subList(1, mockRecipeList.size()));

        assertEquals(adminService.getUnavailableRecipes().size(), 1);
    }

    @Test
    public void testGetAllSales() throws SQLException {
        when(saleDAO.getAll()).thenReturn(mockSaleList);

        assertEquals(adminService.getAllSales().size(), 1);
    }

    @Test
    public void testGetSalesTodayEmpty() throws SQLException {
        when(saleDAO.getAll()).thenReturn(mockSaleList);

        assertEquals(adminService.getSalesToday().size(), 0);
    }

    @Test
    public void testGetRecipeIdByName() throws SQLException {
        when(recipeDAO.getByParameter("name", "Test")).thenReturn(mockRecipeList.subList(0, 1));
        when(recipeDAO.getByParameter("name", "Unavailable recipe"))
                .thenReturn(mockRecipeList.subList(1, mockRecipeList.size()));

        assertEquals(adminService.getRecipeIdByName("Test"), 100);
        assertEquals(adminService.getRecipeIdByName("Unavailable recipe"), 200);
    }

    @Test
    public void testGetIngredientIdByName() throws SQLException {
        when(ingredientDAO.getByParameter("name", "Ingredient One")).thenReturn(mockIngredientList.subList(0, 1));

        assertEquals(adminService.getIngredientIdByName("Ingredient One"), 10);
    }

    @Test
    public void testAddExistingIngredient() throws SQLException {
        when(ingredientDAO.getByParameter("name", "Ingredient One")).thenReturn(mockIngredientList.subList(0, 1));
        when(ingredientDAO.insert(any())).thenReturn(1);
        when(ingredientDAO.update(anyInt(), any())).thenReturn(1);

        Ingredient existingIngredient = mockIngredientList.get(0);
        existingIngredient.setBalance(777);
        assertEquals(adminService.addIngredient(existingIngredient), 1);
        verify(ingredientDAO).update(10, existingIngredient);
    }

    @Test
    public void testAddNewIngredient() throws SQLException {
        when(ingredientDAO.getByParameter("name", "Ingredient One")).thenReturn(mockIngredientList.subList(0, 1));
        when(ingredientDAO.insert(any())).thenReturn(1);
        when(ingredientDAO.update(anyInt(), any())).thenReturn(1);

        Ingredient newIngredient = new Ingredient(1000, "New Ingredient", 100, "ml");
        assertEquals(adminService.addIngredient(newIngredient), 1);
        verify(ingredientDAO).insert(newIngredient);
    }

    @Test
    public void testAddRecipe() throws SQLException {
        when(recipeDAO.insert(any())).thenReturn(1);

        assertEquals(adminService.addRecipe(any()), 1);
        verify(recipeDAO).insert(any());
    }

    @Test
    public void testAddDrinkCompositions() throws SQLException {
        when(drinkCompositionDAO.insert(any())).thenReturn(1);

        List<DrinkComposition> newList = new ArrayList<>(mockDrinkCompositionList);
        newList.forEach(o -> o.setAmount(o.getAmount() + 100));
        assertEquals(adminService.addDrinkCompositions(newList), 1);
        verify(drinkCompositionDAO, times(2)).insert(any());
    }

    @Test
    public void testDeleteExistingRecipeByName() throws SQLException, AdminService.AdminServiceException {
        when(recipeDAO.getByParameter("name", "Test")).thenReturn(mockRecipeList.subList(0, 1));
        when(recipeDAO.delete(100)).thenReturn(1);

        assertEquals(adminService.deleteRecipeByName("Test"), 1);
        verify(recipeDAO).delete(any());
    }

    @Test(expectedExceptions = AdminService.AdminServiceException.class)
    public void testDeleteNotExistingRecipeByName() throws SQLException, AdminService.AdminServiceException {
        when(recipeDAO.getByParameter("name", "Not Existing recipe")).thenReturn(new ArrayList<>());

        adminService.deleteRecipeByName("Not Existing recipe");
    }
}