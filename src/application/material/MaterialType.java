package application.material;

public class MaterialType {
    private String materialCode;
    private String materialName;
    private String materialSupplier;
    private boolean materialStatus;
    private String materialUnit;
    private static long materialInStock = 0;

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

    public static void setMaterialInStock(long materialInStock) {
        MaterialType.materialInStock = materialInStock;
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

    public static long getMaterialInStock() {
        return materialInStock;
    }

    public static void addMaterialInStock(long value) {
        MaterialType.materialInStock += value;
    }

    public static void subMaterialInStock(long value) {
            MaterialType.materialInStock -= value;
    }

    @Override
    public String toString() {
        return materialName + " (" + materialUnit + ")";
    }
}
