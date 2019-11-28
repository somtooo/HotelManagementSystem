package loginScreen;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.CheckLoginFields;
import helperFunctions.CreateNewStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.cell.ImageGridCell;

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
    private StackPane stackPane;

    @FXML
    private  void loginButton(MouseEvent event){
        if (username.getText().equals("")){
            CheckLoginFields.checkLoginField(username,false);
        }else if (password.getText().equals("")){
           CheckLoginFields.checkLoginField(password,false);
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
                            newStage(adminScreen,"../adminScreen/adminScreen.fxml",anchorPane);
                        }else {
                            //if user is normal --> Home Screen
                            Stage homeScreen = new Stage();
                            newStage(homeScreen, "../homeScreen/homeScreen.fxml",anchorPane);
                        }
                    }
                }else {
                    CheckLoginFields.checkLoginField(username,true);
                }
            } catch (SQLException e) {
                Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, e);
            }

        }



    }

    @FXML
    private void closeButton(MouseEvent event){

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Close"));
        dialogLayout.setBody(new Text("Do you want to exit!"));
        JFXButton ok = new JFXButton("OK");
        JFXButton cancel = new JFXButton("Cancel");
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        ok.setOnAction(event12 -> System.exit(0));
        cancel.setOnAction(event1 -> dialog.close());
        dialogLayout.setActions(ok,cancel);
        dialog.show();

    }


}
