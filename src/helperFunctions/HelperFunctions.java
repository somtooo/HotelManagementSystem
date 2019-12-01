package helperFunctions;

import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class HelperFunctions {

    public static void checkLoginField(JFXTextField field, Boolean bothFieldsEmpty) {
        System.out.println(field.getId());
        String output = field.getId() + " is empty!!";
        if (bothFieldsEmpty){
            output = "Wrong username and Password";
        }
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text(output)
                    .hideAfter(Duration.seconds(13))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView("error.png"));
            notifications.darkStyle();
            notifications.show();

    }

    public static void setStyle(Pane anyPane, boolean mouseEntered){
        if (mouseEntered){
            anyPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px");
        }else {
            anyPane.setStyle("-fx-background-color:#377ce8; -fx-background-radius: 6px");
        }
    }
}
