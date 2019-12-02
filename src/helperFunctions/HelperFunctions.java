package helperFunctions;
import availableRoomsScreen.AvailableRooms;
import com.jfoenix.controls.*;
import database.Room;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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

    public static JFXTreeTableColumn<Room, String> insertColumn(String text){
        switch (text) {
            case "Room id":
                JFXTreeTableColumn<Room, String> room_id = new JFXTreeTableColumn<>(text);
                room_id.setPrefWidth(100);
                room_id.setCellValueFactory(param -> param.getValue().getValue().id);
                return room_id;
            case "Room Type":
                JFXTreeTableColumn<Room, String> roomType = new JFXTreeTableColumn<>(text);
                roomType.setPrefWidth(100);
                roomType.setCellValueFactory(param -> param.getValue().getValue().roomType);
                return roomType;
            case "Room Num":
                JFXTreeTableColumn<Room, String> room_number = new JFXTreeTableColumn<>(text);
                room_number.setPrefWidth(100);
                room_number.setCellValueFactory(param -> param.getValue().getValue().roomNumber);
                return room_number;
            case "People":
                JFXTreeTableColumn<Room, String> num_of_people = new JFXTreeTableColumn<>(text);
                num_of_people.setPrefWidth(70);
                num_of_people.setCellValueFactory(param -> param.getValue().getValue().numberOfPeople);
                return num_of_people;
            case "Floor":
                JFXTreeTableColumn<Room, String> floor_number = new JFXTreeTableColumn<>(text);
                floor_number.setPrefWidth(100);
                floor_number.setCellValueFactory(param -> param.getValue().getValue().floorNumber);
                return floor_number;
            case "Room Phone":
                JFXTreeTableColumn<Room, String> room_phone = new JFXTreeTableColumn<>(text);
                room_phone.setPrefWidth(100);
                room_phone.setCellValueFactory(param -> param.getValue().getValue().roomPhone);
                return room_phone;
            case "Room Price":
                JFXTreeTableColumn<Room, String> room_price = new JFXTreeTableColumn<>(text);
                room_price.setPrefWidth(100);
                room_price.setCellValueFactory(param -> param.getValue().getValue().roomPrice);
                return room_price;
            case "Room Status":
                JFXTreeTableColumn<Room, String> room_status = new JFXTreeTableColumn<>(text);
                room_status.setPrefWidth(100);
                room_status.setCellValueFactory(param -> param.getValue().getValue().roomStatus);
                return room_status;
            default:
                throw new IllegalArgumentException("Use matching text");
        }
    }
}