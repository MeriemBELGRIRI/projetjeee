/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionbd {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stdmgdb?useSSL=false&serverTimezone=UTC", "root", "");  
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any errors that occur during loading the driver or connecting to the database
            System.out.println(e.getMessage());
        }
        return con; // Return the Connection object
    }
}