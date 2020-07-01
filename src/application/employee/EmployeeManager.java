package application.employee;

import java.util.*;

public abstract class EmployeeManager {
    private static ArrayList<Employee> employees = new ArrayList<>();

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

    public static boolean deleteEmployee(Employee employee){
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
}