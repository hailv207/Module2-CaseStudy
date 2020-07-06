package application.material;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMaterialController implements Initializable {
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

    public void addNewMaterial() {
        MaterialType material = new MaterialType(materialCodeText.getText(), materialNameText.getText()
                , materialSupplierText.getText(), materialStatusCheck.isSelected(), materialUnitText.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        if (material.getMaterialCode() != null && material.getMaterialName() != null && material.getMaterialUnit() != null) {
            if (MaterialManager.addNewMaterial(material)) {
                MaterialManager.writeFile();
                alert.setContentText("Material was created successfully.");
            } else {
                alert.setContentText("Material code is duplicated.");
            }
        } else {
            alert.setContentText("Material code, name, unit could not be null.");
        }
        alert.showAndWait();
    }
    public void clear() {
        materialCodeText.setText("");
        materialNameText.setText("");
        materialUnitText.setText("");
        materialSupplierText.setText("");
        materialStatusCheck.setSelected(true);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialStatusCheck.setSelected(true);
    }
}

