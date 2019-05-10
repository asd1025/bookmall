package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public boolean insert(CartVo vo) {
		boolean result=false;
		try {
			conn=getConnection();
			String sql="insert into cart values (?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getBookNo());
			pstmt.setLong(2, vo.getMemberNo());
			pstmt.setInt(3, vo.getCount());
			int count =pstmt.executeUpdate();
			result  =(count==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			allClose(conn, pstmt, rs);
		}
		return result;
	}
	
	public List<CartVo> getList(Long memberNo){
		List<CartVo> list=new ArrayList<CartVo>();
		try {
			conn=getConnection();
			String sql="select b.title, m.name, c.count from book b, member m, cart c " + 
					"where b.book_no=c.book_no and c.member_no=m.member_no " + 
					"and c.member_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String title=rs.getString(1);
				String name=rs.getString(2);
				int count=rs.getInt(3);
				CartVo vo=new CartVo();
				vo.setTitle(title);
				vo.setName(name);
				vo.setCount(count);
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
