package application.menu;

import application.App;
import application.material.MenuMaterialItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditMenuController implements Initializable {
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
    TableView<MenuMaterialItem> materialItemTable;
    @FXML
    TableColumn<MenuMaterialItem, String> materialItemNameCol;
    @FXML
    TableColumn<MenuMaterialItem, Long> materialItemQuantityCol;

    MenuItem menuItem = new MenuItem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialItemNameCol.setCellValueFactory(new PropertyValueFactory<MenuMaterialItem, String>("menuMaterialItemDisplay"));
        materialItemQuantityCol.setCellValueFactory(new PropertyValueFactory<MenuMaterialItem, Long>("quantity"));
        materialItemTable.getItems().clear();
        menuStatusCheck.setSelected(true);
        menuCodeText.setDisable(true);
    }

    public void setMenuItem(MenuItem m){
        menuItem = m;
        materialItemTable.getItems().addAll(menuItem.getMaterialList());
        menuCodeText.setText(m.getItemCode());
        menuNameText.setText(m.getItemName());
        menuPriceText.setText(String.valueOf(m.getItemPrice()));
        menuStatusCheck.setSelected(m.isStatus());
        menuUnitText.setText(m.getItemUnit());
    }
    public void save(){
        String menuCode = menuCodeText.getText();
        String menuName = menuNameText.getText();
        String menuUnit = menuUnitText.getText();
        Long menuPrice = Long.valueOf(menuPriceText.getText());
        if (menuCode.equals("") || menuName.equals("") || menuUnit.equals("") || menuPrice <= 0){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("System information");
            alert1.setContentText("Menu code, name, unit and price could not be null.");
        }else {
            menuItem.setItemName(menuCodeText.getText());
            menuItem.setItemUnit(menuUnitText.getText());
            menuItem.setItemPrice(Long.valueOf(menuPriceText.getText()));
            menuItem.setStatus(menuStatusCheck.isSelected());
            MenuManager.writeFile();
            cancel();
        }
    }
    public void cancel(){
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(App.getResource("menu/MenuManagerScene.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scene scene = new Scene(root);
        App.stage.setTitle("Menu Manager");
        App.stage.setScene(scene);
        App.stage.centerOnScreen();
    }
}
