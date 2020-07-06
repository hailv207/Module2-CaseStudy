package application.login;
import application.App;
import application.employee.Employee;
import application.employee.EmployeeManager;
import application.filemanager.FileManager;
import application.material.MaterialManager;
import application.menu.MenuManager;
import application.order.Order;
import application.order.OrderManager;
import application.stockmanager.StockInReceiptManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public void onClickLogin(){
        String user = username.getText();
        String pass = password.getText();
        EmployeeManager.readFile();
        Employee employee = EmployeeManager.getEmployeeByCode(user);
        System.out.println(employee);
        try{
            if (employee.getAuthorized(user, pass)){
                MenuManager.readFile();
                MaterialManager.readFile();
                StockInReceiptManager.readFile();
                String accessType = employee.getAccessType();
                try {
                    FXMLLoader loader = new FXMLLoader();
                    if (accessType.equals("staff")){
                        FileManager<Order> fileManager = new FileManager<>();
                        for (Order o: fileManager.read(App.PATH_ORDER) ){
                            OrderManager.add(o);
                        }
                        loader.setLocation(App.getResource("order/order.fxml"));
                    } else if (accessType.equals("manager")){

                        loader.setLocation(App.getResource("managerOverview/ManagerOverviewScene.fxml"));
                    }
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
                    App.currentUser = user;
                } catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System information");
                    alert.setContentText("User has not wright to access system. Please contact your manager.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("User does not exist. Please contact system admin.");
            alert.showAndWait();
        }
    }
    @FXML
    public void keydownPress(KeyEvent event){
        if (event.getCode()== KeyCode.ENTER){
            onClickLogin();
        }
    }
}