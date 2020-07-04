package application.menu;

import java.util.ArrayList;

public class CustomerOrder {
    private String orderFoodName;
    private int orderQuantity;
    private long price;
    private ArrayList<MenuItem> customerOrder = new ArrayList<>();

    public CustomerOrder(String orderFoodName, int orderQuantity, long price) {
        this.orderFoodName = orderFoodName;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    public String getOrderFoodName() {
        return orderFoodName;
    }

    public void setOrderFoodName(String orderFoodName) {
        this.orderFoodName = orderFoodName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
