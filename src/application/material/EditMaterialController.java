package application.material;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditMaterialController {
    @FXML
    TextField materialCodeText;
    @FXML
    TextField materialNameText;
    @FXML
    TextField materialSupplierText;
    @FXML
    TextField materialUnitText;
    @FXML
    CheckBox materialStatusCheck;

    public void setMaterial(MaterialType material) {
        materialCodeText.setText(material.getMaterialCode());
        materialNameText.setText(material.getMaterialName());
        materialSupplierText.setText(material.getMaterialSupplier());
        materialUnitText.setText(material.getMaterialUnit());
        materialStatusCheck.setSelected(material.getMaterialStatus());
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MaterialManagerScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("Material manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void save() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        if (materialNameText.getText() != null && materialUnitText.getText() != null) {
            MaterialType material = MaterialManager.getMaterialByCode(materialCodeText.getText());
            material.setMaterialName(materialNameText.getText());
            material.setMaterialUnit(materialUnitText.getText());
            material.setMaterialStatus(materialStatusCheck.isSelected());
            material.setMaterialSupplier(materialSupplierText.getText());
            MaterialManager.writeFile();
            alert.setContentText("Saved change.");
        }else {
            alert.setContentText("Material name and unit could not be null.");
        }
        alert.showAndWait();
    }
}
