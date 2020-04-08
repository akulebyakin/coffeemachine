import dao.impl.ConnectionFactory;
import service.AdminService;
import service.ClientService;
import service.impl.AdminServiceImpl;
import service.impl.ClientServiceImpl;
import tools.Printer;

import java.sql.SQLException;
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
                    int i = clientService.makeCoffee(coffeeName, userName, payMethod);
                    if (i == 1) {
                        System.out.println("Done! Drinks made: " + i);
                    } else {
                        System.out.println("I see some shit... problem. Result " + i);
                    }
                    continue;
                }
                switch (operation) {
                    case "menu": {
                        Printer.printList(clientService.getMenu());
                        break;
                    }
                    case "getAllDrinkCompositions": {
                        Printer.printList(clientService.getAllDrinkCompositions());
                        break;
                    }
                    case "getAllRecipes": {
                        Printer.printList(adminService.getAllRecipes());
                        break;
                    }
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
