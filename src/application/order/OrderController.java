package application.order;

import application.App;
import application.employee.Employee;
import application.employee.EmployeeEditController;
import application.employee.EmployeeManager;
import application.library.PasswordDialog;
import application.material.MaterialManager;
import application.material.MaterialType;
import application.material.MenuMaterialItem;
import application.menu.MenuItem;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static application.App.currentUser;
import static javafx.geometry.Pos.CENTER;

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

    @FXML
    Label welcomeLabel;
    @FXML
    Label timeLabel;
    @FXML
    TextField tableNumberText;
    @FXML
    DatePicker datePicker;

    Order order = new Order();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Have a nice day, " + currentUser + ".");
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm:ss");
            timeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        orderTableNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("tableNumber"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("orderStatus"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderTotal"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("orderDate"));
        datePicker.setValue(LocalDate.now());
        tableNumberText.textProperty().addListener((observable, oldValue, newValue) -> {
            searchOrder();
        });
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            searchOrder();
        });
        searchOrder();
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
        if (orderDelete.isOrderStatus()) {
            if (orderDelete != null) {
                OrderManager.remove(orderDelete);
                OrderManager.writeFile();
                searchOrder();
            }
        }
    }

    public void paymentOrder(ActionEvent e) {
        Order orderPayment = orderTable.getSelectionModel().getSelectedItem();
        if (orderPayment != null) {
            orderPayment.payment();
            updateMaterialInStock(orderPayment);
            OrderManager.writeFile();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order payment");
            alert.setContentText("Total = " + orderPayment.getOrderTotal());
            alert.showAndWait();
            orderTable.getItems().clear();
            searchOrder();
        }
    }

    public void editOrder(ActionEvent event) throws IOException {
        Order selectedItem = orderTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
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

    public void changePassword() {
        EmployeeManager.changePassword();
    }

    public void updateMaterialInStock(Order order) {
        for (OrderItem o : order.getOrderItemList()) {
            List<MenuMaterialItem> list = o.getOrderItem().getMaterialList();
            if (list == null) {
                return;
            }
            for (MenuMaterialItem m : list) {
                Long value = m.getQuantity() * o.getOrderItemQuantity();
                String code = m.getMenuMaterialType().getMaterialCode();
                MaterialType mi = MaterialManager.getMaterialByCode(code);
                mi.subMaterialInStock(value);
            }
        }
        MaterialManager.writeFile();
    }

    public void searchOrder() {
        String tableNumber = tableNumberText.getText();
        LocalDate date = datePicker.getValue();
        orderTable.getItems().clear();
        if (date == null) {
            orderTable.getItems().addAll(OrderManager.searchOrder(tableNumber));
        }
        orderTable.getItems().addAll(OrderManager.searchOrder(tableNumber, date));

    }
}
