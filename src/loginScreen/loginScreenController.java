package loginScreen;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.HelperFunctions;
import helperFunctions.CreateNewStage;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginScreenController extends CreateNewStage {
    HelperFunctions helperFunctions = new HelperFunctions();
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;

    @FXML
    private  void loginButton(MouseEvent event){
        if (username.getText().equals("")){
            HelperFunctions.checkLoginField(username,false);
        }else if (password.getText().equals("")){
           HelperFunctions.checkLoginField(password,false);
        }else {
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
                            newStage("../adminScreen/adminScreen.fxml",anchorPane);
                        }else {
                            //if user is normal --> Home Screen
                            Stage homeScreen = new Stage();
                            newStage("../homeScreen/homeScreen.fxml",anchorPane);
                        }
                    }
                }else {
                    HelperFunctions.checkLoginField(username,true);
                }
            } catch (SQLException e) {
                Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, e);
            }

        }

    }

    @FXML
    private void closeButton(MouseEvent event){
        helperFunctions.closeWindow(stackPane,false);
    }


}
