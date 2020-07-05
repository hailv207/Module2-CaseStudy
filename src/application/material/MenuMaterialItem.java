package application.material;

public class MenuMaterialItem {
    private MaterialType itemType;
    private long quantity;
    private String menuMaterialItemDisplay;

    public MenuMaterialItem(MaterialType materialType, long quantity) {
        itemType = materialType;
        this.quantity = quantity;
        menuMaterialItemDisplay = itemType.toString();
    }

    public String getMaterialDisplay() {
        return menuMaterialItemDisplay;
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

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
