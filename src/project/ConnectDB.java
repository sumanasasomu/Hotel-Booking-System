/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author RAHUL
 */
import java.sql.*;
public class ConnectDB {
    
    private static ConnectDB conObj = new ConnectDB();
    private ConnectDB()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");            
        }        
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

public static ConnectDB getInstance(){
return conObj;
}
public static Connection getConnection() throws SQLException,ClassNotFoundException{
    Connection connectn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","pass");
    return connectn;
}
}