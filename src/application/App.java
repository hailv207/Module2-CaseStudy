package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    public static final String PATH_MENU = "src/application/menu/data/menu.dat";
    public static final String PATH_ORDER = "src/application/order/data/data.dat";
    public static final String PATH_EMPLOYEE = "src/application/employee/data/employees.dat";
    public static String currentUser;

    public static Stage stage;

    public static URL getResource(String url){
        return App.class.getResource(url);
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login/login.fxml"));
        primaryStage.setTitle("login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}