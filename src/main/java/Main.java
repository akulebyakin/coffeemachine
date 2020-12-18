import dao.impl.ConnectionFactory;
import model.DrinkComposition;
import model.Ingredient;
import model.Recipe;
import service.AdminService;
import service.ClientService;
import service.impl.AdminServiceImpl;
import service.impl.ClientServiceImpl;
import tools.Printer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String userName = "John Dow";
    private static String userRole = "client";
    private static Scanner scanner;

    private static ClientService clientService;
    private static AdminService adminService;

    public static void main(String[] args) {
        try {
            clientService = new ClientServiceImpl();
            adminService = new AdminServiceImpl();
            scanner = new Scanner(System.in);

            System.out.println("Welcome to the Red Tiger Coffee House! Please tell me your name.");
            System.out.print("My name is: ");

            userName = scanner.nextLine();
            if (userName.toLowerCase().equals("admin"))
                userRole = "admin";

            System.out.println("What do you want" + (userRole.equals("admin") ? ", admin?" : "?"));

            String operation = "";

            while (!operation.equals("exit")) {
                try {
                    System.out.print("Command: ");
                    operation = scanner.nextLine();
                    if (operation.matches("make .+")) {
                        String coffeeName = operation.substring(4).trim();
                        clientCommandMakeCoffee(coffeeName);
                        continue;
                    }
                    switch (operation) {
                        case "help": {
                            printAllCommands();
                            break;
                        }
                        // client service
                        case "menu": {
                            clientCommandMenu();
                            break;
                        }
                        case "getAllDrinkCompositions": {
                            clientCommandGetAllDrinkCompositions();
                            break;
                        }
                        case "getMostPopularDrinks": {
                            clientCommandGetMostPopularDrinks();
                            break;
                        }
                        // admin service
                        case "getAllRecipes": {
                            if (isClient()) break;
                            clientCommandGetAllRecipes();
                            break;
                        }
                        case "getAllIngredients": {
                            if (isClient()) break;
                            adminCommandGetAllIngredients();
                            break;
                        }
                        case "getEndingIngredients": {
                            if (isClient()) break;
                            adminCommandGetEndingIngredients();
                            break;
                        }
                        case "getUnavailableRecipes": {
                            if (isClient()) break;
                            adminCommandGetUnavailableRecipes();
                            break;
                        }
                        case "getAllSales": {
                            if (isClient()) break;
                            adminCommandGetAllSales();
                            break;
                        }
                        case "getSalesToday": {
                            if (isClient()) break;
                            adminCommandGetSalesToday();
                            break;
                        }
                        case "addIngredient": {
                            if (isClient()) break;
                            adminCommandAddIngredient();
                            break;
                        }
                        case "createNewRecipe": {
                            if (isClient()) break;
                            adminCommandCreateNewRecipe();
                            break;
                        }
                        case "deleteRecipeByName": {
                            if (isClient()) break;
                            adminCommandDeleteRecipeByName();
                            break;
                        }
                        case "exit": {
                            System.out.println("Bye-bye! :)");
                            break;
                        }
                        default:
                            System.out.println("I don't know that command. Try again.");
                    }
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        }

    }

    private static boolean isClient() {
        if (userRole.equals("client")) {
            System.out.println("Sorry, but only admin can do it");
            return true;
        }
        return false;
    }

    private static void clientCommandMakeCoffee(String coffeeName) {
        int price = clientService.getCoffeePrice(coffeeName);
        if (price == -1) {
            System.out.println("There is no drink with this name.");
            return;
        }
        System.out.println("Price is: " + price + " rub. Will you pay by card or cash?");
        System.out.print("Pay method (cash/card): ");
        String payMethod = scanner.nextLine();
        System.out.println("I'm making '" + coffeeName + "'");
        int i = 0;
        try {
            i = clientService.makeCoffee(coffeeName, userName, payMethod);
        } catch (ClientService.ClientServiceException e) {
            System.out.println(e.getMessage());
        }
        if (i == 1) {
            System.out.println("Done! Drinks made: " + i);
        } else {
            System.out.println("I see some shit... problem. Result " + i);
        }
    }

    private static void printAllCommands() {
        String[] clientCommands = {
                "menu - get the menu",
                "make Cappuccino - makes Cappuccino. Put any drink name instead of 'Cappuccino'." +
                        " If a drink with the name exists, it will be made",
                "getAllDrinkCompositions - get compositions of all drinks",
                "getMostPopularDrinks - get a list with 5 most popular drinks",
        };

        String[] adminCommands = {
                "getAllIngredients - get a list with all ingredients",
                "getEndingIngredients - get a list with ending ingredients",
                "getAllRecipes - display all recipes",
                "getUnavailableRecipes - display all unavailable recipes",
                "getAllSales - list of all sales",
                "getSalesToday - list of only today sales",
                "addIngredient - create new one or upgrade existing ingredient",
                "createNewRecipe - cmake a new drink",
                "deleteRecipeByName - delete a recipe. Notice: Composition of a drink will be deleted.",
        };

        System.out.println("List of all client commands:");
        Arrays.stream(clientCommands)
                .forEach(s -> System.out.println("\t" + s));
        if (userRole.equals("admin")) {
            System.out.println();
            System.out.println("List of all admin commands:");
            Arrays.stream(adminCommands)
                    .forEach(s -> System.out.println("\t" + s));
        }
    }

    private static void clientCommandMenu() {
        Printer.printList(clientService.getMenu());
    }

    private static void clientCommandGetAllDrinkCompositions() {
        Printer.printList(clientService.getAllDrinkCompositions());
    }

    private static void clientCommandGetMostPopularDrinks() {
        Printer.printList(clientService.getMostPopularDrinks(5));
    }

    private static void clientCommandGetAllRecipes() {
        Printer.printList(adminService.getAllRecipes());
    }

    private static void adminCommandGetAllIngredients() {
        Printer.printList(adminService.getAllIngredients());
    }

    private static void adminCommandGetEndingIngredients() {
        int minBalance = 0;
        while (minBalance == 0) {
            System.out.print("Min Balance: ");
            try {
                minBalance = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("NaN! Try again.");
            }
        }
        Printer.printList(adminService.getEndingIngredients(minBalance));
    }

    private static void adminCommandGetUnavailableRecipes() {
        Printer.printList(adminService.getUnavailableRecipes());
    }

    private static void adminCommandGetAllSales() {
        Printer.printList(adminService.getAllSales());
    }

    private static void adminCommandGetSalesToday() {
        Printer.printList(adminService.getSalesToday());
    }

    private static void adminCommandAddIngredient() {
        System.out.print("Ingredient name: ");
        String name = scanner.nextLine();
        int balance = 0;
        while (balance == 0) {
            System.out.print("balance: ");
            try {
                balance = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("NaN! Try again.");
            }
        }
        System.out.print("unit: ");
        String unit = scanner.nextLine();

        Ingredient ingredient = new Ingredient(name, balance, unit);
        int i = adminService.addIngredient(ingredient);
        if (i == 1)
            System.out.println("Success");
    }

    private static void adminCommandCreateNewRecipe() {
        System.out.print("Drink name: ");
        String recipeName = scanner.nextLine();
        int price = 0;
        while (price == 0) {
            System.out.print("price: ");
            try {
                price = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("NaN! Try again.");
            }
        }
        Recipe recipe = new Recipe(recipeName, price);
        int i = adminService.addRecipe(recipe);
        if (i != 1) {
            System.out.println("Something wrong. Im stopping");
            return;
        }

        // Drink Compositions
        List<DrinkComposition> drinkCompositionList = new ArrayList<>();
        System.out.println("Now choose ingredients. Type 'stop' instead of name when all ingredients will be added");
        while (true) {
            System.out.print("name: ");
            String ingredientName = scanner.nextLine();
            if (ingredientName.equals("stop"))
                break;
            int amount = 0;
            while (amount == 0) {
                System.out.print("amount: ");
                try {
                    amount = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("NaN! Try again.");
                }
            }
            int recipe_id = adminService.getRecipeIdByName(recipeName);
            int ingredient_id = adminService.getIngredientIdByName(ingredientName);
            if (recipe_id > 0 && ingredient_id > 0)
                drinkCompositionList.add(new DrinkComposition(recipe_id, ingredient_id, amount));
            else
                System.out.println("There is no ingredient with that name. Try again");
        }

        int j = adminService.addDrinkCompositions(drinkCompositionList);
        if (j == 1)
            System.out.println("Success");
    }

    private static void adminCommandDeleteRecipeByName() {
        String recipeName = "";
        while (recipeName.equals("")) {
            System.out.print("recipe name: ");
            recipeName = scanner.nextLine();
            int i;
            try {
                i = adminService.deleteRecipeByName(recipeName);
            } catch (AdminService.AdminServiceException e) {
                System.out.println(e.getMessage());
                recipeName = "";
                continue;
            }
            if (i == 1) {
                System.out.println("Success");
            }
            break;
        }
    }
}