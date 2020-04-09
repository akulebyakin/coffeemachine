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
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String userName = "John Dow";
    private static String userRole = "nobody";
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            ClientService clientService = new ClientServiceImpl();
            AdminService adminService = new AdminServiceImpl();

            System.out.println("Welcome to the Red Tiger Coffee House! Please tell me your name.");
            System.out.print("My name is: ");

            scanner = new Scanner(System.in);

            userName = scanner.nextLine();

            System.out.println("What do you want?");

            String operation = "";

            while (!operation.equals("exit")) {
                try {
                    System.out.print("Operation: ");
                    operation = scanner.nextLine();
                    if (operation.matches("make .+")) {
                        String coffeeName = operation.substring(4).trim();
                        int price = clientService.getPrice(coffeeName);
                        if (price == -1) {
                            System.out.println("There is no drink with this name.");
                            continue;
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
                        continue;
                    }
                    switch (operation) {
                        // client service
                        case "menu": {
                            Printer.printList(clientService.getMenu());
                            break;
                        }
                        case "getAllDrinkCompositions": {
                            Printer.printList(clientService.getAllDrinkCompositions());
                            break;
                        }
                        case "getMostPopularDrinks": {
                            Printer.printList(clientService.getMostPopularDrinks(5));
                            break;
                        }
                        // admin service
                        case "getAllRecipes": {
                            Printer.printList(adminService.getAllRecipes());
                            break;
                        }
                        case "getAllIngredients": {
                            Printer.printList(adminService.getAllIngredients());
                            break;
                        }
                        case "getEndingIngredients": {
                            System.out.print("Min Balance: ");
                            int minBalance = scanner.nextInt();
                            Printer.printList(adminService.getEndingIngredients(minBalance));
                            break;
                        }
                        case "getUnavailableRecipes": {
                            Printer.printList(adminService.getUnavailableRecipes());
                            break;
                        }
                        case "getAllSales": {
                            Printer.printList(adminService.getAllSales());
                            break;
                        }
                        case "getSalesToday": {
                            Printer.printList(adminService.getSalesToday());
                            break;
                        }
                        case "addIngredient": {
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
                            break;
                        }
                        case "createNewRecipe": {
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
                                break;
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
                            break;
                        }
                        default:
                            System.out.println("I don't know that operation. Try again.");
                    }
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
}
