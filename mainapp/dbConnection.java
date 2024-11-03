package mainapp;

import java.sql.*;
import javax.swing.JOptionPane;

public class dbConnection {
    static Connection conn = null;

    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:../SQLite_DataBase/suivimpayes.db");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
