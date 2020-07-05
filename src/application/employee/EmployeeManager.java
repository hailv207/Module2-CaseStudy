package application.employee;

import application.App;
import application.filemanager.FileManager;

import java.util.*;

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

}
