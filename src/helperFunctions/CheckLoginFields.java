package helperFunctions;

import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class CheckLoginFields {

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
}
