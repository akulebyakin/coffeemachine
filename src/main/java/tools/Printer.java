package tools;

import java.util.List;

public class Printer {
    public static void printTable() {

    }

    public static void printString() {

    }

    public static void printList(List list) {
        if (list != null && !list.isEmpty()) {
            for (Object o : list) {
                System.out.println(o);
            }
        } else {
            System.out.println("List is empty");
        }
    }

}
