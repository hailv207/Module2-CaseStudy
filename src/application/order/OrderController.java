package application.order;

import application.App;
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
    TableColumn<Order, Long> orderTotalCol;

    Order order = new Order();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderTableNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("tableNumber"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("orderStatus"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<Order, Long>("orderTotal"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        orderTable.getItems().clear();
        orderTable.getItems().addAll(OrderManager.getOrderList());
    }


    public void loadOrder(){
        for (Order o: OrderManager.getOrderList()){
            orderTable.getItems().add(o);
        }
    }

    public void createOrder(){
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


    public void deleteOrder(ActionEvent e){
        Order orderDelete = orderTable.getSelectionModel().getSelectedItem();
        if (orderDelete != null){
            OrderManager.remove(orderDelete);
            orderTable.getItems().clear();
            loadOrder();
        }
    }

    public void paymentOrder(ActionEvent e){
        Order orderPayment = orderTable.getSelectionModel().getSelectedItem();
        if (orderPayment != null){
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

    public void editOrder(ActionEvent e) throws IOException {
        Order editOrder = orderTable.getSelectionModel().getSelectedItem();
        if (editOrder != null){
            Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("order/editOrder.fxml"));
            Parent orderEditView = loader.load();
            Scene scene = new Scene(orderEditView);
            stage.setTitle("Edit order");
            stage.setScene(scene);
        }
    }


}
