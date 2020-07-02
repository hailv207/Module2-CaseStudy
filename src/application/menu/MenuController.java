package application.menu;

import application.App;
import application.filemanager.FileManager;
import application.order.Order;
import application.order.OrderItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    TableView<MenuItem> menuView;
    @FXML
    TableColumn<MenuItem, String> menuNameCol;
    @FXML
    TableColumn<MenuItem, String> unitCol;
    @FXML
    TableColumn<MenuItem, Long> priceCol;

    @FXML
    TableView<OrderItem> orderTable;
    @FXML
    TableColumn<OrderItem, String> orderNameCol;
    @FXML
    TableColumn<OrderItem, String> orderUnitCol;
    @FXML
    TableColumn<OrderItem, Long> orderPriceCol;
    @FXML
    TableColumn<OrderItem, Integer> orderQuantityCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemUnit"));
        priceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Long>("itemPrice"));

        menuNameCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("itemUnit"));
        priceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Long>("itemPrice"));


        menuView.setRowFactory(tv -> {
            TableRow<MenuItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    MenuItem clickedRow = row.getItem();
                    onClickItem(clickedRow);
                }
            });
            return row ;
        });

        FileManager<MenuItem> fileManager = new FileManager<>();
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem("GAQUAY", "lon Quay ", "Con", 3000000, true));
        list.add(new MenuItem("GAQUAY", "Ga Quay ", "Con", 3000000, true));
        list.add(new MenuItem("GAQUAY", "cho Quay Ha Noi", "Con", 3000000, true));

        fileManager.write(App.PATH_MENU, list);

        menuView.getItems().clear();

        for (MenuItem item: MenuManager.getMenuList()){
            System.out.println(item);
            if (item.isStatus())
                menuView.getItems().add(item);
        }

        Order order = new Order();


    }

    public void onClickItem(MenuItem menuItem){
        System.out.println();
    }


}
