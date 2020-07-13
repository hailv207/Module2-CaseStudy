package application.stockmanager;

import application.App;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static application.App.currentUser;

public class StockInReceipt implements Serializable {
    private String updater;
    private String stockInReceiptCode;
    private List<StockInItem> stockInItemList;
    private long totalPayment;
    private LocalDate stockInDate;
    private String stockInContent;
    private static long stockInReceiptCounter = 0;

    public StockInReceipt() throws IOException {
       this.stockInReceiptCode = "sir" + stockInReceiptCounter;
       this.stockInItemList = new ArrayList<>();
       this.stockInDate = LocalDate.now();
       this.updater = currentUser;
       stockInReceiptCounter++;
        FileWriter fw = new FileWriter(App.PATH_RECEIPTCOUNTER);
        fw.write(String.valueOf(stockInReceiptCounter));
        fw.close();
    }

    public static void setStockInReceiptCounter(long stockInReceiptCounter) {
        StockInReceipt.stockInReceiptCounter = stockInReceiptCounter;
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

    public void setStockInDate(LocalDate stockInDate) {
        this.stockInDate = stockInDate;
    }

    public String getStockInReceiptCode() {
        return stockInReceiptCode;
    }

    public List<StockInItem> getStockInItemList() {
        return stockInItemList;
    }

    public long getLongTotalPayment() {
        return totalPayment;
    }
    public String getTotalPayment() {
        String number = null;
        NumberFormat n = NumberFormat.getInstance(new Locale("vi", "VI"));
        number = n.format(this.totalPayment);
        return number;    }
    public void setTotalPayment() {
        long total = 0;
        for (StockInItem s: stockInItemList) {
            total += s.getLongTotalPayment();
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
