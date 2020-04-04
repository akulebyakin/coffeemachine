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
        System.out.println("Welcome to the Red Tiger Coffee House! Please tell us your name.");
        System.out.print("My name is: ");
        Scanner scanner = new Scanner(System.in);
        userName = scanner.nextLine();
//        System.out.println("So " + userName + ", you're our client or administrator?");
//        while (!userRole.equals("client") && !userRole.equals("administrator")) {
//            System.out.print("I'am (client / administrator): ");
//            userRole = scanner.nextLine().toLowerCase();
//        }
//        System.out.println("That's what you can do: ");
//        Method[] clientMethods = ClientService.class.getMethods();
//        Method[] adminMethods = AdminService.class.getMethods();
//        System.out.println("Client methods");
//        for (int i = 0; i < clientMethods.length; i++) {
//            System.out.println(i + 1 + ". " + clientMethods[i].getName());
//        }
//        if (userRole.equals("administrator")) {
//            System.out.println("\nAdministrator Methods");
//            for (int i = 0; i < adminMethods.length; i++) {
//                System.out.println(i + 1 + ". " + adminMethods[i].getName());
//            }
//        }
        System.out.println("What do you want");
        String operation = "";
        ClientService clientService = new ClientServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        while (!operation.equals("exit")) {
            System.out.print("Operation: ");
            operation = scanner.nextLine();
            if (operation.matches("make \\w+")) {
                String coffeeName = operation.substring(4).trim();
                clientService.makeCoffee(coffeeName, userName, "card");
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
