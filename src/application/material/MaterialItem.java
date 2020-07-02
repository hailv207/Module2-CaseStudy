package application.material;

public class MaterialItem {
    private MenuMaterial MenuMaterialType;
    private double quantity;

    public MaterialItem(MenuMaterial menuMaterialType, double quantity) {
        MenuMaterialType = menuMaterialType;
        this.quantity = quantity;
    }

    public MenuMaterial getMenuMaterialType() {
        return MenuMaterialType;
    }

    public void setMenuMaterialType(MenuMaterial menuMaterialType) {
        MenuMaterialType = menuMaterialType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
