package application.employee;

import application.App;
import application.filemanager.FileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class EmployeeEditController {
    @FXML
    private TextField codeText;

    @FXML
    private TextField nameText;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TextField addressText;

    @FXML
    private TextField idNumberText;

    @FXML
    private ComboBox accessTypeCombo;

    @FXML
    private CheckBox statusCheck;


    public void setEmployee(Employee employee) {
        nameText.setText(employee.getName());
        codeText.setText(employee.getCode());
        addressText.setText(employee.getAddress());
        idNumberText.setText(employee.getIdNumber());
        birthdayPicker.setValue(employee.getBirthday());

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

    public void save() {
        String code = codeText.getText();
        String newName = nameText.getText();
        LocalDate newBirthday = birthdayPicker.getValue();
        String newAddress = addressText.getText();
        String newIDNumber = idNumberText.getText();
        String newAccessType = (String) accessTypeCombo.getValue();
        boolean status = statusCheck.isSelected();
        FileManager fileManager = new FileManager();
        Employee employee = EmployeeManager.getEmployeeByCode(code);
        employee.setName(newName);
        employee.setStatus(status);
        employee.setAccessType(newAccessType);
        employee.setAddress(newAddress);
        employee.setBirthday(newBirthday);
        employee.setIdNumber(newIDNumber);
        fileManager.write("src/application/employee/data/employees.dat", EmployeeManager.getEmployees());
    }

    public void resetPassword() {
        String code = codeText.getText();
        TextInputDialog passwordInputDialog = new TextInputDialog();
        passwordInputDialog.setHeaderText("Enter your password.");
        passwordInputDialog.showAndWait();
        String inputResult = passwordInputDialog.getEditor().getText();
        EmployeeManager.getEmployeeByCode(code).resetPassword(App.currentUser,inputResult);
    }

}