package application.employee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private TableColumn<Employee, String> usernameColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        writeEmployees();
        readEmployees();
        codeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("birthday"));
        accessTypeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("accessType"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("status"));
        table.setItems(EmployeeManager.getEmployees());
    }

    public void writeEmployees() {
        String filePath = "src/application/employee/data/employees.dat";
        File file = new File(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new Employee("hailv", "Le Vu Hai", "Ha noi", "199199", "Manager", "hailv", true));
            oos.writeObject(new Employee("tung", "Nguyen Tuan Tung", "Ha noi", "122222", "Staff", "tung", true));
            oos.writeObject(new Employee("hailv", "Nguyen Tuan Toan", "Ha noi", "354646", "Staff", "toan", true));
            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readEmployees() {
        writeEmployees();
        String filePath = "src/application/employee/data/employees.dat";
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                EmployeeManager.addNewEmployee((Employee) obj);
            }
            fis.close();
            ois.close();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
