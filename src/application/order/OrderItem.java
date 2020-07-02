package application.order;

public class OrderItem {
    private String orderItemName;
    private String orderItemUnit;
    private int orderItemQuantity;
    private long orderItemPrice;

    public OrderItem(String orderItemName, String orderItemUnit, int orderItemQuantity, long orderItemPrice) {
        this.orderItemName = orderItemName;
        this.orderItemUnit = orderItemUnit;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemPrice = orderItemPrice;
    }

    public String getOrderItemName() {
        return orderItemName;
    }

    public String getOrderItemUnit() {
        return orderItemUnit;
    }

    public int getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public long getOrderItemPrice() {
        return orderItemPrice;
    }
    public void increaseQuantity() {
        orderItemQuantity += 1;
    }

    public void decreaseQuantity() {
        if (orderItemQuantity > 0) {
            orderItemQuantity -= 1;
        }
    }
}
