package application.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class EmployeeManagerController implements Initializable {
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
        File file = new File("/Users/vuhaile/CodeGym/CaseStudyModule2/src/application/employee/data/employees.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file,true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new Employee("hailv", "Le Vu Hai", "Ha Noi", "197196259", "Manager", true));
            oos.writeObject(new Employee("hylv", "Nguyen Van A", "Ha Noi", "197188659", "Staff", true));
            fos.close();
            oos.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            employeeList = FXCollections.<Employee>observableArrayList();
            Object obj = null;
           while((obj = ois.readObject()) != null){
               employeeList.add((Employee)obj);
           }
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
//        employeeList = FXCollections.observableArrayList(
//                new Employee("hailv","Le Vu Hai","Ha Noi","197196259","Manager",true),
//                new Employee("hylv","Nguyen Van A","Ha Noi","197188659","Staff",true)
//        );
=======
>>>>>>> parent of cdd81a8... update
        codeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        idnumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeIDNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeAddress"));
        accessTypeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("accessType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("status"));
        employeeTableView.setItems(employeeList);
    }

}
