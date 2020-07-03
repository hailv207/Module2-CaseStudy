package application.employee;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    private final String DEFAULT_PASS = "123";
    private String code;
    private String name;
    private String address;
    private String idNumber;
    private String accessType;
    private String password;
    private boolean status;
    private LocalDate birthday;

    public Employee(String code, String name, String address, String idNumber, LocalDate birthday, String accessType, boolean status) {
        this.password = DEFAULT_PASS;
        this.code = code;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.idNumber = idNumber;
        this.accessType = accessType;
        this.status = status;
    }

    public Employee(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getAuthorized(String code, String password) {
        if (getCode().equals(code) && getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean changePassword(String code, String password, String newPassword) {
        if (getAuthorized(code, password)) {
            setPassword(newPassword);
            return true;
        }
        return false;
    }
    public boolean resetPassword(String managerCode, String managerPassword){
        if (EmployeeManager.getEmployeeByCode(managerCode).getAuthorized(managerCode,managerPassword)){
            setPassword(DEFAULT_PASS);
            return true;
        }
        return false;
    }
}