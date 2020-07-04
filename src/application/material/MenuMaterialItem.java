package application.material;

public class MenuMaterialItem {
    private MaterialType itemType;
    private double quantity;

    public MenuMaterialItem(MaterialType materialType, double quantity) {
        itemType = materialType;
        this.quantity = quantity;
    }

    public MaterialType getMenuMaterialType() {
        return itemType;
    }

    public void setMenuMaterialType(MaterialType materialType) {
        itemType = materialType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
