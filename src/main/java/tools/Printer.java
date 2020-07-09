package tools;

import java.util.List;

public class Printer {
    public static void printTable() {

    }

    public static void printString() {

    }

//    public static void printList(List list) {
//        if (list != null && !list.isEmpty()) {
//            for (Object o : list) {
//                System.out.println(o);
//            }
//        } else {
//            System.out.println("List is empty");
//        }
//    }

    public static <T> void printList(List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.get(0).toString().length() - 1; i++) {
                System.out.print("-");
            }
            System.out.println();
            for (T o : list) {
                System.out.println(o);
            }
            for (int i = 0; i < list.get(0).toString().length() - 1; i++) {
                System.out.print("-");
            }
            System.out.println();
        } else {
            System.out.println("List is empty");
        }
    }

}
