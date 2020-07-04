package application.material;

public class MaterialType {
    private String materialCode;
    private String materialName;
    private String materialUnit;
    private static double materialInStock;

    public MaterialType(String materialCode, String materialName, String materialUnit) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialUnit = materialUnit;
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

    public static double getMaterialInStock() {
        return materialInStock;
    }

    public static void addMaterialInStock(double value) {
        MaterialType.materialInStock += value;
    }

    public static void subMaterialInStock(double value) {
            MaterialType.materialInStock -= value;
    }
}
