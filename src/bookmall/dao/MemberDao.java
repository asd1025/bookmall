package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;

public class MemberDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public boolean insert(MemberVo vo) {
		boolean result=false;
		try {
			conn=getConnection();
			String sql="insert into member values (null,?,?,?,password(?))";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPwd());
			int count =pstmt.executeUpdate();
			result  =(count==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			allClose(conn, pstmt, rs);
		}
		return result;
	}
	
	public List<MemberVo> getList(){
		List<MemberVo> list=new ArrayList<MemberVo>();
		try {
			conn=getConnection();
			String sql="select name, tel, email from member";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String name=rs.getString(1);
				String tel=rs.getString(2);
				String email=rs.getString(3);
				MemberVo vo=new MemberVo();
				vo.setName(name);
				vo.setTel(tel);
				vo.setEmail(email);
				list.add(vo);
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			allClose(conn, pstmt, rs);
		}
		return list;
	}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:mariadb://192.168.1.145:3307/bookmall";
		conn=DriverManager.getConnection(url,"bookmall","bookmall");
		return conn;
	}
	private void allClose(Connection conn,PreparedStatement pstmt, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
