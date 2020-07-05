package application.menu;
import application.App;
import application.filemanager.FileManager;
import application.material.MaterialType;

import java.util.List;

public abstract class MenuManager {
    private static List<MenuItem> menuList;

    public static boolean addMenuItem(MenuItem menuItem) {
        if (isExistID(menuItem.getItemCode())){
            return false;
        }
        return menuList.add(menuItem);
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
    public static List<MenuItem> getMenuList(){
        if (menuList == null){
            FileManager fileManager = new FileManager();
            menuList = fileManager.read(App.PATH_MENU);
        }
        return menuList;
    }

    public static boolean readFile() {
        FileManager<MenuItem> fileManager = new FileManager<>();
        List<MenuItem> list = fileManager.read("src/application/menu/data/menu.dat");
        for (MenuItem item : list) {
            menuList.add(item);
        }
        return true;
    }

    public static boolean writeFile() {
        FileManager<MenuItem> fileManager = new FileManager<>();
        fileManager.write("src/application/menu/data/menu.dat", menuList);
        return true;
    }

}