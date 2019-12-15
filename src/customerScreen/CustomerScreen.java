package customerScreen;
import availableRoomsScreen.Room;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.InsertColumns;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import loginScreen.loginScreenController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerScreen extends CreateNewStage implements Initializable {
    public JFXTextField searchByRoomNumber;
    public AnchorPane anchorpane;
    @FXML
    private JFXTreeTableView<Customer> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCustomers("SELECT * FROM heroku_a7d1d4878de55c3.customer");
    }

    public void loadAllCustomers(String sql){
        JFXTreeTableColumn<Customer, String> id = InsertColumns.insertColumnForCustomers("id");
        JFXTreeTableColumn<Customer, String> name = InsertColumns.insertColumnForCustomers("name");
        JFXTreeTableColumn<Customer, String> email = InsertColumns.insertColumnForCustomers("email");
        JFXTreeTableColumn<Customer, String> address = InsertColumns.insertColumnForCustomers("address");
        JFXTreeTableColumn<Customer, String> phone = InsertColumns.insertColumnForCustomers("phone");
        JFXTreeTableColumn<Customer, String> numOfPeople = InsertColumns.insertColumnForCustomers("numOfPeople");
        JFXTreeTableColumn<Customer, String> paymentType = InsertColumns.insertColumnForCustomers("paymentType");
        JFXTreeTableColumn<Customer, String> duration = InsertColumns.insertColumnForCustomers("duration");
        JFXTreeTableColumn<Customer, String> roomType = InsertColumns.insertColumnForCustomers("roomType");
        JFXTreeTableColumn<Customer, String> roomNumber = InsertColumns.insertColumnForCustomers("roomNumber");
        JFXTreeTableColumn<Customer, String> startDate = InsertColumns.insertColumnForCustomers("startDate");
        JFXTreeTableColumn<Customer, String> endDate = InsertColumns.insertColumnForCustomers("endDate");
        JFXTreeTableColumn<Customer, String> price = InsertColumns.insertColumnForCustomers("price");
        JFXTreeTableColumn<Customer, String> services = InsertColumns.insertColumnForCustomers("services");
        JFXTreeTableColumn<Customer, String> total = InsertColumns.insertColumnForCustomers("total");

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                customers.add(new Customer(resultSet.getInt(1)+"",resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9),resultSet.getString(10),
                        resultSet.getString(11),resultSet.getString(12),
                        resultSet.getString(13),resultSet.getString(14),
                        resultSet.getString(15)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<Customer> root = new RecursiveTreeItem<Customer>(customers, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(
                id,name,email,address,phone,numOfPeople,
                paymentType,duration,roomType,roomNumber,
                startDate,endDate,price,services,total
        );
        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }

    public void searchByRoomNumber(MouseEvent event) {
        loadAllCustomers("SELECT * FROM heroku_a7d1d4878de55c3.customer WHERE roomNumber ='"+searchByRoomNumber.getText().trim()+"'");
    }

    public void back(MouseEvent event) {
        loginScreenController loginScreenController = new loginScreenController();
        if (loginScreen.loginScreenController.admin) {
            newStage("../adminScreen/adminScreen.fxml", anchorpane);
        }else {
            newStage("../homeScreen/homeScreen.fxml", anchorpane);
        }

    }
}
