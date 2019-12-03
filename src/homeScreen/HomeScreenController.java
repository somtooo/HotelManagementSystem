package homeScreen;

import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class HomeScreenController extends CreateNewStage {
    public StackPane stackpane;
    private HelperFunctions helperFunctions = new HelperFunctions();
    public Pane reservation;
    public Pane availableRooms;
    public Pane customerInfo;
    public Pane logout;
    public Pane exit;
    public AnchorPane anchorpane;

    public void reservationMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(reservation,false);
    }

    public void reservationMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(reservation,true);
    }

    public void availableRoomsMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(availableRooms,false);
    }

    public void availableRoomsMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(availableRooms,true);
    }

    public void customerInfoMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(customerInfo,false);
    }

    public void customerInfoMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(customerInfo,true);

    }

    public void logoutMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(logout,false);

    }

    public void logoutMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(logout,true);

    }

    public void exitMouseHover() {
        HelperFunctions.setStyle(exit,false);

    }

    public void exitMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(exit,true);

    }

    public void openReservationScreen(MouseEvent event) {
        newStage("../reservationScreen/reservationScreen.fxml",anchorpane);
    }

    public void openAvailableRoomsScreen(MouseEvent event) {
        newStage("../availableRoomsScreen/availableRooms.fxml",anchorpane);
    }

    public void openCustomerInfoScreen(MouseEvent event) {
        newStage("../customerScreen/customerScreen.fxml",anchorpane);
    }

    public void logout(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,true,"../loginScreen/loginScreen.fxml",anchorpane);
    }

    public void exit(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,false);
    }
}
