package controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Dao;
import dto.User;
@WebServlet("/Saveuser")
@MultipartConfig(maxFileSize=10*1024*1024)
public class Saveuser extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email"); 
		long contact=Long.parseLong(req.getParameter("contact"));
		String password=req.getParameter("password");
		 Part imagePart= req.getPart("image");
		 byte[] imagebytes=imagePart.getInputStream().readAllBytes();
		 User user=new User(id, name, email, contact, imagebytes, password);
		 Dao d=new Dao();
		 try {
			 int res=d.saveuser(user);
			 if(res>0) {
				 resp.sendRedirect("login.jsp");
			 }
			 else {
				 resp.sendRedirect("signup.jsp");
			 }
		 }
		 catch(ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
				 }
}