package homeScreen;

import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class HomeScreenController extends CreateNewStage {
    public Pane reservation;
    public Pane availableRooms;
    public Pane customerInfo;
    public Pane logout;
    public Pane exit;

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

    }

    public void openAvailableRoomsScreen(MouseEvent event) {
    }

    public void openCustomerInfoScreen(MouseEvent event) {
    }

    public void logout(MouseEvent event) {
    }

    public void exit(MouseEvent event) {
    }
}
