package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static Connection connection = null;


    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dataBaseName = "hotel";
            String url = "jdbc:mysql://localhost:3306/" + dataBaseName;
            connection = DriverManager.getConnection(url, "root", "");
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
