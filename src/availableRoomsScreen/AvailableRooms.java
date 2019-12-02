package availableRoomsScreen;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import database.DBConnection;
import database.Room;
import helperFunctions.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AvailableRooms implements Initializable {
    @FXML
    private JFXTreeTableView<Room> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllRooms("SELECT * FROM room");
    }

    public void loadAllRooms(String sql){
        JFXTreeTableColumn<Room, String> room_id = HelperFunctions.insertColumn("Room id");
        JFXTreeTableColumn<Room, String> room_type = HelperFunctions.insertColumn("Room Type");
        JFXTreeTableColumn<Room, String> room_number = HelperFunctions.insertColumn("Room Num");
        JFXTreeTableColumn<Room, String> num_of_people = HelperFunctions.insertColumn("People");
        JFXTreeTableColumn<Room, String> floor_number = HelperFunctions.insertColumn("Floor");
        JFXTreeTableColumn<Room, String> room_phone = HelperFunctions.insertColumn("Room Phone");
        JFXTreeTableColumn<Room, String> room_price = HelperFunctions.insertColumn("Room Price");
        JFXTreeTableColumn<Room, String> room_status = HelperFunctions.insertColumn("Room Status");
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

    }
