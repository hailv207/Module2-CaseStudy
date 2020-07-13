package application.stockmanager;

import application.App;
import application.material.MaterialManager;
import application.material.MaterialType;
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

public class NewStockInReceiptController implements Initializable {
    @FXML
    TableView<MaterialType> materialTable;
    @FXML
    TableColumn<MaterialType, String> materialNameCol;
    @FXML
    TableColumn<MaterialType, String> materialUnitCol;

    @FXML
    TableView<StockInItem> stockInItemTable;
    @FXML
    TableColumn<StockInItem, String> stockInItemNameCol;
    @FXML
    TableColumn<StockInItem, String> stockInItemUnitCol;
    @FXML
    TableColumn<StockInItem, Long> stockInItemQuantityCol;
    @FXML
    TableColumn<StockInItem, Long> stockInItemTotalCol;

    @FXML
    TextField stockInReceiptContentText;
    @FXML
    TextField searchMaterialText;
    @FXML
    DatePicker datePicker;

    StockInReceipt newStockInReceipt;

    {
        try {
            newStockInReceipt = new StockInReceipt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialNameCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialName"));
        materialUnitCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialUnit"));
        materialTable.getItems().addAll(MaterialManager.getMaterialList());

        stockInItemNameCol.setCellValueFactory(new PropertyValueFactory<StockInItem, String>("materialName"));
        stockInItemUnitCol.setCellValueFactory(new PropertyValueFactory<StockInItem, String>("materialUnit"));
        stockInItemQuantityCol.setCellValueFactory(new PropertyValueFactory<StockInItem, Long>("quantity"));
        stockInItemTotalCol.setCellValueFactory(new PropertyValueFactory<StockInItem, Long>("totalPayment"));
        stockInItemTable.getItems().addAll(newStockInReceipt.getStockInItemList());
        datePicker.setValue(LocalDate.now());
        searchMaterial();
        searchMaterialText.textProperty().addListener((observable, oldValue, newValue) -> {
            searchMaterial();
        });
    }

    public void save() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("System information");
        alert.setContentText("Caution: You can not edit Stock in receipt once you had saved it. Please check details of Stock in receipt carefully!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            newStockInReceipt.setStockInDate(datePicker.getValue());
            newStockInReceipt.setTotalPayment();
            newStockInReceipt.setStockInContent(stockInReceiptContentText.getText());
            StockInReceiptManager.addStockInReceipt(newStockInReceipt);
            StockInReceiptManager.writeFile();
            cancel();
        }
    }

    public void cancel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("stockmanager/StockInReceiptManagerScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        App.stage.setTitle("Edit stock in receipt");
        App.stage.setScene(scene);
        App.stage.centerOnScreen();
    }

    public void addStockInItem() {
        MaterialType material = materialTable.getSelectionModel().getSelectedItem();
        if (material != null) {
            for (StockInItem s : newStockInReceipt.getStockInItemList()) {
                if (s.getMaterial().getMaterialCode().equals(material.getMaterialCode())) {
                    return;
                }
            }
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Quantity input");
            dialog.setHeaderText("Please enter quantity");
            Optional<String> result1 = dialog.showAndWait();
            Long quantity = Long.parseLong(result1.get());
            dialog.setTitle("Total payment input");
            dialog.setHeaderText("Please enter Total payment");
            Optional<String> result2 = dialog.showAndWait();
            Long totalPayment = Long.valueOf(result2.get());
            StockInItem item = new StockInItem(material, quantity, totalPayment);
            if (newStockInReceipt.addStockInItem(item)) {
                refreshStockInItemList();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System information");
                alert.setContentText("Add item failed.");
                alert.showAndWait();
            }
        }
    }

    public void removeStockInItem() {
        StockInItem selectedItem = stockInItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            newStockInReceipt.removeStockInItem(selectedItem);
            refreshStockInItemList();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("No item selected.");
            alert.showAndWait();
        }
    }

    public void refreshStockInItemList() {
        stockInItemTable.getItems().clear();
        stockInItemTable.getItems().addAll(newStockInReceipt.getStockInItemList());
    }

    public void editStockInItem() {
        StockInItem selectedItem = stockInItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Quantity input");
            dialog.setHeaderText("Please enter quantity");
            Optional<String> result1 = dialog.showAndWait();
            Long quantity = Long.valueOf(result1.get());
            dialog.setTitle("Total payment input");
            dialog.setHeaderText("Please enter Total payment");
            Optional<String> result2 = dialog.showAndWait();
            Long totalPayment = Long.valueOf(result2.get());
            selectedItem.setQuantity(quantity);
            selectedItem.setTotalPayment(totalPayment);
            refreshStockInItemList();
        }
    }

    public void searchMaterial() {
        String searchKey = searchMaterialText.getText();
        materialTable.getItems().clear();
        materialTable.getItems().addAll(MaterialManager.searchMaterialByName(searchKey, true));
    }
}
