import service.AdminService;
import service.ClientService;
import service.impl.AdminServiceImpl;
import service.impl.ClientServiceImpl;
import tools.Printer;

import java.util.Scanner;

public class Main {
    private static String userName = "John Dow";
    private static String userRole = "nobody";

    public static void main(String[] args) {
        ClientService clientService = new ClientServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        System.out.println("Welcome to the Red Tiger Coffee House! Please tell me your name.");
        System.out.print("My name is: ");

        Scanner scanner = new Scanner(System.in);
        userName = scanner.nextLine();

        System.out.println("What do you want?");

        String operation = "";

        while (!operation.equals("exit")) {
            System.out.print("Operation: ");
            operation = scanner.nextLine();
            if (operation.matches("make .+")) {
                String coffeeName = operation.substring(4).trim();
                System.out.println("I'm making '" + coffeeName + "'");
                int i = clientService.makeCoffee(coffeeName, userName, "cash");
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
                case "getDrinkComposition": {
                    Printer.printList(clientService.getDrinkComposition());
                    break;
                }
                case "getAllRecipes": {
                    Printer.printList(adminService.getAllRecipes());
                    break;
                }
            }
        }

    }
}
