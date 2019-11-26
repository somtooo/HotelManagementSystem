package helperFunctions;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loginScreen.loginScreenController;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateNewStage {


    public void newStage(Stage newStageName, String fxmlName, AnchorPane username){
        System.out.println("I was called");
        newStageName = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(fxmlName));
        }catch (IOException ex){
            Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage current = (Stage)username.getScene().getWindow();
        Scene scene = new Scene(root);

        newStageName.setScene(scene);
        newStageName.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        newStageName.show();
    }

    public void print(){
        System.out.println("I was called");
    }


}
