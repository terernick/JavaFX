/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentsnick;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nick
 */
public class JdbcDao2 {
   private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_registration?useSSL=false"; 
   private static final String DATABASE_USERNAME = "root"; 
   private static final String DATABASE_PASSWORD = "root"; 
   private static final String SELECT_QUERY = "SELECT * FROM registration WHERE NAME = ? and PASSWORD = ?";
        
   
   public boolean validate(String nameId, String passwordId) throws SQLException { 
            // Step 1: Establishing a Connection and 
            // try-with-resource statement will auto close the connection. 
        try (Connection connection = DriverManager 
            .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
 
            // Step 2:Create a statement using connection object 
           PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) { 
           ps.setString(1, nameId);
           ps.setString(2, passwordId);
    
           System.out.println(ps);
           ResultSet resultSet = ps.executeQuery(); 
            if (resultSet.next()) { 
                return true;
            }
            
            }
        
        catch (SQLException e) { 
        // print SQL exception information 
            printSQLException(e); 
            } 
            return false; 
}
   
   
   private void printSQLException(SQLException ex) {
        for (Throwable e: ex) { 
            if (e instanceof SQLException) {
               e.printStackTrace(System.err); 
               System.err.println("SQLState: " + ((SQLException) e).getSQLState()); 
               System.err.println("Error Code: " + ((SQLException) e).getErrorCode()); 
               System.err.println("Message: " + e.getMessage()); 
               Throwable t = ex.getCause(); 
               while (t != null) { 
                   System.out.println("Cause: " + t); 
                   t = t.getCause(); 
               } 
            } 
        }
    }
}
