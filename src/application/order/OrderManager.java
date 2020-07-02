package application.order;

import application.App;
import application.filemanager.FileManager;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static List<Order> OrderList;
    public OrderManager(){
        if (OrderList == null) {
            OrderList = new ArrayList<>();
            FileManager<Order> fileManager = new FileManager();
            OrderList = fileManager.read(App.PATH_ORDER);
        }
    }

    public List<Order> getOrderList(){
        return OrderList;
    }

    public void add(Order order){
        OrderList.add(order);
    }

    public void remove(Order order){
        OrderList.remove(order);
    }

    public void saveFile(){
        FileManager<Order> fileManager = new FileManager();
        fileManager.write(App.PATH_ORDER, OrderList);
    }

}
