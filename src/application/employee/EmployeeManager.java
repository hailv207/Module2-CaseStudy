package application.employee;

import application.App;
import application.library.FileManager;
import application.library.PasswordDialog;
import javafx.scene.control.Alert;

import java.util.*;

import static application.App.currentUser;

public abstract class EmployeeManager {
    private static List<Employee> employees = new ArrayList<>();

    public static boolean addNewEmployee(Employee employee) {
        if (isExistCode(employee.getCode())) {
            return false;
        } else {
            employees.add(employee);
            return true;
        }
    }

    public static Employee getEmployeeByCode(String code) {
        for (Employee employee : employees) {
            if (employee.getCode().equals(code)) {
                return employee;
            }
        }
        return null;
    }

    public static boolean deleteEmployee(Employee employee) {
        return employees.remove(employee);
    }

    public static boolean isExistCode(String code) {
        for (Employee e : employees) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
public static boolean readFile(){
    FileManager<Employee> fileManager = new FileManager<>();
    List<Employee> list = fileManager.read(App.PATH_EMPLOYEE);
    for (Employee e:list){
        EmployeeManager.addNewEmployee(e);
    }
    return true;
}

public static boolean writeFile(){
    FileManager<Employee> fileManager = new FileManager<>();
    fileManager.write(App.PATH_EMPLOYEE, employees);
    return true;
}
    public static void changePassword() {
        PasswordDialog dialog = new PasswordDialog();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        dialog.setTitle("Confirm password");
        dialog.setContentText("Please enter your current password");
        Optional<String> currentPassword = dialog.showAndWait();
        if (!currentPassword.isPresent()){
            return;
        }
        if (EmployeeManager.getEmployeeByCode(currentUser).getAuthorized(currentUser, currentPassword.get())) {
            dialog.setTitle("New password");
            dialog.setContentText("Please enter your new password");
            dialog.getPasswordField().clear();
            Optional<String> newPassword = dialog.showAndWait();
            dialog.setTitle("Confirm new password");
            dialog.setContentText("Please confirm your new password");
            dialog.getPasswordField().clear();
            Optional<String> confirmNewPassword = dialog.showAndWait();
            if (newPassword.get().equals(confirmNewPassword.get())){
                boolean isDone = EmployeeManager.getEmployeeByCode(currentUser).changePassword(currentUser,currentPassword.get(),confirmNewPassword.get());
                if (isDone) {
                    EmployeeManager.writeFile();
                    alert.setContentText("Change password successfully");
                }   else{
                    alert.setContentText("Change password failed");
                }
            }else{
                alert.setContentText("Your new password was not confirmed. Please try again.");
            }
        }else{
            alert.setContentText("You entered wrong password");
        }
        alert.showAndWait();
    }
    public static List<Employee> searchEmployeeByName(String searchKey){
        List<Employee> result = new ArrayList<>();
        for (Employee e:employees){
            if (e.getName().toLowerCase().contains(searchKey.toLowerCase())){
                result.add(e);
            }
        }
        return result;
    }
}
