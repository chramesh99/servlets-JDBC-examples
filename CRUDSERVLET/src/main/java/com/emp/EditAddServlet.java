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
@WebServlet("/EditAddServlet")
public class EditAddServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		String name = request.getParameter("name");
		System.out.println(name);
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		PrintWriter pw = response.getWriter();
		
		try {
			String query = "update crudemp set name = ?, password = ?, email = ?, country = ? where id = ?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, country);
			statement.setInt(5, id);
			statement.executeUpdate();
			statement.close();
			connection.close();
			pw.print("Update successfully");
			request.getRequestDispatcher("FetchServlet").include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
