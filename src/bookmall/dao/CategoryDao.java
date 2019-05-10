package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public boolean insert(CategoryVo vo) {
		boolean result=false;
		try {			
			conn=getConnection();
			String sql="insert into category values(null,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			int count =pstmt.executeUpdate();
			result  =(count==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			allClose(conn, pstmt, rs);
		}
		return result;
	}
	
	public List<CategoryVo> getList(){
		List<CategoryVo> list=new ArrayList<CategoryVo>();
		try {
			conn=getConnection();
			String sql="select * from category order by category_no";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				CategoryVo vo=new CategoryVo(no,name);
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
