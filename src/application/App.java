package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;

public class App extends Application {

    public static final String PATH_MENU = "src/application/data/menu.dat";
    public static final String PATH_ORDER = "src/application/data/orders.dat";
    public static final String PATH_EMPLOYEE = "src/application/data/employees.dat";
    public static final String PATH_MATERIALS = "src/application/data/materials.dat";
    public static final String PATH_STOCKINRECEIPT = "src/application/data/stockinreceipts.dat";
    public static final String PATH_RECEIPTCOUNTER = "src/application/data/receiptcounter.dat";
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