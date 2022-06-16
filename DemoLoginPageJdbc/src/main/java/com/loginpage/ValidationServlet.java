package com.loginpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/validate")
public class ValidationServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//getting values from form or index.html file 
		String name = request.getParameter("name");

		String email = request.getParameter("email");

		String phone = request.getParameter("phonenumber");

		String value = request.getParameter("gender");
		PrintWriter out = response.getWriter();
		
		//DataBase connections
		try {
			
			String query = "insert into student (name,email,phonenumber,gender) values(?, ?, ?, ?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			PreparedStatement st = con.prepareStatement(query);
//			Statement statement = con.createStatement();
//			statement.execute(query);
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, phone);
			st.setString(4, value);
			int count = st.executeUpdate();
			request.getRequestDispatcher("homepage.html").include(request, response);
//			if(count != 1) {
//				out.println("fail");
//			}else {
//				out.println("success");
//			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("db store failed");
		}
	}
}
