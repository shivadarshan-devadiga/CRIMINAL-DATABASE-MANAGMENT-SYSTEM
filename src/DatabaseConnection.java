package src;


import java.sql.*;

public class DatabaseConnection {
    
    Connection c;
    Statement s;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306//criminal", "root", "Shiva2212#");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}