package application.stockmanager;

import application.filemanager.FileManager;

import java.util.ArrayList;
import java.util.List;

public abstract class StockInReceiptManager {
    private static List<StockInReceipt> stockInReceiptList = new ArrayList<>();

    public static boolean addStockInReceipt(StockInReceipt s) {
        for (StockInItem item : s.getStockInItemList()) {
            item.getMaterial().addMaterialInStock(item.getQuantity());
        }
        return stockInReceiptList.add(s);
    }

    public static List<StockInReceipt> getStockInReceiptList() {
        return stockInReceiptList;
    }

    public static boolean deleteStockInReceipt(StockInReceipt s){
        for (StockInItem item : s.getStockInItemList()) {
            item.getMaterial().subMaterialInStock(item.getQuantity());
        }
        return stockInReceiptList.remove(s);
    }
    public static StockInReceipt getStockInReceiptByCode(String stockInReceiptCode){
        for (StockInReceipt s: stockInReceiptList){
            if (s.getStockInReceiptCode().equals(stockInReceiptCode)){
                return s;
            }
        }
        return null;
    }
    public static boolean readFile(){
        FileManager<StockInReceipt> fileManager = new FileManager<>();
        List<StockInReceipt> list = new ArrayList<>();
        stockInReceiptList.addAll(list);
        return true;
    }
    public static boolean writeFile(){
        FileManager<StockInReceipt> fileManager = new FileManager<>();
        fileManager.write("src/stockmanager/data/stockinreceipts.dat",stockInReceiptList);
        return true;
    }
}
