package application.material;

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

public class MaterialManagerController implements Initializable {
    @FXML
    TextField searchText;

    @FXML
    TableView<MaterialType> materialTable;

    @FXML
    TableColumn<MaterialType, String> materialCodeCol;
    @FXML
    TableColumn<MaterialType, String> materialUnitCol;
    @FXML
    TableColumn<MaterialType, String> materialNameCol;
    @FXML
    TableColumn<MaterialType, String> materialSupplierCol;
    @FXML
    TableColumn<MaterialType, Boolean> materialStatusCol;
    @FXML
    TableColumn<MaterialType, Integer> materialInStockCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialCodeCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialCode"));
        materialNameCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialName"));
        materialSupplierCol.setCellValueFactory(new PropertyValueFactory<MaterialType, String>("materialSupplier"));
        materialUnitCol.setCellValueFactory(new PropertyValueFactory<MaterialType,String>("materialUnit"));
        materialStatusCol.setCellValueFactory(new PropertyValueFactory<MaterialType, Boolean>("materialStatus"));
        materialInStockCol.setCellValueFactory(new PropertyValueFactory<MaterialType, Integer>("materialInStock"));
        materialTable.getItems().addAll(MaterialManager.getMaterialList());
    }
    public void addNewMaterial(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddNewMaterialScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("Add new material");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    public void editMaterial(ActionEvent event) throws IOException {
        MaterialType material = materialTable.getSelectionModel().getSelectedItem();
        if (material != null){
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditMaterialScene.fxml"));
            Parent employeeEditView = loader.load();
            Scene scene = new Scene(employeeEditView);
            stage.setTitle("Edit Material");
            stage.setScene(scene);
            EditMaterialController controller = loader.getController();
            controller.setMaterial(material);
            stage.setScene(scene);
            stage.centerOnScreen();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("No material type was selected.");
            alert.showAndWait();
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
}
