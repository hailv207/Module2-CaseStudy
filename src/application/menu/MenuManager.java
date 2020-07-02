package application.menu;
import java.util.ArrayList;

public abstract class MenuManager {
    private static ArrayList<MenuItem> menuList;

    public static boolean addMenuItem(MenuItem menuItem) {
        if (isExistID(menuItem.getItemCode())){
            return false;
        }
        return menuList.add(menuItem);
    }

    public static boolean deleteMenuItem(MenuItem menuItem) {
        return menuList.remove(menuItem);
    }

    public static MenuItem getMenuItemByID(String id) {
        for (MenuItem i : menuList){
            if (i.getItemCode().equals(id)){
                return i;
            }
        }
        return null;
    }
    public  static boolean isExistID(String id){
        boolean check = false;
        for (MenuItem i : menuList){
            if (i.getItemCode().equals(id)){
                check = true;
            }
        }
        return check;
    }
}