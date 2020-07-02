package application.login;

import application.employee.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button cancel;

    Employee employee = new Employee("nkdblack", "khongnho");


    @FXML
    public void onClickLogin(){
        String user = username.getText();
        String pass = password.getText();

        System.out.println(employee.getAuthorized(user, pass));

        try {
            if (employee.getAuthorized(user, pass)){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../menu/menu.fxml"));
                Parent menuParent = null;
                try {
                    menuParent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(menuParent);
                LoginTest.stage.setScene(scene);
            }
        } catch (Exception e){
            System.out.println("wrong username or password");
        }
    }
//    public void keydownEnter(KeyEvent event){
//        if (event.KEY_PRESSED ==)
//    }
}
