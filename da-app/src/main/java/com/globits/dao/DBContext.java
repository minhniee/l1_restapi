package com.globits.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    public Connection getConnection() {
        Connection connection = null;

        // Update the user, pass, and url to match your MySQL configuration
        try {
            String user = "root"; // Update if necessary
            String pass = "admin";  // Update if necessary
            String url = "jdbc:mysql://localhost:3306/lv2"; // Correct MySQL URL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the MySQL driver
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(new DBContext().getConnection());
    }
}
