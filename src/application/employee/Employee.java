package application.employee;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeCode;
    private String employeeName;
    private String employeeAddress;
    private String employeeIDNumber;
    private String accessType;
    private String userName;
    private String password;
    private boolean status;

    public Employee(String employeeCode, String employeeName, String employeeAddress, String employeeIDNumber, String accessType, boolean status) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeIDNumber = employeeIDNumber;
        this.accessType = accessType;
        this.status = status;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeIDNumber() {
        return employeeIDNumber;
    }

    public void setEmployeeIDNumber(String employeeIDNumber) {
        this.employeeIDNumber = employeeIDNumber;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public boolean isStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
    public boolean changePassword(String userName, String password,String newPassword) {
        if (getAuthority(userName, password)){
            setPassword(newPassword);
            return true;
        }
        return false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getAuthority(String username, String password) {
        boolean authorized = false;
        if (username.equals(getUserName()) && password.equals(getPassword())) {
            authorized = true;
        }
        return authorized;
    }
}
