package application.login;

import application.App;
import application.employee.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    Employee employee = new Employee("a", "a");


    @FXML
    public void onClickLogin(){
        String user = username.getText();
        String pass = password.getText();

        System.out.println(employee.getAuthorized(user, pass));

        try {
            if (employee.getAuthorized(user, pass)){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.getResource("menu/menu.fxml"));
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
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void keydownPress(KeyEvent event){
        if (event.getCode()== KeyCode.ENTER){
            onClickLogin();
        }
    }
}