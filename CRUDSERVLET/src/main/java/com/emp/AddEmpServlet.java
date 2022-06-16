package com.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addservlet")
public class AddEmpServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		PrintWriter pw = response.getWriter();
		
		try {
			String query = "insert into crudemp (name,password,email,country) values (?, ?, ?, ?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, country);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			pw.println("added successfully....");
			request.getRequestDispatcher("index.html").include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("Failed to add record");
		}
		
//		pw.println("<a href='FetchServlet'>go</a>");
		
	}
	
}
