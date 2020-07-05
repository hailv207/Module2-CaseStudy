package application.menu;

import application.material.MenuMaterialItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewMenuController implements Initializable {
    @FXML
    TextField menuCodeText;
    @FXML
    TextField menuNameText;
    @FXML
    TextField meneUnitText;
    @FXML
    TextField menuPriceText;
    @FXML
    CheckBox menuStatusCheck;

    @FXML
    TableView materialTable;
    @FXML
    TableColumn materialNameCol;
    @FXML
    TableColumn materialUnitCol;


    @FXML
    TableView materialItemTable;
    @FXML
    TableColumn materialItemNameCol;
    @FXML
    TableColumn materialItemUnitCol;
    @FXML
    TableColumn materialItemQuantityCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//public void add(){
//    List<MenuMaterialItem> list = new ArrayList<>();
//    MenuItem newMenuItem = new MenuItem();
//
//
//
//}


}
