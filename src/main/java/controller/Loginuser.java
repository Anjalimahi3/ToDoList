package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Task;
import dto.User;
@WebServlet("/Loginuser")
public class Loginuser extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		Dao dao=new Dao();
		try {
			User u=dao.findByEmail(email);
			if(u!=null) {
				//verify the passowrd
				if(u.getUserpassword().equals(password)) {
					HttpSession session=req.getSession();
					session.setAttribute("user",u);
				
					List<Task> tasks=dao.getAllTasksByUserId(u.getUserid());
					req.setAttribute("tasks",tasks);
					
					
					req.getRequestDispatcher("home.jsp").include(req, resp);
				}
				else {
					req.setAttribute("message", "password wrong");
					req.getRequestDispatcher("login.jsp").include(req, resp);
				}
			}
			else {
				req.getRequestDispatcher("addtask.jsp").include(req, resp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
