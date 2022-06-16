package com.loginpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
//		Cookie ck[] = req.getCookies();
		PrintWriter pWriter = resp.getWriter();
//		if (ck!=null) {
//			String name = ck[0].getValue();
//			
//			if (name != null) {
//				pWriter.println("This is profile page");
//				pWriter.println("hello " + name);
//			}
//		}else {
//			pWriter.println("Login requried");
//		}
		
		HttpSession session = req.getSession(false);
		if (session != null) {
			String nameString = (String) session.getAttribute("name");
			pWriter.println("hello " + nameString + "welcome to profile page");
		}
		else {
			pWriter.println("login required");
		}
		
	}
}
