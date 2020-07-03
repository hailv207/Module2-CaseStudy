package application.login;

import application.App;
import application.employee.Employee;
import application.employee.EmployeeManager;
import application.filemanager.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    @FXML
    public void onClickLogin(){
        String user = username.getText();
        String pass = password.getText();


        FileManager<Employee> employeeFileManager = new FileManager<>();
        EmployeeManager.getEmployees() = employeeFileManager.read(App.PATH_EMPLOYEE);
        Employee employee = EmployeeManager.getEmployeeByCode(user);
        if (employee.getAuthorized(user, pass)){
            String accessType = employee.getAccessType();
            try {


                    FXMLLoader loader = new FXMLLoader();
                    if (accessType.equals("staff")){
                        loader.setLocation(App.getResource("menu/menu.fxml"));
                    } else if (accessType.equals("manager")){
                        loader.setLocation(App.getResource("../employee/EmployeeManagerScene.fxml"));
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
                    App.currentUser = user;

            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User has not wright to access system. Please contact your manager.");
                alert.showAndWait();
            }
        }

    }

    @FXML
    public void keydownPress(KeyEvent event){
        if (event.getCode()== KeyCode.ENTER){
            onClickLogin();
        }
    }
}