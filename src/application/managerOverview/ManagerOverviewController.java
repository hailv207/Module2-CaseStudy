package application.managerOverview;

import application.App;
import application.employee.EmployeeManager;
import application.library.PasswordDialog;
import application.material.MaterialManager;
import application.menu.MenuManager;
import application.stockmanager.StockInReceiptManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import static application.App.currentUser;
import static javafx.geometry.Pos.CENTER;

public class ManagerOverviewController implements Initializable {
    @FXML
    Label timeLabel;
    @FXML
    Label welcomeLabel;


    public void changeSceneEmployeeManager(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("employee/EmployeeManagerScene.fxml"));
        Parent employeeManagerView = loader.load();
        Scene scene = new Scene(employeeManagerView);
        stage.setTitle("Employee manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneMaterialManager(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("material/MaterialManagerScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
        stage.setTitle("Material manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneStockManager(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("stockmanager/StockInReceiptManagerScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Stock manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneMenuManager(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("menu/MenuManagerScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Menu manager");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Have a nice day, " + currentUser);
        welcomeLabel.setAlignment(CENTER);
        timeLabel.setAlignment(CENTER);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm:ss");
            timeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void changeSceneReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("report/ReportScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Report");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    public void changePassword(){
        PasswordDialog dialog = new PasswordDialog();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System information");
        dialog.setTitle("Confirm password");
        dialog.setContentText("Please enter your current password");
        Optional<String> currentPassword = dialog.showAndWait();
        if (!currentPassword.isPresent()){
            return;
        }
        if (EmployeeManager.getEmployeeByCode(currentUser).getAuthorized(currentUser, currentPassword.get())) {
            dialog.setTitle("New password");
            dialog.setContentText("Please enter your new password");
            dialog.getPasswordField().clear();
            Optional<String> newPassword = dialog.showAndWait();
            dialog.setTitle("Confirm new password");
            dialog.setContentText("Please confirm your new password");
            dialog.getPasswordField().clear();
            Optional<String> confirmNewPassword = dialog.showAndWait();
            if (newPassword.get().equals(confirmNewPassword.get())){
                boolean isDone = EmployeeManager.getEmployeeByCode(currentUser).changePassword(currentUser,currentPassword.get(),confirmNewPassword.get());
                if (isDone) {
                    EmployeeManager.writeFile();
                    alert.setContentText("Change password successfully");
                }   else{
                    alert.setContentText("Change password failed");
                }
            }else{
                alert.setContentText("Your new password was not confirmed. Please try again.");
            }
        }else{
            alert.setContentText("You entered wrong password");
        }
        alert.showAndWait();
    }
}
