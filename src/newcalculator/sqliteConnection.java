package newcalculator;

import javax.swing.*;
import java.sql.*;
import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;

public class sqliteConnection {

    Connection conn = null;

    public static Connection dbConnector() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Aakash\\Desktop\\JavaMiniProject\\MyExpenseManager.sqlite");
           
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

 
}