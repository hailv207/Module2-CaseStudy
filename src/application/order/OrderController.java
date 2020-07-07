package application.order;

import application.App;
import application.employee.Employee;
import application.employee.EmployeeEditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    TableView<Order> orderTable;

    @FXML
    TableColumn<Order, String> orderTableNumberCol;

    @FXML
    TableColumn<Order, Boolean> orderStatusCol;

    @FXML
    TableColumn<Order, LocalDate> orderDateCol;

    @FXML
    TableColumn<Order, String> orderTotalCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderTableNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("tableNumber"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("orderStatus"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderTotal"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        orderTable.getItems().clear();
        orderTable.getItems().addAll(OrderManager.getOrderList());
    }


    public void loadOrder() {
        for (Order o : OrderManager.getOrderList()) {
            orderTable.getItems().add(o);
        }
    }

    public void createOrder() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("menu/menu.fxml"));

        System.out.println(loader);
        Parent menuParent = null;

        try {
            menuParent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(menuParent);
        App.stage.setTitle("Menu");
        App.stage.setScene(scene);
        App.stage.centerOnScreen();
    }


    public void deleteOrder(ActionEvent e) {
        Order orderDelete = orderTable.getSelectionModel().getSelectedItem();
        if (orderDelete != null) {
            OrderManager.remove(orderDelete);
            orderTable.getItems().clear();
            loadOrder();
        }
    }

    public void paymentOrder(ActionEvent e) {
        Order orderPayment = orderTable.getSelectionModel().getSelectedItem();
        if (orderPayment != null) {
            orderPayment.payment();
            orderPayment.setOrderStatus(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order payment");
            alert.setContentText("Total = " + orderPayment.getOrderTotal());
            alert.showAndWait();
            orderTable.getItems().clear();
            loadOrder();
        }
    }

    public void editOrder(ActionEvent event) throws IOException {
        Order selectedItem = orderTable.getSelectionModel().getSelectedItem();

        if (selectedItem.isOrderStatus()) {
            if (selectedItem != null) {
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.getResource("order/editOrder.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setTitle("Edit Order");
                stage.setScene(scene);
                EditOrderController controller = loader.getController();
                Order order = orderTable.getSelectionModel().getSelectedItem();
                controller.setOrder(order);
                stage.setScene(scene);
                stage.centerOnScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System information");
                alert.setContentText("No Order was selected.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("Can not edit paid order.");
            alert.showAndWait();
        }
    }


}
