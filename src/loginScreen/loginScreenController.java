package loginScreen;

import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginScreenController extends CreateNewStage {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private  void loginButton(MouseEvent event){
        boolean isExist = false;
        String userPass = "";
        String userType = "";
        String sql = "select * from users where username='" + username.getText().trim()+"'";
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                isExist = true;
                userPass = resultSet.getString(3);
                userType = resultSet.getString(9);

            }

            if (isExist){
                if (password.getText().trim().equals(userPass)){
                    if (userType.equals("admin")){
                        //if user is admin --> Admin Screen

                        Stage adminScreen = new Stage();
                        newStage(adminScreen,"../adminScreen/adminScreen.fxml",anchorPane);
                    }else {
                        //if user is normal --> Home Screen
                        Stage homeScreen = new Stage();
                        newStage(homeScreen, "../homeScreen/homeScreen.fxml",anchorPane);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, e);
        }


    }
}
