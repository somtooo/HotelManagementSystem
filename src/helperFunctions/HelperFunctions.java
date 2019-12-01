package helperFunctions;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sun.plugin.javascript.navig.Anchor;

import java.util.Stack;
import java.util.concurrent.Callable;

public class HelperFunctions extends CreateNewStage {

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

    public  void closeWindow(StackPane stackPane, boolean isLogout, String fxmlName, AnchorPane anchorpane){
        if (isLogout) {
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("Alert"));
            dialogLayout.setBody(new Text("Do you want to logout"));
            JFXButton ok = new JFXButton("Logout");
            JFXButton cancel = new JFXButton("Close");
            JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
            ok.setOnAction(event12 -> newStage(fxmlName,anchorpane));
            cancel.setOnAction(event1 -> dialog.close());
            dialogLayout.setActions(ok, cancel);
            dialog.show();
        }
    }

    public void closeWindow(StackPane stackPane, boolean isLogout){
        if (!isLogout) {
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("Close"));
            dialogLayout.setBody(new Text("Do you want to exit!"));
            JFXButton ok = new JFXButton("OK");
            JFXButton cancel = new JFXButton("Cancel");
            JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
            ok.setOnAction(event12 -> System.exit(0));
            cancel.setOnAction(event1 -> dialog.close());
            dialogLayout.setActions(ok, cancel);
            dialog.show();
        }
    }
}
