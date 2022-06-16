package com.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/FetchServlet")
public class FetchServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		pWriter.println("<a href='index.html'> Add New Employee </a>");
		
		try {
			String query = "select * from crudemp";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			pWriter.println("<table border='1'>");
			pWriter.println("<tr><th>id</th><th>name</th><th>password</th><th>email</th><th>country</th><th>edit</th><th>delete</th></tr>");
			while(rs.next()) {
				pWriter.println("<tr>" + "<td>"+ rs.getInt(1) +"</td>" +  "<td>"+ rs.getString(2) +"</td>" +  "<td>"+ rs.getString(3) +"</td>" + "<td>"+ rs.getString(4) +"</td>" + "<td>"+ rs.getString(5) +"</td>" +  "<td> <a href='EditServlet?id=" + rs.getInt(1) + "'>Edit</a> </td>" + "<td> <a href='DeleteServlet?id="+rs.getInt(1)+"'>Delete</a> </td>" + "</tr>");
			}
			pWriter.println("</table>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}