package com.jdbc;

import java.sql.*;

public class DemoClass {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3307/student";
		String uname = "root";
		String pass = "root";
		int id = 10;
		String name = "spiderman";
		int phone = 123456789;
		String query = "insert into details values(" + id + ",'" + name + "'," + phone +")";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
