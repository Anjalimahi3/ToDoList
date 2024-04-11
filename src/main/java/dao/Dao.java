package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;


import java.sql.Blob;

import dto.Task;
import dto.User;

public class Dao   {

	public static Connection getconnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root","root");
		return con;
		
	}
	public  int saveuser(User user) throws SQLException, ClassNotFoundException {
		int userid=Dao.genUserId();
		user.setUserid(userid);
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("insert into user values(?,?,?,?,?,?)");
		pst.setInt(1, userid);
		pst.setString(2, user.getUsername());
		pst.setString(3, user.getUseremail());
		pst.setLong(4,user.getUsercontact());
		pst.setString(5, user.getUserpassword());
		pst.setBlob(6, new SerialBlob(user.getUserimage()));
		return pst.executeUpdate();
		
	}
	
	public User findByEmail(String email) throws ClassNotFoundException, SQLException {
		Connection con=getconnection();
		PreparedStatement ps= con.prepareStatement("select * from user where useremail=?");
		try {
		ps.setString(1, email);
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			User u= new User();
			u.setUserid(rs.getInt(1));
			u.setUsername(rs.getString(2));
			u.setUseremail(rs.getString(3));
			u.setUsercontact(rs.getLong(4));
			u.setUserpassword(rs.getString(5));
			//convert blob image to byte
			Blob imageBlob=rs.getBlob(6);
			byte[] image=imageBlob.getBytes(1, (int)imageBlob.length());
			u.setUserimage(image);
		
			return u;
		}
		else {
			return null;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//Create Task
	public int createtask(Task task) throws ClassNotFoundException, SQLException {
		
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("insert into task values(?,?,?,?,?,?,?)");
		pst.setInt(1,task.getTaskid());
		pst.setString(2, task.getTasktitle());
		pst.setString(3, task.getTaskdescription());
		pst.setString(4, task.getTaskpriority());
		pst.setString(5, task.getTaskduedate());
		pst.setString(6, task.getTaskstatus());
		pst.setInt(7, task.getUserid());
		int res=pst.executeUpdate(); 
		return res; 
	}
	//user id generator
		public static int genUserId() throws ClassNotFoundException, SQLException {
			Connection con=getconnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select max(userid) from user");
	        int res=0;
	        if(rs.next()) {
	        	res=rs.getInt(1);
	        return res+1;
	        }
	        return 1;
		}
		//Task id generator
		public static int genTaskid() throws ClassNotFoundException, SQLException {
			Connection con=getconnection(); 
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery("select max(taskid) from task");
			
			int res=0;
			if(rs.next()) {
				res=rs.getInt(1);
				return res+1;
			}
			else {
				return 1;
			}
				 
		}
		//task id auto generator support
		
	//displaying the task
	public List<Task> getAllTasksByUserId(int userid) throws ClassNotFoundException, SQLException{
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("select * from task where userid=?");
		pst.setInt(1, userid);
		 
		ResultSet rs=pst.executeQuery();
		List<Task> tasks=new ArrayList<Task>();
		while(rs.next()) {
			Task task=new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			tasks.add(task);
		}
		return tasks;
		
	}
	//delete task
	public int deleteTask(int taskid) throws ClassNotFoundException, SQLException {
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("delete from task where taskid=?");
		pst.setInt(1, taskid);
		int res=pst.executeUpdate();
		return  res;
	}
	//
	public Task  gettaskid(int id) throws SQLException, ClassNotFoundException {
		
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("select * from task where taskid=?");
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		Task tasks=new Task(id, rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), "pending", rs.getInt(7));
		return  tasks;
	
		
		
	}
	public int updatetask(Task task) throws SQLException, ClassNotFoundException {
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("update task set tasktitle=?,taskdescription=?,taskpriority=?,taskduedate=?,taskstatus=? where taskid=?");
		
		pst.setString(1, task.getTasktitle());
		pst.setString(2, task.getTaskdescription());
		pst.setString(3, task.getTaskpriority());
		pst.setString(4, task.getTaskduedate());
		pst.setString(5, task.getTaskstatus());
		pst.setInt(6,task.getTaskid());
		
		int res=pst.executeUpdate();   
		return  res;  
		
	}
	
}
