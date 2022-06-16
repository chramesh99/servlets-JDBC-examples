package com.loginandregistration;

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
@WebServlet("/registration")
public class Registration extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String nameString = request.getParameter("name");
		String passwordString = request.getParameter("password");
		PrintWriter pWriter = response.getWriter();
		try {
			int count = 0;
			String queryString = "select * from demotable";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(queryString);
			while(rs.next()) {
				String dbname = rs.getString(2);
				if(dbname.equals(nameString)) {
					count ++;
				}
			}
			System.out.println(count);
			if(count < 1) {
				String insertquery = "insert into demotable (name, password) values (?, ?)";
				PreparedStatement statement2 = connection.prepareStatement(insertquery);
				statement2.setString(1, nameString);
				statement2.setString(2, passwordString);
				statement2.executeUpdate();	
			}
			else {
				pWriter.println("name already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
