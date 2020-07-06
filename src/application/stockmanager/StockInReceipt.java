package application.stockmanager;

import application.employee.Employee;
import application.employee.EmployeeManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static application.App.currentUser;

public class StockInReceipt implements Serializable {
    private static long stockInReceiptCounter = 0;
    private String updater;
    private String stockInReceiptCode;
    private List<StockInItem> stockInItemList;
    private long totalPayment;
    private LocalDate stockInDate;
    private String stockInContent;

    public StockInReceipt() {
       this.stockInReceiptCounter += 1;
       this.stockInReceiptCode = "sir" + this.stockInReceiptCounter;
       this.stockInItemList = new ArrayList<>();
       this.stockInDate = LocalDate.now();
       this.updater = currentUser;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getStockInContent() {
        return stockInContent;
    }

    public void setStockInContent(String stockInContent) {
        this.stockInContent = stockInContent;
    }

    public LocalDate getStockInDate() {
        return stockInDate;
    }


    public static long getStockInReceiptCounter() {
        return stockInReceiptCounter;
    }

    public String getStockInReceiptCode() {
        return stockInReceiptCode;
    }

    public List<StockInItem> getStockInItemList() {
        return stockInItemList;
    }

    public long getTotalPayment() {
        return totalPayment;
    }
    public void setTotalPayment() {
        long total = 0;
        for (StockInItem s: stockInItemList) {
            total += s.getTotalPayment();
            this.totalPayment = total;
        }
    }

    public boolean addStockInItem(StockInItem stockInItem){
        for (StockInItem s: stockInItemList){
            if (s.getMaterial().getMaterialCode().equals(stockInItem.getMaterial().getMaterialCode())){
                return false;
            }
        }
        return stockInItemList.add(stockInItem);
    }
    public boolean removeStockInItem(StockInItem stockInItem){
        for (StockInItem s: stockInItemList){
            if (s.getMaterial().getMaterialCode().equals(stockInItem.getMaterial().getMaterialCode())){
                return stockInItemList.remove(stockInItem);
            }
        }
        return false;
    }
    public StockInItem getStockInItemByCode(String materialCode){
        for (StockInItem s: stockInItemList){
            if (s.getMaterial().getMaterialCode().equals(materialCode)){
                return s;
            }
        }
        return null;
    }
}
