package application.material;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

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

    public Long getLongQuantity() {
        return quantity;
    }
    public String getQuantity() {
        String number = null;
        NumberFormat n = NumberFormat.getInstance(new Locale("vi", "VI"));
        number = n.format(this.quantity);
        return number;    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
