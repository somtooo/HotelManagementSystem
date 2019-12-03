package availableRoomsScreen;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import helperFunctions.InsertColumns;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AvailableRooms extends CreateNewStage implements Initializable {
    public AnchorPane anchorpane;
    public StackPane stackpane;
    private HelperFunctions helperFunctions = new HelperFunctions();
    String status = null;
    public JFXTextField searchByRoomNumber;
    public JFXCheckBox busy;
    public JFXCheckBox available;
    @FXML
    private JFXTreeTableView<Room> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllRooms("SELECT * FROM room");
    }

    public void loadAllRooms(String sql){
        JFXTreeTableColumn<Room, String> room_id = InsertColumns.insertColumnForRoom("Room id");
        JFXTreeTableColumn<Room, String> room_type = InsertColumns.insertColumnForRoom("Room Type");
        JFXTreeTableColumn<Room, String> room_number = InsertColumns.insertColumnForRoom("Room Num");
        JFXTreeTableColumn<Room, String> num_of_people = InsertColumns.insertColumnForRoom("People");
        JFXTreeTableColumn<Room, String> floor_number = InsertColumns.insertColumnForRoom("Floor");
        JFXTreeTableColumn<Room, String> room_phone = InsertColumns.insertColumnForRoom("Room Phone");
        JFXTreeTableColumn<Room, String> room_price = InsertColumns.insertColumnForRoom("Room Price");
        JFXTreeTableColumn<Room, String> room_status = InsertColumns.insertColumnForRoom("Room Status");
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                rooms.add(new Room(resultSet.getInt(1)+"",resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6),
                resultSet.getString(7), resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<Room> root = new RecursiveTreeItem<Room>(rooms, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(room_id,room_type,room_number,num_of_people,floor_number,room_phone,room_price,room_status);
        treeView.setRoot(root);
        treeView.setShowRoot(false);



    }

    public void searchByRoomNumber(MouseEvent event) {
        loadAllRooms("SELECT * FROM room WHERE roomNumber ='"+searchByRoomNumber.getText().trim()+"'");
    }

    public void makeAvailable(MouseEvent event) {
        String text = searchByRoomNumber.getText().trim();
        int res = 0;
        String sql = "UPDATE room SET roomStatus=? WHERE roomNumber =?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"available");
            preparedStatement.setString(2, text);
            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Record updated successfully");
            alert.showAndWait();
            loadAllRooms("SELECT * FROM `room` WHERE 1");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error!");
            alert.showAndWait();
        }

    }

    public void searchByStatus(MouseEvent event) {
        loadAllRooms(status);
    }

    public void goBack(MouseEvent event) {
        newStage("../homeScreen/homeScreen.fxml",anchorpane);
    }

    public void close(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,false);
    }

    public void searchByBusy(ActionEvent actionEvent) {
        available.setSelected(false);
        status = "SELECT * FROM room WHERE roomStatus= 'busy'";
    }

    public void searchByAvailable(ActionEvent actionEvent) {
        busy.setSelected(false);
        status = "SELECT * FROM room WHERE roomStatus = 'available'";
    }
}
