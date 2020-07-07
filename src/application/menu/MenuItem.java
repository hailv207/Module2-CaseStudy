package application.menu;


import application.material.MenuMaterialItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItem extends MenuManager implements Serializable {
    private String itemCode;
    private String itemName;
    private String itemUnit;
    private long itemPrice;
    private boolean status;
    private List<MenuMaterialItem> materialList;

    public MenuItem(String itemCode, String itemName, String unit, long itemPrice, boolean status) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemUnit = unit;
        this.itemPrice = itemPrice;
        this.status = status;
    }
    public MenuItem(){
        this.materialList = new ArrayList<>();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean addNewMaterial(MenuMaterialItem item) {
        for (MenuMaterialItem m : materialList) {
            if (m.getMenuMaterialType().getMaterialCode().equals(item.getMenuMaterialType().getMaterialCode())) {
                return false;
            }
        }
        return materialList.add(item);
    }

    public boolean deleteMaterial(MenuMaterialItem item) {
        for (MenuMaterialItem m : materialList) {
            if (m.getMenuMaterialType().getMaterialCode().equals(item.getMenuMaterialType().getMaterialCode())) {
                return materialList.remove(m);
            }
        }
        return false;
    }

    public List<MenuMaterialItem> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MenuMaterialItem> materialList) {
        this.materialList = materialList;
    }


    @Override
    public String toString() {
        return getItemName() + " (" + getItemUnit() + ")";
    }
}