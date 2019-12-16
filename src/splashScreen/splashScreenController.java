package splashScreen;

import helperFunctions.CreateNewStage;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import loginScreen.loginScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        newStage("../loginScreen/loginScreen.fxml",anchorPane);

    });
    }
}
