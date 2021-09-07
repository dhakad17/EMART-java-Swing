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
import java.util.List;
//import java.util.Collections.list;

import market.dbutil.DBConection;
import market.pojo.EmployeesPojo;

/**
 *
 * @author dhakadJr
 */
public class employeeDAO {
    public static String getNextEmpID() throws SQLException
    {
        Connection conn=DBConection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(empid) from employees");
        
        rs.next();
        String empid=rs.getString(1).substring(1);
        int empno=Integer.parseInt(empid);
        empno=empno+1;
        return "E"+empno;
        
    }
    
    public static boolean addEmployee(EmployeesPojo emp) throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into employees values (?,?,?,?)");
        
        ps.setString(1, emp.getEmpid());
        ps.setString(2, emp.getEmpname());
        ps.setString(3,emp.getJob());
        ps.setDouble(4, emp.getSalary());
        
        int result=ps.executeUpdate();
        return result==1;
    }

    
    public static List<EmployeesPojo> getAllEmployee()throws SQLException
    {
        Connection conn=DBConection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from employees order by empid");
        
        ArrayList<EmployeesPojo> emplist=new ArrayList<>();
        while(rs.next())
        {
            EmployeesPojo emp=new EmployeesPojo();
            
            emp.setEmpid(rs.getString(1));
            emp.setEmpname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSalary(rs.getDouble(4));
            
            emplist.add(emp);
        }
        return emplist;
    }
    public static List<String> getAllEmpID() throws  SQLException
    {
          Connection conn=DBConection.getConnection();
          Statement st=conn.createStatement();
          ResultSet rs=st.executeQuery("select empid from employees order by empid");
          
          ArrayList<String > allid=new ArrayList<>();
          
          while(rs.next())
          {
              allid.add(rs.getString(1));
          }
          return allid;
    }
    public static EmployeesPojo findEmpById(String empid) throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from employees where empid=?");
        
        ps.setString(1, empid);
        
        ResultSet rs=ps.executeQuery();
        
        rs.next();
        EmployeesPojo e=new EmployeesPojo();
        e.setEmpid(rs.getString(1));
        e.setEmpname(rs.getString(2));
        e.setJob(rs.getString(3));
        e.setSalary(rs.getDouble(4));
        
        return e;
    }
    public static boolean updateEmployee(EmployeesPojo e)throws SQLException
    {
        Connection conn=DBConection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update employees set empname=? ,job=?, salary=? where empid=? ");
        
        ps.setString(1, e.getEmpname());
        ps.setString(2, e.getJob());
        ps.setDouble(3, e.getSalary());
        ps.setString(4, e.getEmpid());
        System.out.println("run1");
        int x=ps.executeUpdate(); 
         System.out.println(x);
        if (x==0) {
            return false;
        }
        
        else{
            boolean result=UserDAO.isUserPresent(e.getEmpid());
              System.out.println("result of user present"+result);      
            if (result==false) {
                return true;
            }
        ps=conn.prepareStatement("update users set username=?,usertype=? where empid=?");
        
        ps.setString(1, e.getEmpname());
        ps.setString(2, e.getJob());
        ps.setString(3, e.getEmpid());
        
        int y=ps.executeUpdate();
            System.out.println("y="+y);
        return y==1;
        }
        
    }
}
