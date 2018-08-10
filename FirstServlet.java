package org.btm.postApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String sph=req.getParameter("ph");
		long phone=Long.parseLong(sph);
		String name=req.getParameter("nm");
		String adar=req.getParameter("adh");
		long aadhar=Long.parseLong(adar);
		PrintWriter out=resp.getWriter();
		out.println("<html><body bgcolor='blue'>"
				+ "<h1>You are details are"+name+" "+phone+" "+aadhar+" "+" SUCCESSFULLY ENTERED"+"</h1>"
						+ "</body></html>");//PRESENTATION LOGIC//
		out.flush();
		out.close();
		
		//Connecting to server
		
		Connection con=null;
		PreparedStatement pstmt=null;
		String qry="insert into muzeeb.details values(?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=muzeeb");
			pstmt=con.prepareStatement(qry);
			
			//SET VALUES FOR PLACEHOLDER
			
			pstmt.setString(1,name);
			pstmt.setLong(2, phone);
			pstmt.setLong(3, aadhar);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
