package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static Connection connection = null;


    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dataBaseName = "heroku_a7d1d4878de55c3";
            String url = "jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_a7d1d4878de55c3" ;
            connection = DriverManager.getConnection(url, "b0ef9deb07fbc8", "2c05e88c");
            System.out.println("connection success");
        }catch (Exception e){
            System.out.println("Connection error: " + e);
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection result = getConnection();
        System.out.println(result);
    }


}
