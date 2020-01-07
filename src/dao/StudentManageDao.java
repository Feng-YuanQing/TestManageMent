package dao;

import Login.StringUtil;
import model.StudentManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentManageDao {
	
	public static int add(Connection con, StudentManage sm)throws Exception {
		String sql="insert into student values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, sm.getSno());
		pstmt.setString(2, sm.getSname());
		pstmt.setString(3, sm.getSsex());
		pstmt.setString(4, sm.getStel());
		pstmt.setString(5, sm.getSclass());
		pstmt.setString(6, sm.getSid());
		pstmt.setString(7, sm.getSage());
		pstmt.setString(8, sm.getSmajor());
		pstmt.setString(9, sm.getSnative());
		
		return pstmt.executeUpdate();
	}
	public static ResultSet inquire(Connection con, StudentManage stu)throws Exception
	{
		
		StringBuffer sql1 = new StringBuffer("select * from student order by Sno");
		if(StringUtil.isNotEmpty(stu.getSno())) {
			sql1.append(" where Sno like '"+stu.getSno()+"'");
		}
		System.out.print(sql1.toString());
		PreparedStatement pstmt = con.prepareStatement(sql1.toString());
		
		return pstmt.executeQuery();
	}
	public static int delete(Connection con, String Sno)throws Exception
	{
		String sql2 = "delete from student where Sno =?";
		PreparedStatement pstmt=con.prepareStatement(sql2);
		pstmt.setString(1, Sno);
		return pstmt.executeUpdate();
	}
	public static int update(Connection con, StudentManage sm)throws Exception
	{
		String sql3 = "update student set Sname = ?,Ssex = ?,Stel = ?,Sclass = ?,Sid = ?,Sage = ?,Smajor = ?,Snative = ? where Sno = ?";
		PreparedStatement pstmt=con.prepareStatement(sql3);
		pstmt.setString(1, sm.getSname());
		pstmt.setString(2, sm.getSsex());
		pstmt.setString(3, sm.getStel());
		pstmt.setString(4, sm.getSclass());
		pstmt.setString(5, sm.getSid());
		pstmt.setString(6, sm.getSage());
		pstmt.setString(7, sm.getSmajor());
		pstmt.setString(8, sm.getSnative());
		pstmt.setString(9, sm.getSno());
		return pstmt.executeUpdate();
	}
}
