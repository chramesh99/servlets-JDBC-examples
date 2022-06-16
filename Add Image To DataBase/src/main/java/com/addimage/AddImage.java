package com.addimage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig
@WebServlet("/AddImage")
public class AddImage extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("printed");
		Part filePart = request.getPart("image");//to get the image we must to use the part
		
		String imageFileNameString = filePart.getSubmittedFileName();//by using getSubmittedFilename() we are getting the uploaded file name
		System.out.println(imageFileNameString);
		
		// After getting the fileName we want to add it into the images folder also
		
		String uploadPathString = "C:/Users/d1/eclipse-workspace/Add Image To DataBase/src/main/webapp/images/" + imageFileNameString;
		System.out.println(uploadPathString);
		
		// After getting the uploadpath we want to get the outputstream object and inputstream object
		
		FileOutputStream fileOutputStream = new FileOutputStream(uploadPathString);
		InputStream iStream = filePart.getInputStream();
		
		byte[] data = new byte[iStream.available()];
		iStream.read(data);
		fileOutputStream.write(data);
		fileOutputStream.close();
		
		try {
			String insertImageString = "insert imagestore (imageFilename) values (?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "root");
			PreparedStatement statement = connection.prepareStatement(insertImageString);
			statement.setString(1, imageFileNameString);
			int row = statement.executeUpdate();
			if(row > 0) {
				System.out.println("Image upload successfully");
			}
			else {
				System.out.println("Failed to upload");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
