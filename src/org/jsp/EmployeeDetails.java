package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import java.sql.PreparedStatement;


@WebServlet("/EmployeeDetails")
public class EmployeeDetails extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException 
	{
		
		String name=req.getParameter("name");
		String tempsal=req.getParameter("sal");
		double salary=Double.parseDouble(tempsal);
		String tempdeptno=req.getParameter("dept");
		int deptno=Integer.parseInt(tempdeptno);
		
		String url="jdbc:mysql://localhost:3306/teja20?user=root&password=12345";
		String insert="insert into employee(name,salary,deptno)values(?,?,?)";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url);
			PreparedStatement ps=con.prepareStatement(insert);
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setInt(3, deptno);
			int num=ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if(num!=0)
			{
				pw.println("Data inserted ..");
			}
			else
			{
				pw.println("No Data Inserted...");
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
