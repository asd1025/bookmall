package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.connector.Connector;
import bookmall.vo.BookVo;

public class BookDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public boolean insert(BookVo vo) {
		boolean result=false;
		try {
			conn=Connector.getConnection();
			String sql="insert into book values (null,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			int count =pstmt.executeUpdate();
			result  =(count==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Connector.allClose(conn, pstmt, rs);
		}
		return result;
	}
	
	public List<BookVo> getList(){
		List<BookVo> list=new ArrayList<BookVo>();
		try {
			conn=Connector.getConnection();
			String sql="select b.title, b.price, c.name  from book b, category c where b.category_no=c.category_no";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String title=rs.getString(1);
				int price=rs.getInt(2);
				String categoryName=rs.getString(3);
				BookVo vo=new BookVo();
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategoryName(categoryName);
				list.add(vo);
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Connector.allClose(conn, pstmt, rs);
		}
		return list;
	}
	
	
	 
}
