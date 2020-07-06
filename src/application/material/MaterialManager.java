package application.material;

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
        List<MaterialType> list = fileManager.read("src/application/material/data/materials.dat");
        for (MaterialType material : list) {
            materialList.add(material);
        }
        return true;
    }

    public static boolean writeFile() {
        FileManager<MaterialType> fileManager = new FileManager();
        fileManager.write("src/application/material/data/materials.dat", materialList);
        return true;
    }
}
