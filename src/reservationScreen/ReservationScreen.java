package reservationScreen;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import loginScreen.loginScreenController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationScreen extends CreateNewStage implements Initializable {
    public JFXTextField customerName;
    public JFXTextField phone;
    public JFXTextField address;
    public JFXTextField email;
    public Label duration;


    public JFXComboBox roomType;
    public Label roomNumber;
    public Label price;
    public Label services;
    public Label total;
    public JFXDatePicker startDate;
    public JFXDatePicker endDate;
    public AnchorPane anchorpane;
    public StackPane stackpane;
    public Label numOfPeople;
    public JFXComboBox paymentType;
    private HelperFunctions helperFunctions = new HelperFunctions();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomType.getItems().add("Double Room");
        roomType.getItems().add("Single Room");
        paymentType.getItems().add("Cash");
        paymentType.getItems().add("Card");

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
//        paymentType.setText("");
        numOfPeople.setText("");
        roomNumber.setText("");
//        roomType.setText("");
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
            preparedStatement.setString(6, paymentType.getValue().toString());
            preparedStatement.setString(7, duration.getText());
            preparedStatement.setString(8, roomType.getValue().toString());
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

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public void load(MouseEvent event) {
        if (startDate.getValue() != null && endDate.getValue()!= null) {
            LocalDate date_value_start = startDate.getValue();
            LocalDate date_value_end = endDate.getValue();
            long daysBetween = DAYS.between(date_value_start, date_value_end);
            duration.setText(String.valueOf(daysBetween));

            Object roomTypeSelection = roomType.getValue();
            if (String.valueOf(roomTypeSelection).equals("Double Room")) {
                numOfPeople.setText("2");
            } else if (String.valueOf(roomTypeSelection).equals("Single Room")){
                numOfPeople.setText("1");
            }

            boolean isExist = false;
            String numbers = "";
            String userType = "";
            String sql = "select * from heroku_a7d1d4878de55c3.customer";
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                int random = getRandomNumberInRange(100, 500);
                while (resultSet.next()) {
                    numbers = resultSet.getString(10);
                    if (String.valueOf(random).equals(numbers)) {
                        isExist = true;
                    }
                }
                if (isExist) {
                    roomNumber.setText(String.valueOf(random + random));
                } else {
                    roomNumber.setText(String.valueOf(random));
                }
            } catch (SQLException e) {
                Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, e);
            }

            double serviceFee = getRandomNumberInRange(12,30) * 0.2;
            double pricefee = getRandomNumberInRange(400,1000) *0.3;
            double taxfee = pricefee *1.07;
            double totalfee = serviceFee + pricefee + taxfee;

            price.setText(String.valueOf(pricefee));
            services.setText(String.valueOf(Math.round(serviceFee)));
            total.setText(String.valueOf(Math.round(totalfee)));
        }




    }
}
