package application.employee;

import java.io.Serializable;

public class Employee implements Serializable {
    private String code;
    private String name;
    private String adress;
    private String idNumber;
    private String accessType;
    private String username;
    private String password;
    private boolean status;

    public Employee(String code, String name, String adress, String idNumber, String accessType, String username, boolean status) {
        this.password = "123";
        this.code = code;
        this.name = name;
        this.adress = adress;
        this.idNumber = idNumber;
        this.accessType = accessType;
        this.username = username;
        this.status = status;
    }

    public Employee(String username, String password) {
        this.username = username;
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
        return adress;
    }

    public void setAddress(String adress) {
        this.adress = adress;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean getAuthorized(String username, String password) {
        if (getUsername().equals(username) && getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    public boolean changePassword(String username, String password, String newPassword) {
        if (getAuthorized(username, password)){
            setPassword(newPassword);
            return true;
        }
        return false;
    }
}
