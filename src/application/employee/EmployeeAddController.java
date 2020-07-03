package application.employee;

import application.filemanager.FileManager;
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
import java.util.ResourceBundle;

import static application.App.PATH_EMPLOYEE;

public class EmployeeAddController implements Initializable {
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

    public void add() {
        Employee employee = new Employee(codeText.getText(), nameText.getText(), addressText.getText(), idNumberText.getText(), birthdayPicker.getValue(), (String) accessTypeCombo.getValue(), statusCheck.isSelected());
        if (EmployeeManager.addNewEmployee(employee)) {
            FileManager fileManager = new FileManager();
            fileManager.write(PATH_EMPLOYEE,EmployeeManager.getEmployees());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("Add new employee successfully.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("Add new employee unsuccessfully.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accessTypeCombo.getItems().addAll("manger", "staff");
        accessTypeCombo.getSelectionModel().select("staff");
        clear();
    }
    public void clear(){
        nameText.setText("");
        codeText.setText("");
        addressText.setText("");
        idNumberText.setText("");
        birthdayPicker.setValue(LocalDate.now());
        statusCheck.setSelected(true);
        accessTypeCombo.getSelectionModel().select("staff");
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

}
