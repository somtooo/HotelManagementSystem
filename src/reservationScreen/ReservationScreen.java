package reservationScreen;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationScreen extends CreateNewStage implements Initializable {
    public JFXTextField customerName;
    public JFXTextField phone;
    public JFXTextField address;
    public JFXTextField email;
    public JFXTextField duration;
    public JFXTextField numOfPeople;
    public JFXTextField paymentType;
    public JFXTextField roomType;
    public JFXTextField roomNumber;
    public JFXTextField price;
    public JFXTextField services;
    public JFXTextField total;
    public JFXDatePicker startDate;
    public JFXDatePicker endDate;
    public AnchorPane anchorpane;
    public StackPane stackpane;
    private HelperFunctions helperFunctions = new HelperFunctions();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void back(MouseEvent event) {
        newStage("../homeScreen/homeScreen.fxml",anchorpane);
    }

    public void close(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,false);
    }

    public void rest(MouseEvent event) {
        customerName.setText("");
        email.setText("");
        address.setText("");
        phone.setText("");
        paymentType.setText("");
        numOfPeople.setText("");
        roomNumber.setText("");
        roomType.setText("");
        price.setText("");
        total.setText("");

    }

    public void book(MouseEvent event) {
        int res = 0;
        String sql = "INSERT INTO customer (name,email,address,phone,numOfPeople,paymentType,duration,roomType,roomNumber,startDate,endDate,price,services,total) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerName.getText());
            preparedStatement.setString(2, email.getText());
            preparedStatement.setString(3, address.getText());
            preparedStatement.setString(4, phone.getText());
            preparedStatement.setString(5, numOfPeople.getText());
            preparedStatement.setString(6, paymentType.getText());
            preparedStatement.setString(7, duration.getText());
            preparedStatement.setString(8, roomType.getText());
            preparedStatement.setString(9, roomNumber.getText());
            preparedStatement.setString(10, startDate.getValue().toString());
            preparedStatement.setString(11, endDate.getValue().toString());
            preparedStatement.setString(12, price.getText());
            preparedStatement.setString(13, services.getText());
            preparedStatement.setString(14, total.getText());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (res>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Data updated successfully");
            alert.showAndWait();
            updateStatus();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

    private void updateStatus() {
        String text = roomNumber.getText().trim();
        String sql = "UPDATE room SET roomStatus=? WHERE roomNumber=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"busy");
            preparedStatement.setString(2,text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print(MouseEvent event) {
    }


}
