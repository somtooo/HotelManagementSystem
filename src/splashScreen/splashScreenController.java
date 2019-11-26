package splashScreen;

import helperFunctions.CreateNewStage;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class splashScreenController  extends CreateNewStage implements Initializable  {
    @FXML
    private ImageView splashScreenImage;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeTransition =  new FadeTransition(Duration.millis(4000), splashScreenImage );
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> {
            Stage loginScreen = new Stage();
            newStage(loginScreen,"../loginScreen/loginScreen.fxml",anchorPane);
        });
    }
}
