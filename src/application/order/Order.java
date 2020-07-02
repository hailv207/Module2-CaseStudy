package application.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orderItemList;
    private LocalDate orderDate;
    private boolean orderStatus;
    private String tableNumber;

    public Order() {
        this.orderItemList = new ArrayList<OrderItem>();
        this.orderDate = LocalDate.now();
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }


    public void addOrderItem(OrderItem item) {
        for (OrderItem i:orderItemList){
            if (i.getOrderItemName().equals(item.getOrderItemName())){
                item.increaseQuantity();
            }
            return;
        }
        orderItemList.add(item);
    }

    public void deleteOrderItem(OrderItem item) {
        orderItemList.remove(item);
    }


}
