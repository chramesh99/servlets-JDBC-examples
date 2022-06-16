package com.loginandregistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String passwordString = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		try {
			String query = "insert into demotable (name, password) values (?, ?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, passwordString);
			statement.executeUpdate();
			pw.println("added successfully....");
			request.getRequestDispatcher("index.html").include(request, response);
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
