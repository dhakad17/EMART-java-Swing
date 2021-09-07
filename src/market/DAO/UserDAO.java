/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import market.dbutil.DBConection;
import market.pojo.UserPojo;
import market.pojo.UserProfile;

/**
 *
 * @author dhakadJr
 */
public class UserDAO {
    
    public static boolean validateUser(UserPojo user) throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userid=? and password=? and usertype=? ");
        
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPass());
        ps.setString(3, user.getUsertype());
        
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            UserProfile.setUsername(user.getUsername());
            return true;
        }
        else 
            return false;
    }
    public static boolean isUserPresent(String empid) throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select 1 from users where empid=?");
        ps.setString(1, empid);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        else
            return false;
    }
    
}
