package application.employee;

import application.App;
import application.filemanager.FileManager;
import application.library.PasswordDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;


public class EmployeeEditController implements Initializable {
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
        statusCheck.setSelected(employee.getStatus());
        accessTypeCombo.getSelectionModel().select(employee.getAccessType());
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmployeeManagerScene.fxml"));
        Parent employeeManagerView = loader.load();
        Scene scene = new Scene(employeeManagerView);
        stage.setTitle("Employee Manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void save() {
        String code = codeText.getText();
        String newName = nameText.getText();
        LocalDate newBirthday = birthdayPicker.getValue();
        String newAddress = addressText.getText();
        String newIDNumber = idNumberText.getText();
        String newAccessType = (String) accessTypeCombo.getValue();
        boolean status = statusCheck.isSelected();
        Employee employee = EmployeeManager.getEmployeeByCode(code);
        employee.setName(newName);
        employee.setStatus(status);
        employee.setAccessType(newAccessType);
        employee.setAddress(newAddress);
        employee.setBirthday(newBirthday);
        employee.setIdNumber(newIDNumber);
        EmployeeManager.writeFile();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        alert.setContentText("Save employee successfully.");
        alert.showAndWait();
    }

    public void resetPassword() {
        String code = codeText.getText();
        PasswordDialog passwordInputDialog = new PasswordDialog();
        Optional<String> inputResult = passwordInputDialog.showAndWait();
        Employee e = EmployeeManager.getEmployeeByCode(code);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        if (e.getAccessType().equals("admin")){
            alert.setContentText("System can not reset admin's password");
            return;
        }
        boolean isDone = e.resetPassword(App.currentUser, inputResult.get());
        System.out.println(inputResult.toString());

        if (isDone) {
            EmployeeManager.writeFile();
            alert.setContentText("Reset password successfully.");
        }else{
            alert.setContentText("You has entered wrong password. Resetting was cancelled.");
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accessTypeCombo.getItems().addAll("manager", "staff");
    }
}