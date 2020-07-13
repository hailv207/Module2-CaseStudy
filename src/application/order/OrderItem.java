package application.order;

import application.menu.MenuItem;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;


public class OrderItem implements Serializable {
    private MenuItem orderItem;
    private int orderItemQuantity;
    private String orderItemName;

    public MenuItem getOrderItem() {
        return orderItem;
    }

    public OrderItem(MenuItem orderItem, int orderItemQuantity) {
        this.orderItem = orderItem;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemName = orderItem.toString();
    }

    public String getOrderItemName() {
        return orderItemName;
    }

    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    public int getIntOrderItemQuantity() {
        return orderItemQuantity;
    }
    public String getOrderItemQuantity() {
        String number = null;
        NumberFormat n = NumberFormat.getInstance(new Locale("vi", "VI"));
        number = n.format(this.orderItemQuantity);
        return number;
    }


    public void increaseQuantity() {
        orderItemQuantity += 1;
    }

    public void decreaseQuantity() {
        if (orderItemQuantity > 1) {
            orderItemQuantity -= 1;
        }
    }
}
