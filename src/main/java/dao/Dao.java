package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.rowset.serial.SerialBlob;

import java.sql.Blob;

import dto.User;

public class Dao   {

	public static Connection getconnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root","root");
		return con;
		
	}
	public static int saveuser(User user) throws SQLException, ClassNotFoundException {
		Connection con=getconnection();
		PreparedStatement pst=con.prepareStatement("insert into user values(?,?,?,?,?,?)");
		pst.setInt(1, user.getUserid());
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
}
