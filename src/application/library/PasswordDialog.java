package application.library;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
public class PasswordDialog extends Dialog<String> {
    private PasswordField passwordField;
    public PasswordDialog() {
        super.setTitle("Confirm password");
        super.setHeaderText("Please enter your password.");
        ButtonType passwordButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
        super.getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        HBox hBox = new HBox();
        hBox.getChildren().add(passwordField);
        hBox.setPadding(new Insets(20));
        HBox.setHgrow(passwordField, Priority.ALWAYS);
        super.getDialogPane().setContent(hBox);
        Platform.runLater(() -> passwordField.requestFocus());
        super.setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == passwordButtonType.getButtonData()) {
                return passwordField.getText();
            }
            return null;
        });
    }
    public PasswordField getPasswordField() {
        return passwordField;
    }
}