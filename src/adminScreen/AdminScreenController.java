package adminScreen;

import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class AdminScreenController extends CreateNewStage {
    private HelperFunctions helperFunctions = new HelperFunctions();
    public Pane home;
    public Pane employees;
    public Pane customers;
    public Pane logout;
    public Pane exit;
    public AnchorPane anchorpane;
    public StackPane stackpane;

    public void homeMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(home,false);
    }

    public void homeMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(home,true);
    }

    public void employeesMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(employees,false);
    }

    public void employeesMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(employees,true);
    }

    public void customersMouseHover(MouseEvent event) {
        HelperFunctions.setStyle(customers,false);
    }

    public void customersMouseExit(MouseEvent event) {
        HelperFunctions.setStyle(customers,true);

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

    public void openHomeScreen(MouseEvent event) {
        newStage("../homeScreen/homeScreen.fxml",anchorpane);
    }

    public void openEmployeeScreen(MouseEvent event) {
        newStage("../employeeScreen/employeeScreen.fxml",anchorpane);
    }

    public void openCustomersScreen(MouseEvent event) {
        newStage("../customerInfoScreen/CustomerInfoScreen.fxml",anchorpane);
    }

    public void logout(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,true,"../loginScreen/loginScreen.fxml",anchorpane);
    }

    public void exit(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,false);
    }
}
