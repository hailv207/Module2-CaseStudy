package application.menu;

import application.material.MaterialItem;

import java.util.ArrayList;

public class MenuItem extends MenuManager{
    private String itemCode;
    private String itemName;
    private int unit;
    private long itemPrice;
    private boolean status;
    private ArrayList<MenuItem> materialList = new ArrayList<>();

    public MenuItem(String itemCode, String itemName, int unit,long itemPrice,boolean status) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unit = unit;
        this.itemPrice = itemPrice;
        this.status = status;
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

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean addMenuItem(String itemCode, String itemName, int unit,long itemPrice, boolean status){
        boolean check = checkID(itemCode);
        if (!check){
            materialList.add(new MenuItem(itemCode, itemName, unit, itemPrice, status));
        }else {
            return false;
        }
       return true;
    }

    public boolean deleteMenuItem(String itemCode){
        boolean check = false;
       for (int i = 0; i < materialList.size(); i++){
           if (checkID(itemName)){
               materialList.remove(materialList.get(i));
               check = true;
           }
       }
       return check;

    }

    public boolean checkID(String id){
        boolean check = false;
        for (int i = 0; i < materialList.size(); i++){
            if (materialList.get(i).getItemCode().equals(id)){
                check = true;
            }
        }
        return check;
    }
}
