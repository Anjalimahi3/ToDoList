package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Task;
import dto.User;
@WebServlet("/updatetask")
public class Updatetask extends HttpServlet {
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 int taskid=Integer.parseInt(req.getParameter("taskid"));
		String tasktitle=req.getParameter("tasktitle");
		String taskdescription=req.getParameter("taskdescription");
		String taskpriority=req.getParameter("taskpriority");
		String taskduedate=req.getParameter("taskduedate");
		String taskstatus=req.getParameter("taskstatus");
		
		Task tasks=new Task(taskid, tasktitle, taskdescription, taskpriority, taskduedate, "pending");
		
		Dao dao=new Dao();
		  try {
			int res=dao.updatetask(tasks);
			if(res>0) {
				
				User u=(User)req.getSession().getAttribute("user");
				req.setAttribute("tasks",dao.getAllTasksByUserId(u.getUserid()));
				req.getRequestDispatcher("home.jsp").include(req, resp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tasks);
}
}
