package employeeScreen;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import customerScreen.Customer;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import helperFunctions.InsertColumns;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeScreen extends CreateNewStage implements Initializable {


    private HelperFunctions helperFunctions = new HelperFunctions();


    @FXML
    private AnchorPane anchorpane;

    @FXML
    private StackPane stackpane;

    @FXML
    private JFXTreeTableView<Employee> treeView;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField salary;

    @FXML
    private JFXTextField userType;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXTextField searchById;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCustomers("SELECT * FROM heroku_a7d1d4878de55c3.users");

    }

    public void loadAllCustomers(String sql){
        JFXTreeTableColumn<Employee, String> id = InsertColumns.insertColumnForEmployees("id");
        JFXTreeTableColumn<Employee, String> username = InsertColumns.insertColumnForEmployees("username");
        JFXTreeTableColumn<Employee, String> password = InsertColumns.insertColumnForEmployees("password");
        JFXTreeTableColumn<Employee, String> fullName = InsertColumns.insertColumnForEmployees("fullName");
        JFXTreeTableColumn<Employee, String> address = InsertColumns.insertColumnForEmployees("address");
        JFXTreeTableColumn<Employee, String> phone = InsertColumns.insertColumnForEmployees("phone");
        JFXTreeTableColumn<Employee, String> startDate = InsertColumns.insertColumnForEmployees("startDate");
        JFXTreeTableColumn<Employee, String> salary = InsertColumns.insertColumnForEmployees("salary");
        JFXTreeTableColumn<Employee, String> userType = InsertColumns.insertColumnForEmployees("userType");


        ObservableList<Employee> employee = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                employee.add(new Employee(resultSet.getInt(1)+"",resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8),resultSet.getString(9)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<Employee> root = new RecursiveTreeItem<Employee>(employee, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(
                id,username,password,fullName,address,phone,
                startDate,salary,userType
        );
        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }

    public void search(MouseEvent event) {
        loadAllCustomers("SELECT * FROM heroku_a7d1d4878de55c3.users WHERE id ='"+searchById.getText().trim()+"'");

    }

    public void insert(MouseEvent event) {
        int res = 0;
        String sql = "INSERT INTO users (username,password,fullName,address,phone,startDate,salary,userType) VALUES(?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username.getText());
            preparedStatement.setString(2, password.getText());
            preparedStatement.setString(3, fullName.getText());
            preparedStatement.setString(4, address.getText());
            preparedStatement.setString(5, phone.getText());
            preparedStatement.setString(6, startDate.getValue().toString());
            preparedStatement.setString(7, salary.getText());
            preparedStatement.setString(8, userType.getText());
            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (res>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Data updated successfully");
            alert.showAndWait();
            loadAllCustomers("SELECT * FROM heroku_a7d1d4878de55c3.users");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

    public void update(MouseEvent event) {
        String text = username.getText().trim();
        String sql = "UPDATE heroku_a7d1d4878de55c3.users SET salary=? WHERE username=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,salary.getText());
            preparedStatement.setString(2,text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(MouseEvent event) {
    }

    public void clear(MouseEvent event) {
        username.setText("");
        password.setText("");
        address.setText("");
        phone.setText("");
        fullName.setText("");
        salary.setText("");
        userType.setText("");
    }

    public void back(MouseEvent event) {
        newStage("../adminScreen/adminScreen.fxml",anchorpane);
    }

    public void close(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,false);
    }


}
