package application.order;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    List<Order> list;

    @FXML
    TableView<Order> orderTable;

    @FXML
    TableColumn<Order, String> orderTableNumber;

    @FXML
    TableColumn<Order, String> orderTableStatus;

    @FXML
    TableColumn<Order, Long> orderTableTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OrderManager orderManager = new OrderManager();

        list = orderManager.getOrderList();
        refresh();

    }


    public void onNewOrder(){
//         new // add ordermanager// list.add
//        refresh();
//
//         OrderManager orderManager = new OrderManager();
//
//        System.out.println("test");
    }

    public void refresh(){
        orderTable.getItems().clear();

        //for ()

        // new // add ordermanager

        System.out.println("test");
    }



}
