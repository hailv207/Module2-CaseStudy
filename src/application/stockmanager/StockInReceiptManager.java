package application.stockmanager;

import application.App;
import application.library.FileManager;
import application.library.MyUtil;
import application.material.MaterialManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class StockInReceiptManager {
    private static List<StockInReceipt> stockInReceiptList = new ArrayList<>();

    public static boolean addStockInReceipt(StockInReceipt s) throws IOException {
        for (StockInItem item : s.getStockInItemList()) {
            item.getMaterial().addMaterialInStock(item.getQuantity());
        }
        MaterialManager.writeFile();
        return stockInReceiptList.add(s);
    }

    public static List<StockInReceipt> getStockInReceiptList() {
        return stockInReceiptList;
    }

    public static boolean deleteStockInReceipt(StockInReceipt s) {
        for (StockInItem item : s.getStockInItemList()) {
            item.getMaterial().subMaterialInStock(item.getQuantity());
        }
        return stockInReceiptList.remove(s);
    }

    public static StockInReceipt getStockInReceiptByCode(String stockInReceiptCode) {
        for (StockInReceipt s : stockInReceiptList) {
            if (s.getStockInReceiptCode().equals(stockInReceiptCode)) {
                return s;
            }
        }
        return null;
    }

    public static boolean readFile() {
        FileManager<StockInReceipt> fileManager = new FileManager<>();
        stockInReceiptList.addAll(fileManager.read(App.PATH_STOCKINRECEIPT));
        return true;
    }

    public static boolean writeFile() {
        FileManager<StockInReceipt> fileManager = new FileManager<>();
        fileManager.write(App.PATH_STOCKINRECEIPT, stockInReceiptList);
        return true;
    }

    public static List<StockInReceipt> searchStockInReceipt(String content, LocalDate date) {
        List<StockInReceipt> list = new ArrayList<>();
        for (StockInReceipt s : stockInReceiptList) {
            if (s.getStockInContent().toLowerCase().contains(content.toLowerCase()) && MyUtil.isSameDate(s.getStockInDate(),date)) {
                list.add(s);
            }
        }
        return list;
    }
    public static List<StockInReceipt> searchStockInReceipt(String content) {
        List<StockInReceipt> list = new ArrayList<>();
        for (StockInReceipt s : stockInReceiptList) {
            if (s.getStockInContent().toLowerCase().contains(content.toLowerCase())) {
                list.add(s);
            }
        }
        return list;
    }
}
