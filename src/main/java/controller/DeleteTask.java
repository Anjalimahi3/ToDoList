package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Task;
import dto.User;
@WebServlet("/deletetask")
public class DeleteTask extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int taskid=Integer.parseInt(req.getParameter("id"));
		
		Dao d=new Dao();
		 try {
			
			HttpSession session=req.getSession();
			User u=(User)session.getAttribute("user");
			
			if(u!=null) {
				Task t=d.gettaskid(taskid);
				if(t.getUserid()==u.getUserid()) {
				d.deleteTask(taskid);
			req.setAttribute("tasks",d.getAllTasksByUserId(u.getUserid()));
			RequestDispatcher rd=req.getRequestDispatcher("home.jsp");
			rd.include(req, resp);}
				else {
					resp.sendRedirect("login.jsp");
				}
			} 
			else {
				resp.sendRedirect("login.jsp");			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
