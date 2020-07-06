package application.stockmanager;

import application.material.MaterialType;

import java.io.Serializable;

public class StockInItem implements Serializable {
    private MaterialType material;
    private long quantity;
    private long totalPayment;
    private String materialName;
    private String materialUnit;

    public StockInItem(MaterialType material, long quantity, long totalPayment) {
        this.material = material;
        this.quantity = quantity;
        this.totalPayment = totalPayment;
        this.materialName = material.getMaterialName();
        this.materialUnit = material.getMaterialUnit();
    }

    public MaterialType getMaterial() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material = material;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(long totalPayment) {
        this.totalPayment = totalPayment;
    }


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName() {
        this.materialName = material.getMaterialName();
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit() {
        this.materialUnit = material.getMaterialUnit();
    }
}
