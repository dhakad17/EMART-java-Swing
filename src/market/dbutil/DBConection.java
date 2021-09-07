/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dhakadJr
 */
public class DBConection {
    private static Connection conn;
    static {
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-TP27GJG:1521/xe","grocery","grocery");
            
            JOptionPane.showMessageDialog(null, "Connection opened successfully!!","success",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error in loading the driver !!","error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error in opening Connection !!","error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
            
        }
    
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection(){
        
        try {
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection closed successfully!!","Success",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error in closing connection !!","error",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
            
            
        }
    }
    
}
