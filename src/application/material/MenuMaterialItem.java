package application.material;

import java.io.Serializable;

public class MenuMaterialItem implements Serializable {
    private MaterialType itemType;
    private long quantity;
    private String menuMaterialItemDisplay;

    public MenuMaterialItem(MaterialType materialType, long quantity) {
        itemType = materialType;
        this.quantity = quantity;
        menuMaterialItemDisplay = itemType.toString();
    }

    public MaterialType getItemType() {
        return itemType;
    }

    public void setItemType(MaterialType itemType) {
        this.itemType = itemType;
    }

    public String getMenuMaterialItemDisplay() {
        return menuMaterialItemDisplay;
    }

    public void setMenuMaterialItemDisplay(String menuMaterialItemDisplay) {
        this.menuMaterialItemDisplay = menuMaterialItemDisplay;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
