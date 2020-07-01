package application.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class test implements Initializable {
    private ObservableList<Employee> employeeList;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> codeColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> idnumberColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, LocalDate> birthdayColumn;

    @FXML
    private TableColumn<Employee, String> accessTypeColumn;

    @FXML
    private TableColumn<Employee, Boolean> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeList = FXCollections.observableArrayList(
                new Employee("hailv", "Le Vu Hai", "Ha Noi", "197196259", "Manager", true)
        );
        codeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeCode"));
        employeeTableView.setItems(employeeList);
    }
}
