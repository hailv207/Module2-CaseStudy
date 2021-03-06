package application.material;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class MaterialType implements Serializable {
    private String materialCode;
    private String materialName;
    private String materialSupplier;
    private boolean materialStatus;
    private String materialUnit;
    private long materialInStock;

    public MaterialType(String materialCode, String materialName, String materialSupplier, boolean materialStatus, String materialUnit) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialSupplier = materialSupplier;
        this.materialStatus = materialStatus;
        this.materialUnit = materialUnit;
    }

    public String getMaterialSupplier() {
        return materialSupplier;
    }

    public void setMaterialSupplier(String materialSupplier) {
        this.materialSupplier = materialSupplier;
    }

    public boolean isMaterialStatus() {
        return materialStatus;
    }

    public boolean getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(boolean materialStatus) {
        this.materialStatus = materialStatus;
    }

    public void setMaterialInStock(long materialInStock) {
        this.materialInStock = materialInStock;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public long getLongMaterialInStock() {
        return materialInStock;
    }

    public String getMaterialInStock() {
        String number = null;
        NumberFormat n = NumberFormat.getInstance(new Locale("vi", "VI"));
        number = n.format(this.materialInStock);
        return number;
    }

    public void addMaterialInStock(long value) {
        materialInStock += value;
    }

    public void subMaterialInStock(long value) {
        materialInStock -= value;
    }


    @Override
    public String toString() {
        return materialName + " (" + materialUnit + ")";
    }
}
