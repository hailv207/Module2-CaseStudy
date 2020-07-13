package application.report;

import org.apache.commons.math3.util.ArithmeticUtils;
import application.App;
import application.employee.Employee;
import application.library.MyUtil;
import application.order.Order;
import application.order.OrderManager;
import application.stockmanager.StockInReceipt;
import application.stockmanager.StockInReceiptManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    LineChart<String, Number> lineChart;
    @FXML
    ComboBox reportTypeCombo;
    @FXML
    ComboBox reportTimeCombo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportTypeCombo.getItems().add("Revenue");
        reportTypeCombo.getItems().add("Cost");
        reportTypeCombo.getSelectionModel().select("Revenue");
        reportTimeCombo.getItems().add("Daily");
        reportTimeCombo.getItems().add("Monthly");
        reportTimeCombo.getSelectionModel().select("Daily");
        lineChart.getData().clear();
    }

    public void show() {
        if (reportTypeCombo.getValue().toString().equals("Revenue")) {
            getLineChartRevenueData();
        } else {
            getLineChartCostData();
        }

    }

    public void getLineChartRevenueData() {
        String reportTime = reportTimeCombo.getValue().toString();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        lineChart.getData().clear();
        switch (reportTime) {
            case "Daily":
                series.setName("Daily revenue report");
                series.getData().clear();
                for (int i = 9; i >= 0; i--) {
                    LocalDate date = LocalDate.now().minusDays(i);
                    Long sum = 0l;
                    for (Order o : OrderManager.getOrderList()) {
                        if (!o.isOrderStatus() && MyUtil.isSameDate(o.getOrderDate(), date)) {
                            sum += o.getLongOrderTotal();
                        }
                    }
                    series.getData().add(new XYChart.Data<String, Number>(date.getDayOfMonth() + "/" + date.getMonthValue(), sum));
                }
                break;
            case "Monthly":
                series.setName("Monthly revenue report");
                series.getData().clear();
                for (int i = 5; i >= 0; i--) {
                    Long sum = 0l;
                    LocalDate date = LocalDate.now().minusMonths(i);
                    for (Order o : OrderManager.getOrderList()) {
                        if (!o.isOrderStatus() && MyUtil.isSameMonth(o.getOrderDate(), date)) {
                            sum += o.getLongOrderTotal();
                        }
                    }
                    series.getData().add(new XYChart.Data<String, Number>(date.getMonthValue() + "/" + date.getYear(), sum));
                }
                break;
        }
        lineChart.getData().add(series);
    }

    public void getLineChartCostData() {
        String reportTime = reportTimeCombo.getValue().toString();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        lineChart.getData().clear();
        switch (reportTime) {
            case "Daily":
                series.setName("Daily cost report");
                series.getData().clear();
                for (int i = 9; i >= 0; i--) {
                    LocalDate date = LocalDate.now().minusDays(i);
                    Long sum = 0l;
                    for (StockInReceipt s : StockInReceiptManager.getStockInReceiptList()) {
                        if (MyUtil.isSameDate(s.getStockInDate(), date)) {
                            sum += s.getLongTotalPayment();
                        }
                    }
                    series.getData().add(new XYChart.Data<String, Number>(date.getDayOfMonth() + "/" + date.getMonthValue(), sum));
                }
                break;
            case "Monthly":
                series.setName("Monthly cost report");
                series.getData().clear();
                for (int i = 5; i >= 0; i--) {
                    Long sum = 0l;
                    LocalDate date = LocalDate.now().minusMonths(i);
                    for (StockInReceipt s : StockInReceiptManager.getStockInReceiptList()) {
                        if (MyUtil.isSameMonth(s.getStockInDate(), date)) {
                            sum += s.getLongTotalPayment();
                        }
                    }
                    series.getData().add(new XYChart.Data<String, Number>(date.getMonthValue() + "/" + date.getYear(), sum));
                }
                break;
        }
        lineChart.getData().add(series);
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("managerOverview/ManagerOverviewScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Manager overview");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void exportXLS() {
        String type = reportTypeCombo.getValue().toString() + reportTimeCombo.getValue().toString();
        switch (type) {
            case "RevenueDaily":
                exportDailyRevenueToXLS();
                break;
            case "RevenueMonthly":
                exportMonthlyRevenueToXLS();
                break;
            case "CostDaily":
                exportDailyCostToXLS();
                break;
            case "CostMonthly":
                exportMonthlyCostToXLS();
                break;
        }
    }

    public void exportMonthlyCostToXLS() {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("report");
        Row row = spreadsheet.createRow(0);
        for (int i = 5, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusMonths(i);
            row.createCell(j).setCellValue(date.getMonthValue() + "/" + date.getYear());
        }
        row = spreadsheet.createRow(1);
        List<StockInReceipt> list = new ArrayList<>();
        for (int i = 5, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusMonths(i);
            Long sum = 0l;
            for (StockInReceipt s : StockInReceiptManager.getStockInReceiptList()) {
                if (MyUtil.isSameMonth(s.getStockInDate(), date)) {
                    sum += s.getLongTotalPayment();
                    list.add(s);
                }
            }
            row.createCell(j).setCellValue(sum);
        }
        Sheet details = workbook.createSheet("details");
        Row detailsHeader = details.createRow(0);
        detailsHeader.createCell(0).setCellValue("Stock-in receipt date");
        detailsHeader.createCell(1).setCellValue("Updater");
        detailsHeader.createCell(2).setCellValue("Content");
        detailsHeader.createCell(3).setCellValue("Total payment");

        for (int i = 1;i <= list.size();i++){
            Row dataRow = details.createRow(i);
            dataRow.createCell(0).setCellValue(list.get(i-1).getStockInDate());
            dataRow.createCell(1).setCellValue(list.get(i-1).getUpdater());
            dataRow.createCell(2).setCellValue(list.get(i-1).getStockInContent());
            dataRow.createCell(3).setCellValue(list.get(i-1).getLongTotalPayment());
        }

        String fileName = "monthly-cost-rp-" + LocalDate.now().toString() + ".xls";
        try {
            FileOutputStream fileOut = new FileOutputStream("src/application/data/report/" + fileName);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportDailyCostToXLS() {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("report");
        Row row = spreadsheet.createRow(0);
        for (int i = 9, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusDays(i);
            row.createCell(j).setCellValue(date.getDayOfMonth() + "/" + date.getMonthValue());
        }
        row = spreadsheet.createRow(1);
        List<StockInReceipt> list = new ArrayList<>();
        for (int i = 9, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusDays(i);
            Long sum = 0l;
            for (StockInReceipt s : StockInReceiptManager.getStockInReceiptList()) {
                if (MyUtil.isSameDate(s.getStockInDate(), date)) {
                    sum += s.getLongTotalPayment();
                    list.add(s);
                }
            }
            row.createCell(j).setCellValue(sum);
        }

        Sheet details = workbook.createSheet("details");
        Row detailsHeader = details.createRow(0);
        detailsHeader.createCell(0).setCellValue("Stock-in receipt date");
        detailsHeader.createCell(1).setCellValue("Updater");
        detailsHeader.createCell(2).setCellValue("Content");
        detailsHeader.createCell(3).setCellValue("Total payment");

        for (int i = 1;i <= list.size();i++){
            Row dataRow = details.createRow(i);
            dataRow.createCell(0).setCellValue(list.get(i-1).getStockInDate());
            dataRow.createCell(1).setCellValue(list.get(i-1).getUpdater());
            dataRow.createCell(2).setCellValue(list.get(i-1).getStockInContent());
            dataRow.createCell(3).setCellValue(list.get(i-1).getLongTotalPayment());
        }

        String fileName = "daily-cost-rp-" + LocalDate.now().toString() + ".xls";
        try {
            FileOutputStream fileOut = new FileOutputStream("src/application/data/report/" + fileName);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportMonthlyRevenueToXLS() {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("report");
        Row row = spreadsheet.createRow(0);
        for (int i = 5, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusMonths(i);
            row.createCell(j).setCellValue(date.getMonthValue() + "/" + date.getYear());
        }
        row = spreadsheet.createRow(1);
        List<Order> list = new ArrayList<>();
        for (int i = 5, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusMonths(i);
            Long sum = 0l;
            for (Order o : OrderManager.getOrderList()) {
                if (!o.isOrderStatus() && MyUtil.isSameMonth(o.getOrderDate(), date)) {
                    sum += o.getLongOrderTotal();
                    list.add(o);
                }
            }
            row.createCell(j).setCellValue(sum);
        }

        Sheet details = workbook.createSheet("details");
        Row detailsHeader = details.createRow(0);
        detailsHeader.createCell(0).setCellValue("Order date");
        detailsHeader.createCell(1).setCellValue("Table number");
        detailsHeader.createCell(2).setCellValue("Total");
        detailsHeader.createCell(3).setCellValue("Number of menu items");

        for (int i = 1;i <= list.size();i++){
            Row dataRow = details.createRow(i);
            dataRow.createCell(0).setCellValue(list.get(i-1).getOrderDate());
            dataRow.createCell(1).setCellValue(list.get(i-1).getTableNumber());
            dataRow.createCell(2).setCellValue(list.get(i-1).getLongOrderTotal());
            dataRow.createCell(3).setCellValue(list.get(i-1).getOrderItemList().size());
        }

        String fileName = "monthly-revenue-rp-" + LocalDate.now().toString() + ".xls";
        try {
            FileOutputStream fileOut = new FileOutputStream("src/application/data/report/" + fileName);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportDailyRevenueToXLS() {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("report");
        Row row = spreadsheet.createRow(0);
        for (int i = 9, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusDays(i);
            row.createCell(j).setCellValue(date.getDayOfMonth() + "/" + date.getMonthValue());
        }
        row = spreadsheet.createRow(1);
        List<Order> list = new ArrayList<>();
        for (int i = 9, j = 0; i >= 0; i--, j++) {
            LocalDate date = LocalDate.now().minusDays(i);
            Long sum = 0l;
            for (Order o : OrderManager.getOrderList()) {
                if (!o.isOrderStatus() && MyUtil.isSameDate(o.getOrderDate(), date)) {
                    sum += o.getLongOrderTotal();
                    list.add(o);
                }
            }
            row.createCell(j).setCellValue(sum);
        }

        Sheet details = workbook.createSheet("details");
        Row detailsHeader = details.createRow(0);
        detailsHeader.createCell(0).setCellValue("Order date");
        detailsHeader.createCell(1).setCellValue("Table number");
        detailsHeader.createCell(2).setCellValue("Total");
        detailsHeader.createCell(3).setCellValue("Number of menu items");

        for (int i = 1;i <= list.size();i++){
            Row dataRow = details.createRow(i);
            dataRow.createCell(0).setCellValue(list.get(i-1).getOrderDate());
            dataRow.createCell(1).setCellValue(list.get(i-1).getTableNumber());
            dataRow.createCell(2).setCellValue(list.get(i-1).getLongOrderTotal());
            dataRow.createCell(3).setCellValue(list.get(i-1).getOrderItemList().size());
        }


        String fileName = "daily-revenue-rp-" + LocalDate.now().toString() + ".xls";
        try {
            FileOutputStream fileOut = new FileOutputStream("src/application/data/report/" + fileName);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
