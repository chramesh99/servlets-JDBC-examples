package com.addimage;

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
@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter pWriter = response.getWriter(); 
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			String selectQueryString = "select imageId,imageFilename from imagestore";
			Class.forName("com.mysql.cj.jdbc.Driver");			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(selectQueryString);
			pWriter.print("<table border=1px>");
			while(rSet.next()) {
				System.out.println(rSet.getString("imageFilename"));
				System.out.println(rSet.getInt("imageId"));
				String fileNameString = rSet.getString("imageFilename");
				String imageSourceString = "C:/Users/d1/eclipse-workspace/Add Image To DataBase/src/main/webapp/images/"+fileNameString;
				System.out.println(imageSourceString);
				if(rSet.getInt("imageId") == id) {
					pWriter.print("<tr>");
					pWriter.print("<th>image id</th><th>image</th>");
					pWriter.print("</tr>");
					pWriter.print("<tr>");
					pWriter.print("<td>"+ rSet.getInt("imageId") +"</td>");
					pWriter.print("<td><img src = "+imageSourceString+"></td>");
					pWriter.print("</tr>");
				}
			}
			pWriter.print("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
