package com.loginpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		String uName = request.getParameter("username");
	
		String pass = request.getParameter("password");
		
		if (uName.equals("admin") && pass.equals("admin")) {
			RequestDispatcher rDispatcher = request.getRequestDispatcher("homePage.html");
			rDispatcher.include(request, response);
//			Cookie ck = new Cookie("name", uName);
//			response.addCookie(ck);
			HttpSession session = request.getSession();
			session.setAttribute("name", uName);
		}
		else {
			pWriter.println("login failed.");
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/login.html");
			rDispatcher.include(request, response);
		}	
	}	
}
