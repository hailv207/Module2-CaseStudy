package application.menu;

import application.App;
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
    TextField searchByNameText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuCodeCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemCode"));
        menuNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemName"));
        menuUnitCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemUnit"));
        menuPriceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Long>("itemPrice"));
        menuStatusCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Boolean>("itemStatus"));
        menuTable.getItems().clear();
        menuTable.getItems().addAll(MenuManager.getMenuList());
    }

    public void newMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("menu/AddNewMenuScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("Material manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void editMenu(ActionEvent event) {

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
