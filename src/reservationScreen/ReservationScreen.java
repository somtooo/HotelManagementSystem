package reservationScreen;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import helperFunctions.CreateNewStage;
import helperFunctions.HelperFunctions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import loginScreen.loginScreenController;

import java.awt.*;
import java.awt.print.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationScreen extends CreateNewStage implements Initializable {
    public JFXTextField customerName;
    public JFXTextField phone;
    public JFXTextField address;
    public JFXTextField email;
    public Label duration;


    public JFXComboBox roomType;
    public Label roomNumber;
    public Label price;
    public Label services;
    public Label total;
    public JFXDatePicker startDate;
    public JFXDatePicker endDate;
    public AnchorPane anchorpane;
    public StackPane stackpane;
    public Label numOfPeople;
    public JFXComboBox paymentType;
    private HelperFunctions helperFunctions = new HelperFunctions();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomType.getItems().add("Double Room");
        roomType.getItems().add("Single Room");
        paymentType.getItems().add("Cash");
        paymentType.getItems().add("Card");

    }
    public void back(MouseEvent event) {
        newStage("../homeScreen/homeScreen.fxml",anchorpane);
    }

    public void close(MouseEvent event) {
        helperFunctions.closeWindow(stackpane,false);
    }

    public void rest(MouseEvent event) {
        customerName.setText("");
        email.setText("");
        address.setText("");
        phone.setText("");
//        paymentType.setText("");
        numOfPeople.setText("");
        roomNumber.setText("");
//        roomType.setText("");
        price.setText("");
        total.setText("");

    }

    public void print(MouseEvent event) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public void book(MouseEvent event) {
        int res = 0;
        String sql = "INSERT INTO customer (name,email,address,phone,numOfPeople,paymentType,duration,roomType,roomNumber,startDate,endDate,price,services,total) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerName.getText());
            preparedStatement.setString(2, email.getText());
            preparedStatement.setString(3, address.getText());
            preparedStatement.setString(4, phone.getText());
            preparedStatement.setString(5, numOfPeople.getText());
            preparedStatement.setString(6, paymentType.getValue().toString());
            preparedStatement.setString(7, duration.getText());
            preparedStatement.setString(8, roomType.getValue().toString());
            preparedStatement.setString(9, roomNumber.getText());
            preparedStatement.setString(10, startDate.getValue().toString());
            preparedStatement.setString(11, endDate.getValue().toString());
            preparedStatement.setString(12, price.getText());
            preparedStatement.setString(13, services.getText());
            preparedStatement.setString(14, total.getText());

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
            updateStatus();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

    private void updateStatus() {
        String text = roomNumber.getText().trim();
        String sql = "UPDATE room SET roomStatus=? WHERE roomNumber=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"busy");
            preparedStatement.setString(2,text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public void load(MouseEvent event) {
        if (startDate.getValue() != null && endDate.getValue()!= null) {
            LocalDate date_value_start = startDate.getValue();
            LocalDate date_value_end = endDate.getValue();
            long daysBetween = DAYS.between(date_value_start, date_value_end);
            duration.setText(String.valueOf(daysBetween));

            Object roomTypeSelection = roomType.getValue();
            if (String.valueOf(roomTypeSelection).equals("Double Room")) {
                numOfPeople.setText("2");
            } else if (String.valueOf(roomTypeSelection).equals("Single Room")){
                numOfPeople.setText("1");
            }

            boolean isExist = false;
            String numbers = "";
            String userType = "";
            String sql = "select * from heroku_a7d1d4878de55c3.customer";
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                int random = getRandomNumberInRange(100, 500);
                while (resultSet.next()) {
                    numbers = resultSet.getString(10);
                    if (String.valueOf(random).equals(numbers)) {
                        isExist = true;
                    }
                }
                if (isExist) {
                    roomNumber.setText(String.valueOf(random + random));
                } else {
                    roomNumber.setText(String.valueOf(random));
                }
            } catch (SQLException e) {
                Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, e);
            }

            double serviceFee = getRandomNumberInRange(12,30) * 0.2;
            double pricefee = getRandomNumberInRange(400,1000) *0.3;
            double taxfee = pricefee *1.07;
            double totalfee = serviceFee + pricefee + taxfee;

            price.setText(String.valueOf(Math.round(pricefee)));
            services.setText(String.valueOf(Math.round(serviceFee)));
            total.setText(String.valueOf(Math.round(totalfee)));
        }




    }

    public class BillPrintable implements Printable {




        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException
        {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());


                FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

                int idLength=metrics.stringWidth("000");
                int amtLength=metrics.stringWidth("000000");
                int qtyLength=metrics.stringWidth("00000");
                int priceLength=metrics.stringWidth("000000");
                int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;



                int productPosition = 0;
                int discountPosition= prodLength+5;
                int pricePosition = discountPosition +idLength+10;
                int qtyPosition=pricePosition + priceLength + 4;
                int amtPosition=qtyPosition + qtyLength;



                try{
                    /*Draw Header*/
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    int headerRectHeighta=40;

                    String  user_name=customerName.getText();
                    String phone=email.getText();
                    String add=address.getText();
                    String payentType=duration.getText();
                    String num=numOfPeople.getText();


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawString("-------------------------------------",12,y);y+=yShift;
                    g2d.drawString("      Hotel Bill Receipt        ",12,y);y+=yShift;
                    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("                                     ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                    g2d.drawString("  Name                    " +user_name+"  ",10,y);y+=yShift;
                    g2d.drawString("  Address                 " +add+"  ",10,y);y+=yShift;
                    g2d.drawString("  Payment                 " +payentType+"  ",10,y);y+=yShift;
                    g2d.drawString("  Number Of People        " +num+"  ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Total amount:      sum=" +total.getText().toString()+"           ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("          Hotel Phone Number         ",10,y);y+=yShift;
                    g2d.drawString("             03111111111             ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("    THANKS TO VISIT OUR HOTEL        ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;



                }
                catch(Exception r){
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight =8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }
}
