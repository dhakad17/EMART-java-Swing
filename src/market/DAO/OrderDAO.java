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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import market.dbutil.DBConection;
import market.pojo.ProductPojo;
import market.pojo.UserProfile;

/**
 *
 * @author hp
 */
public class OrderDAO {
    public static String getNextProductId() throws SQLException
    {
        Connection conn=DBConection.getConnection();
        Statement st=conn.createStatement();
        
        ResultSet rs=st.executeQuery("select max(order_id) from orders");
        
        rs.next();
        String ordId=rs.getString(1);
        
        if (ordId==null) {
            return "O-101";
        }
        int orderno=Integer.parseInt(ordId.substring(2));
        orderno=orderno+1;
        
        return "O-"+orderno;
    }
    public static boolean addOrder(ArrayList<ProductPojo> al,String ordid)throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?)");
        int count=0;
        for(ProductPojo p:al)
        {
            ps.setString(1, ordid);
            ps.setString(2, p.getProductId());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, UserProfile.getUserid());
            count=count+ps.executeUpdate();
        }
        return count==al.size();
    }
    public static HashMap showOrderId(String orderId)throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select p_id , quantity from orders where order_id=?");
        HashMap<String,String> prod=new HashMap<>();
        ps.setString(1, orderId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
           prod.put(rs.getString(1), rs.getString(2));
        }
        return prod;
    }
     public static List<ProductPojo> getProductDetails() throws SQLException
    {
        Connection conn=DBConection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from products where status='Y' order by p_id");
        ArrayList<ProductPojo> productlist=new ArrayList<>();
        while(rs.next())
        {
            ProductPojo p=new ProductPojo();
            p.setProductId(rs.getString(1));
            p.setProductName(rs.getString(2));
            p.setProductComapny(rs.getString(3));
            p.setProductPrice(rs.getDouble(4));
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
            
            productlist.add(p);
        }
        return productlist;
    }
}
