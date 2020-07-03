package application.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class EmployeeEditController {
    @FXML
    private TextField codeText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField idNumberText;

    @FXML
    private ComboBox accessTypeCombo;

    @FXML
    private ComboBox statusCombo;

    @FXML
    private CheckBox statusCheck;

    public void setEmployee(Employee employee) {
        nameText.setText(employee.getName());
        codeText.setText(employee.getCode());
        addressText.setText(employee.getAddress());
        idNumberText.setText(employee.getIdNumber());
//        accessTypeCombo.setSelected(employee.getAccessType());
    }
    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmployeeManagerScene.fxml"));
        Parent employeeManagerView = loader.load();
        Scene scene = new Scene(employeeManagerView);
        stage.setTitle("Employee Manager");
        stage.setScene(scene);
    }
    public void save(){
        String newCode = codeText.getText();
        String newName = nameText.getText();
        String newAddress = addressText.getText();
        String newIDNumber = idNumberText.getText();
        String newAccessType = (String)accessTypeCombo.getValue();
        String newUserName = usernameText.getText();
        boolean status =
    }


}