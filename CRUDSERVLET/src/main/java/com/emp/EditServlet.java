package com.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		pw.println("<h1>Update Employee</h1>");
		try {
			String query = "select * from crudemp where id = ?"; 
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			pw.print("<table>");
			pw.print("<form action='EditAddServlet' method='post'>");
			while(rs.next()) {
				pw.print("<tr><td></td><td><input type = 'hidden' name = 'id' value = " + rs.getInt(1) + " ></td></tr>");
				pw.print("<tr><td>Name:</td><td><input type = 'text' name = 'name' value ="+ rs.getString(2) +" required></td></tr>");
				pw.print("<tr><td>Password:</td><td><input type = 'password' name = 'password' value ="+ rs.getString(3) +" required></td></tr>");
				pw.print("<tr><td>Email:</td><td><input type = 'email' name = 'email' value ="+ rs.getString(4) +" required></td></tr>");
				pw.print("<tr><td>country</td>");
				pw.print("<td><select name = 'country'><option value ="+ rs.getString(5) +">"+ rs.getString(5) +"</option>");
				pw.print("<option value = India>India</option>");
				pw.print("<option value = USA>USA</option>");
				pw.print("<option value = UK>UK</option>");
				pw.print("<option value = Other>Other</option>");
				pw.print("</select></td></tr>");
			}
			pw.print("<tr><td><input type = 'submit' value = 'Submit'></td></tr>");
			pw.print("</form>");
			pw.print("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
