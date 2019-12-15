package cancelScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginScreen.loginScreenController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cancelScreenController extends CreateNewStage {
    public static   boolean admin = false;
    private HelperFunctions helperFunctions = new HelperFunctions();
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
            String sql = "select * from heroku_a7d1d4878de55c3.users where username='" + username.getText().trim()+"'";
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
                            admin = true;
                            System.out.println(admin);
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
                Logger.getLogger(cancelScreenController.class.getName()).log(Level.SEVERE, null, e);
            }

        }

    }

    @FXML
    private void closeButton(MouseEvent event){
        helperFunctions.closeWindow(stackPane,false);
    }


    public void selfReservation(MouseEvent event) {
        newStage("../reservationScreen/reservationScreen.fxml",anchorPane);
    }

    public void cancelReservation(MouseEvent event) {

    }

    public void delete(MouseEvent event) {
        if (username.getText().equals("")) {
            HelperFunctions.checkLoginField(username, false);
        }
        else {
            boolean isExist = false;
            String cname = username.getText().trim();
            String sql = "DELETE from heroku_a7d1d4878de55c3.customer where name='" + cname + "'";
            Connection connection = DBConnection.getConnection();
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("Delete"));
            dialogLayout.setBody(new Text("Do you want to delete!"));
            JFXButton ok = new JFXButton("Yes");
            JFXButton cancel = new JFXButton("No");
            JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
            ok.setOnAction(event12 -> {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    int resultSet = preparedStatement.executeUpdate();
                    if (resultSet>0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Data update");
                        alert.setHeaderText("Information Dialog");
                        alert.setContentText("Data updated successfully");
                        alert.showAndWait();
                        newStage("../loginScreen/loginScreen.fxml",anchorPane);
                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Data update");
                        alert.setHeaderText("Information Dialog");
                        alert.setContentText("No record found in database!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            cancel.setOnAction(event1 -> {
                newStage("../loginScreen/loginScreen.fxml",anchorPane);
            });
            dialogLayout.setActions(ok, cancel);
            dialog.show();
        }
    }
}
