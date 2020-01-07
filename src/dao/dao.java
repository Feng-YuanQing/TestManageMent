package dao;

import model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class dao {
	public Manager login(Connection con, Manager user)throws Exception
	{
		Manager resultUser = null;
		String sql = "select * from login where Sno =? and Spassword = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassWord());
		ResultSet res = pstmt.executeQuery();
		if(res.next())
		{
			resultUser = new Manager();
			resultUser.setId(res.getInt("Sno"));
			resultUser.setUserName(res.getString("Sname"));
			resultUser.setUserPassWord(res.getString("Spassword"));
		}
		return resultUser;
	}
	
}

