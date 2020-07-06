package application.order;

import application.App;
import application.filemanager.FileManager;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderManager {
    private static List<Order> OrderList = new ArrayList<>();

    public static List<Order> getOrderList(){
        return OrderList;
    }

    public static boolean add(Order order){
       return OrderList.add(order);
    }

    public static boolean remove(Order order){
       return OrderList.remove(order);
    }

    public static void writeFile(){
        FileManager<Order> fileManager = new FileManager<>();
        fileManager.write(App.PATH_ORDER, OrderManager.getOrderList());
    }

    public static void readFile(){
        FileManager<Order> fileManager = new FileManager<>();
        OrderManager.getOrderList().addAll(fileManager.read(App.PATH_ORDER));
    }


}
