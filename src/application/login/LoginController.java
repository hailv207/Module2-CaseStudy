package application.login;

import application.App;
import application.employee.Employee;
import application.employee.EmployeeManager;
import application.material.MaterialManager;
import application.menu.MenuManager;
import application.order.OrderManager;
import application.stockmanager.StockInReceipt;
import application.stockmanager.StockInReceiptManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    public void onClickLogin() {
        String user = username.getText();
        String pass = password.getText();
        if (user.equals("") || pass.equals("")){
            return;
        }
        EmployeeManager.readFile();
        checkUsers();
        Employee employee = EmployeeManager.getEmployeeByCode(user);
        if (!employee.getStatus()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("Your username had expired. Please contact administrator.");
            alert.showAndWait();
            return;
        }

        try {
            if (employee.getAuthorized(user, pass)) {
                MenuManager.readFile();
                MaterialManager.readFile();
                StockInReceiptManager.readFile();
                OrderManager.readFile();
                App.currentUser = user;
                FileReader fr = new FileReader(App.PATH_RECEIPTCOUNTER);
                Long value = Long.valueOf(fr.read());
                StockInReceipt.setStockInReceiptCounter(value);
                String accessType = employee.getAccessType();
                try {
                    FXMLLoader loader = new FXMLLoader();
                    if (accessType.equals("staff")) {
                        loader.setLocation(App.getResource("order/order.fxml"));
                        App.stage.setTitle("Orders");
                    } else if (accessType.equals("manager") || accessType.equals("admin")) {

                        loader.setLocation(App.getResource("managerOverview/ManagerOverviewScene.fxml"));
                        App.stage.setTitle("Manager overview");
                    }
                    Parent menuParent = null;
                    try {
                        menuParent = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(menuParent);
                    App.stage.setScene(scene);
                    App.stage.centerOnScreen();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System information");
                    alert.setContentText("User has not wright to access system. Please contact your manager.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("User does not exist. Please contact system admin.");
            alert.showAndWait();
        }
    }

    @FXML
    public void keydownPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onClickLogin();
        }
    }

    public void checkUsers() {
        for (Employee e : EmployeeManager.getEmployees()) {
            if (e.getAccessType().equals("admin")) {
                return;
            }
        }
        EmployeeManager.addNewEmployee(new Employee("admin", "admin", "", "", LocalDate.now(), "admin", true));
    }
}