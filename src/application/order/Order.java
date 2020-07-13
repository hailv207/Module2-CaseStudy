package application.order;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order implements Serializable {
    private List<OrderItem> orderItemList;
    private LocalDate orderDate;
    private boolean orderStatus;
    private String tableNumber;
    private long orderTotal;

    public Order() {
        this.orderItemList = new ArrayList<OrderItem>();
        this.orderDate = LocalDate.now();
        this.orderStatus = true;
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

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTotal() {
        String total = null;
        NumberFormat n = NumberFormat.getInstance(new Locale("vi", "VI"));
        total = n.format(this.orderTotal);
        return total;
    }
    public Long getLongOrderTotal(){
        return orderTotal;
    }

    public void setOderTotal(long orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void addOrderItem(OrderItem item) {
        for (OrderItem i : orderItemList) {
            if ((i.getOrderItem().getItemCode()).equals(item.getOrderItem().getItemCode())) {
                i.increaseQuantity();
                return;
            }
        }
        orderItemList.add(item);
    }

    public void calculateTotal() {
        long total = 0;
        for (OrderItem o : orderItemList) {
            total += (o.getOrderItem().getLongItemPrice() * o.getIntOrderItemQuantity());
        }
        setOderTotal(total);
    }

    public boolean payment() {
        calculateTotal();
        setOrderStatus(false);
        return true;
    }

    public boolean deleteOrderItem(OrderItem item) {
        return orderItemList.remove(item);
    }


}
