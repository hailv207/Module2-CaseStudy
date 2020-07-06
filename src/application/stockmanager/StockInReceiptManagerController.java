package application.stockmanager;

import application.App;
import application.employee.EmployeeManager;
import application.library.PasswordDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class StockInReceiptManagerController implements Initializable {
    @FXML
    TableView<StockInReceipt> stockInReceiptTable;
    @FXML
    TableColumn<StockInReceipt, LocalDate> stockInReceiptDateCol;
    @FXML
    TableColumn<StockInReceipt, String> stockInReceiptCodeCol;
    @FXML
    TableColumn<StockInReceipt, String> stockInReceiptUpdaterCol;
    @FXML
    TableColumn<StockInReceipt, String> stockInReceiptContentCol;
    @FXML
    TableColumn<StockInReceipt, Long> stockInReceiptTotalCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stockInReceiptCodeCol.setCellValueFactory(new PropertyValueFactory<StockInReceipt, String>("stockInReceiptCode"));
        stockInReceiptDateCol.setCellValueFactory(new PropertyValueFactory<StockInReceipt, LocalDate>("stockInDate"));
        stockInReceiptUpdaterCol.setCellValueFactory(new PropertyValueFactory<StockInReceipt, String>("updater"));
        stockInReceiptContentCol.setCellValueFactory(new PropertyValueFactory<StockInReceipt, String>("stockInContent"));
        stockInReceiptTotalCol.setCellValueFactory(new PropertyValueFactory<StockInReceipt, Long>("totalPayment"));
        stockInReceiptTable.getItems().addAll(StockInReceiptManager.getStockInReceiptList());
    }

    public void newStockInReceipt(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("stockmanager/NewStockInReceiptScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("New stock in receipt");
        stage.setScene(scene);
        stage.centerOnScreen();
    }


    public void deleteStockInReceipt() {
        StockInReceipt s = stockInReceiptTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        if (s != null) {
            PasswordDialog passwordInputDialog = new PasswordDialog();
            Optional<String> inputResult = passwordInputDialog.showAndWait();
            boolean authorized = EmployeeManager.getEmployeeByCode(App.currentUser).getAuthorized(App.currentUser, inputResult.get());
            if (authorized) {
                if (StockInReceiptManager.deleteStockInReceipt(s)) {
                    StockInReceiptManager.writeFile();
                    refreshStockInReceiptTable();
                    alert.setContentText("Deleted stock in receipt successfully");
                }
            } else {
                alert.setContentText("You entered the wrong password.");
            }
        } else {
            alert.setContentText("No stock in receipt selected.");
        }
        alert.showAndWait();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("managerOverview/ManagerOverviewScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("Manager overview");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    public void refreshStockInReceiptTable(){
        stockInReceiptTable.getItems().clear();
        stockInReceiptTable.getItems().addAll(StockInReceiptManager.getStockInReceiptList());
    }

}
