package application.menu;

import application.App;
import application.material.MaterialManager;
import application.material.MaterialType;
import application.material.MenuMaterialItem;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddNewMenuController implements Initializable {
    @FXML
    TextField menuCodeText;
    @FXML
    TextField menuNameText;
    @FXML
    TextField menuUnitText;
    @FXML
    TextField menuPriceText;
    @FXML
    CheckBox menuStatusCheck;

    @FXML
    TableView<MaterialType> materialTable;
    @FXML
    TableColumn<MaterialType, String> materialNameCol;
    @FXML
    TableColumn<MaterialType, String> materialUnitCol;


    @FXML
    TableView<MenuMaterialItem> materialItemTable;
    @FXML
    TableColumn<MenuMaterialItem, String> materialItemNameCol;
    @FXML
    TableColumn<MenuMaterialItem, Long> materialItemQuantityCol;

    @FXML
    TextField searchMaterialText;

    MenuItem menuItem = new MenuItem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialNameCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialName"));
        materialUnitCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialUnit"));
        materialTable.getItems().clear();
        materialTable.getItems().addAll(MaterialManager.getMaterialList());

        materialItemNameCol.setCellValueFactory(new PropertyValueFactory<MenuMaterialItem, String>("menuMaterialItemDisplay"));
        materialItemQuantityCol.setCellValueFactory(new PropertyValueFactory<MenuMaterialItem, Long>("quantity"));
        materialItemTable.getItems().clear();
        menuStatusCheck.setSelected(true);
        searchMaterialText.textProperty().addListener((observable, oldValue, newValue) -> {
            searchMaterialByName();
        });

    }

    public void addMenuMaterialItem() {
        MaterialType selectedItem = materialTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            for (MenuMaterialItem m : menuItem.getMaterialList()) {
                if (m.getMenuMaterialType().getMaterialCode().equals(selectedItem.getMaterialCode())) {
                    return;
                }
            }
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Quantity input");
            dialog.setHeaderText("Please enter quantity");
            Optional<String> result1 = dialog.showAndWait();
            Long quantity = Long.parseLong(result1.get());
            menuItem.addNewMaterial(new MenuMaterialItem(selectedItem, quantity));
            refreshMenuMaterialItemTable();
        }
    }

    public void editMaterialItem() {
        MenuMaterialItem selectedItem = materialItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Quantity input");
            dialog.setHeaderText("Please enter quantity");
            Optional<String> result1 = dialog.showAndWait();
            Long quantity = Long.parseLong(result1.get());
            selectedItem.setQuantity(quantity);
            refreshMenuMaterialItemTable();
        }
    }

    public void refreshMenuMaterialItemTable() {
        materialItemTable.getItems().clear();
        materialItemTable.getItems().addAll(menuItem.getMaterialList());
    }

    public void removeMaterialItem() {
        MenuMaterialItem selectedItem = materialItemTable.getSelectionModel().getSelectedItem();
        menuItem.deleteMaterial(selectedItem);
    }

    public void add() throws IOException {
        String menuCode = menuCodeText.getText();
        String menuName = menuNameText.getText();
        String menuUnit = menuUnitText.getText();
        Long menuPrice = Long.valueOf(menuPriceText.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("System information");
        alert.setContentText("Caution: You can not edit materials once you had saved menu. Please check details carefully!");
        Optional<ButtonType> result = alert.showAndWait();
        if (menuCode.equals("") || menuName.equals("") || menuUnit.equals("") || menuPrice <= 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("System information");
            alert1.setContentText("Menu code, name, unit and price could not be null.");
        } else {
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                menuItem.setItemCode(menuCode);
                menuItem.setItemName(menuName);
                menuItem.setItemUnit(menuUnit);
                menuItem.setItemPrice(menuPrice);
                menuItem.setStatus(menuStatusCheck.isSelected());
                MenuManager.addMenuItem(menuItem);
                MenuManager.writeFile();
                cancel();
            }
        }
    }

    public void cancel() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(App.getResource("menu/MenuManagerScene.fxml"));

        Parent menuParent = null;
        try {
            menuParent = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scene scene = new Scene(menuParent);
        App.stage.setTitle("Menu");
        App.stage.setScene(scene);
        App.stage.centerOnScreen();
    }

    public void loadMaterial() {
        materialTable.getItems().clear();
        for (MaterialType m : MaterialManager.getMaterialList()) {
            if (m.getMaterialStatus()) {
                materialTable.getItems().add(m);
            }
        }
    }
    public void searchMaterialByName(){
        String searchKey = searchMaterialText.getText();
        materialTable.getItems().clear();
        materialTable.getItems().addAll(MaterialManager.searchMaterialByName(searchKey,true));
    }

}
