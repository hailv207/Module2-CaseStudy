package application.managerOverview;

import application.App;
import application.material.MaterialManager;
import application.menu.MenuManager;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void changeSceneMenuManager(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.getResource("menu/MenuManagerScene.fxml"));
        Parent employeeAddView = loader.load();
        Scene scene = new Scene(employeeAddView);
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
        MaterialManager.readFile();
        MenuManager.readFile();
    }
}
