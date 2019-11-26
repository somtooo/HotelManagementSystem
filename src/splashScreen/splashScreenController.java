package splashScreen;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class splashScreenController implements Initializable {
    @FXML
    private ImageView splashScreenImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeTransition =  new FadeTransition(Duration.millis(4000), splashScreenImage );
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage loginScreen = new Stage();
                Parent root = null;
                try{
                    root = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
                }catch (Exception e){
                    System.out.println("this is error: " + e);
                }
                Stage current = (Stage) splashScreenImage.getScene().getWindow();
                Scene scene = new Scene(root, 720,600);
                loginScreen.setScene(scene);
                current.hide();
                loginScreen.show();
            }
        });
    }
}
