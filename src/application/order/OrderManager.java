package application.order;

import application.App;
import application.filemanager.FileManager;
import application.library.MyUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderManager {
    private static List<Order> orderList = new ArrayList<>();

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static boolean add(Order order) {
        return orderList.add(order);
    }

    public static boolean remove(Order order) {
        return orderList.remove(order);
    }

    public static void writeFile() {
        FileManager<Order> fileManager = new FileManager<>();
        fileManager.write(App.PATH_ORDER, OrderManager.getOrderList());
    }

    public static void readFile() {
        FileManager<Order> fileManager = new FileManager<>();
        OrderManager.getOrderList().addAll(fileManager.read(App.PATH_ORDER));
    }

    public static List<Order> searchOrder(String tableNumber) {
        List<Order> list = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getTableNumber().toLowerCase().contains(tableNumber.toLowerCase())) {
                list.add(o);
            }
        }
        return list;
    }

    public static List<Order> searchOrder(String tableNumber, LocalDate date) {
        List<Order> list = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getTableNumber().toLowerCase().contains(tableNumber.toLowerCase()) && MyUtil.isSameDate(o.getOrderDate(), date)) {
                list.add(o);
            }
        }
        return list;
    }

}
