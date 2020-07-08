package application.report;

import application.App;
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

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                for (int i = 6; i >= 0; i--) {
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
                for (int i = 6; i >= 0; i--) {
                    LocalDate date = LocalDate.now().minusDays(i);
                    Long sum = 0l;
                    for (StockInReceipt s : StockInReceiptManager.getStockInReceiptList()) {
                        if (MyUtil.isSameDate(s.getStockInDate(), date)) {
                            sum += s.getTotalPayment();
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
                            sum += s.getTotalPayment();
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
}
