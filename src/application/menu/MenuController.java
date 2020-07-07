package application.menu;

import application.App;
import application.filemanager.FileManager;
import application.order.Order;
import application.order.OrderItem;
import application.order.OrderManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    TableView<MenuItem> menuView;
    @FXML
    TableColumn<MenuItem, String> menuNameCol;
    @FXML
    TableColumn<MenuItem, String> unitCol;
    @FXML
    TableColumn<MenuItem, Long> priceCol;

    @FXML
    TableView<OrderItem> orderTable;
    @FXML
    TableColumn<OrderItem, String> orderItemName;
    @FXML
    TableColumn<OrderItem, Integer> orderQuantityCol;
    @FXML
    TextField tableNumberText;
    @FXML
    TextField searchMenuText;

    Order newOrder = new Order();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemUnit"));
        priceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Long>("itemPrice"));

        orderItemName.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("orderItemName"));
        orderQuantityCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("orderItemQuantity"));
        loadMenuItems();
        searchMenuText.textProperty().addListener(((observable, oldValue, newValue) -> {
            searchMenuByName();
        }));
    }

    public void loadOrderItem() {
        orderTable.getItems().clear();
        for (OrderItem oi : newOrder.getOrderItemList()) {
            orderTable.getItems().add(oi);
        }

        List<OrderItem> items = orderTable.getItems();
    }

    public void onClickItem() {
        MenuItem selectedItem = menuView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            OrderItem orderItem = new OrderItem(selectedItem, 1);
            newOrder.addOrderItem(orderItem);
            loadOrderItem();
        }

    }

    public void cancelOrder() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(App.getResource("order/order.fxml"));

        Parent menuParent = null;
        try {
            menuParent = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scene scene = new Scene(menuParent);
        App.stage.setTitle("Menu");
        App.stage.setScene(scene);
        App.stage.centerOnScreen();
    }

    public void deleteOrderItem(ActionEvent e) {
        OrderItem oi = orderTable.getSelectionModel().getSelectedItem();
        if (oi != null) {
            newOrder.deleteOrderItem(oi);
            loadOrderItem();
        }

    }

    public void increaseQuantityOrderItem(ActionEvent e) {
        OrderItem oi = orderTable.getSelectionModel().getSelectedItem();
        if (oi != null) {
            oi.increaseQuantity();
            loadOrderItem();
            orderTable.getSelectionModel().select(oi);
        }
    }

    public void decreaseQuantityOrderItem(ActionEvent e) {
        OrderItem oi = orderTable.getSelectionModel().getSelectedItem();
        if (oi != null) {
            oi.decreaseQuantity();
            loadOrderItem();
            orderTable.getSelectionModel().select(oi);
        }
    }

    public void saveOrder(ActionEvent event) {
        String tableNumber = tableNumberText.getText();
        if (tableNumber.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("Table number can not be null.");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i < OrderManager.getOrderList().size(); i++) {
            if (OrderManager.getOrderList().get(i).getTableNumber().equals(tableNumber)) {
                if (OrderManager.getOrderList().get(i).isOrderStatus()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System information");
                    alert.setContentText("Table number you entered is serving.");
                    alert.showAndWait();
                    return;
                }
            }
        }
        newOrder.setTableNumber(tableNumber);
        newOrder.calculateTotal();
        OrderManager.add(newOrder);
        OrderManager.writeFile();
        cancelOrder();

    }

    public void loadMenuItems() {
        menuView.getItems().clear();
        for (MenuItem m : MenuManager.getMenuList()) {
            if (m.isStatus()) {
                menuView.getItems().add(m);
            }
        }
    }

    public void searchMenuByName() {
        String searchKey = searchMenuText.getText();
        menuView.getItems().clear();
        menuView.getItems().addAll(MenuManager.searchMenuItemByName(searchKey, true));


    }

}
