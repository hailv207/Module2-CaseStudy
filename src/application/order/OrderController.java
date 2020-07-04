package application.order;

import application.App;
import application.filemanager.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderTableNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("tableNumber"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("orderStatus"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<Order, Long>("orderTotal"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        orderTable.getItems().clear();
        orderTable.getItems().addAll(OrderManager.getOrderList());
    }

    public void onNewOrder(){

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




}
