package application.employee;

import application.App;
import application.filemanager.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static application.App.PATH_EMPLOYEE;

public class EmployeeManagerController implements Initializable {
    List<Employee> list = new ArrayList<Employee>();
    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> codeColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<Employee, String> idNumberColumn;
    @FXML
    private TableColumn<Employee, String> accessTypeColumn;
    @FXML
    private TableColumn<Employee, Boolean> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("birthday"));
        accessTypeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("accessType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("status"));
        table.getItems().addAll(EmployeeManager.getEmployees());
    }


    public void changeSceneEditEmployee(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmployeeEditScene.fxml"));
        Parent employeeEditView = loader.load();
        Scene scene = new Scene(employeeEditView);
        stage.setTitle("Edit Employee");
        stage.setScene(scene);
        EmployeeEditController controller = loader.getController();
        Employee employee = table.getSelectionModel().getSelectedItem();
        controller.setEmployee(employee);
        stage.setScene(scene);
    }
    public void changeSceneAddEmployee(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmployeeAddScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("Add new Employee");
        stage.setScene(scene);
        stage.setScene(scene);
    }
    public void deleteEmployee(){
        Employee employee = table.getSelectionModel().getSelectedItem();
        TextInputDialog passwordInputDialog = new TextInputDialog();
        passwordInputDialog.setHeaderText("Enter your password.");
        passwordInputDialog.showAndWait();
        String inputResult = passwordInputDialog.getEditor().getText();
        boolean authorized = EmployeeManager.getEmployeeByCode(App.currentUser).getAuthorized(App.currentUser, inputResult);
        if (authorized) {
            if (EmployeeManager.deleteEmployee(EmployeeManager.getEmployeeByCode(employee.getCode()))){
                FileManager fileManager = new FileManager();
                fileManager.write(PATH_EMPLOYEE,EmployeeManager.getEmployees());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System information");
                alert.setContentText("Delete employee successfully.");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System information");
                alert.setContentText("Delete employee unsuccessfully.");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("Your password is incorrect. Deleting was cancelled.");
            alert.showAndWait();
        }


    }
}
