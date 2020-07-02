package application.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TextField addressText;

    @FXML
    private TextField idNumberText;

    @FXML
    private ComboBox accessTypeCombo;

    @FXML
    private ComboBox statusCombo;

    public void settEmployee(Employee employee) {
        nameText.setText(employee.getName());
        codeText.setText(employee.getCode());
        addressText.setText(employee.getAddress());
        idNumberText.setText(employee.getIdNumber());
//        accessTypeCombo.setSelected(employee.getAccessType());
    }


}