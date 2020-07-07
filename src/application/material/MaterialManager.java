package application.material;

import application.App;
import application.filemanager.FileManager;

import java.util.*;

public abstract class MaterialManager {
    private static List<MaterialType> materialList = new ArrayList<MaterialType>();

    public static boolean addNewMaterial(MaterialType material) {
        for (MaterialType m : materialList) {
            if (material.getMaterialCode().equals(m.getMaterialCode())) {
                return false;
            }
        }
        return materialList.add(material);
    }

    public static List<MaterialType> getMaterialList() {
        return materialList;
    }

    public static MaterialType getMaterialByCode(String materialCode) {
        for (MaterialType material : materialList) {
            if (material.getMaterialCode().equals(materialCode)) {
                return material;
            }
        }
        return null;
    }

    public static boolean readFile() {
        FileManager<MaterialType> fileManager = new FileManager();
        materialList.addAll(fileManager.read(App.PATH_MATERIALS));
        return true;
    }

    public static boolean writeFile() {
        FileManager<MaterialType> fileManager = new FileManager();
        fileManager.write(App.PATH_MATERIALS, materialList);
        return true;
    }

    public static List<MaterialType> searchMaterialByName(String searchKey) {
        List<MaterialType> list = new ArrayList<>();
        for (MaterialType m : materialList) {
            if (m.getMaterialName().toLowerCase().contains(searchKey.toLowerCase())) {
                list.add(m);
            }
        }
        return list;
    }

    public static List<MaterialType> searchMaterialByName(String searchKey, boolean status) {
        List<MaterialType> list = new ArrayList<>();
        for (MaterialType m : materialList) {
            if (m.getMaterialName().toLowerCase().contains(searchKey.toLowerCase()) && m.getMaterialStatus() == status) {
                list.add(m);
            }
        }
        return list;
    }

}
