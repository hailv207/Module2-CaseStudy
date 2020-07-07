package application.menu;

import application.App;
import application.material.EditMaterialController;
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
import java.util.ResourceBundle;

public class MenuManagerController implements Initializable {
    @FXML
    TableView<MenuItem> menuTable;
    @FXML
    TableColumn<MenuItem, String> menuCodeCol;
    @FXML
    TableColumn<MenuItem, String> menuNameCol;
    @FXML
    TableColumn<MenuItem, Long> menuPriceCol;
    @FXML
    TableColumn<MenuItem, String> menuUnitCol;
    @FXML
    TableColumn<MenuItem, Boolean> menuStatusCol;

    @FXML
    TextField searchMenuText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuCodeCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemCode"));
        menuNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemName"));
        menuUnitCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemUnit"));
        menuPriceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Long>("itemPrice"));
        menuStatusCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Boolean>("status"));
        menuTable.getItems().clear();
        menuTable.getItems().addAll(MenuManager.getMenuList());
        searchMenuText.textProperty().addListener((observable, oldValue, newValue) -> {
            searchMenuByName();
        });
    }

    public void newMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("menu/AddNewMenuScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Add new menu");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void editMenu(ActionEvent event) throws IOException {
        MenuItem selectedItem = menuTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditMenuScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Edit Menu");
            stage.setScene(scene);
            EditMenuController controller = loader.getController();
            controller.setMenuItem(selectedItem);
            stage.setScene(scene);
            stage.centerOnScreen();
        }
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

    public void searchMenuByName() {
        String searchKey = searchMenuText.getText();
        menuTable.getItems().clear();
        menuTable.getItems().addAll(MenuManager.searchMenuItemByName(searchKey));
    }
}
