package application.filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager<T> {
    public void write(String filePath, List<T> list){
        File file = new File(filePath);
        try {
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(output);
            for (T item: list){
                out.writeObject(item);
            }
            output.close();
            out.close();
        } catch (Exception e){
            System.out.println(e.getMessage());;
        }
    }

    public List<T> read(String filePath){
        List<T> resultList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            T obj = null;
            while ((obj = (T) ois.readObject()) != null){
                resultList.add((T) obj);
            }
            fis.close();
            ois.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultList;
    }

}
