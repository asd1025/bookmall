package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookOrdersVo;
import bookmall.vo.OrdersVo;

public class OrdersDao {
 //insert order (Ordervo)
	// insert orderBook (List<OrdersBook>)
	// 오더를 보여주고 눌렀을떄 List  getOrderList(Long MemeberNo)
	// List getOrderBookList (Long orderNo)  --> service에서 잘 조합하도록
	/// 여기는 CRUD만
	// select last_insert_id(); -> 방금 넣은 것의 pk 를 찾아줌
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public boolean insertOrder(List<BookOrdersVo> list ) {
		
		
		return false;
	}
	/**	 주문 넣기
	 * */
	public boolean insert(OrdersVo vo) {
		boolean result=false;
		try {
			conn=getConnection();
			String sql=" insert into orders values "
					+ "(null,?,?,now(),null,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPirce());
			pstmt.setString(2, vo.getAddress());
			pstmt.setLong(3, vo.getMemberNo());
			int count =pstmt.executeUpdate();
			result  =(count==1);
			if(result) {
				sql="select  LAST_INSERT_ID()";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				rs.next();
				Long ordersNo=rs.getLong(1);
				update(ordersNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			allClose(conn, pstmt, rs);
		}
		return result;
	}
	/**
	 *  주문시 일시+orders_no -> Code 로 변환해주기 위한 update
	 * */
	public boolean update(Long ordersNo) {
		boolean result=false;
		try {
			conn=getConnection();
			String sql="update orders o set code =(\r\n" + 
					"select concat ( (select date_format(o.date,'%Y%m%d')), '',(select lpad(?,4,0)) ) " + 
					") where orders_no=? " ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, ordersNo);
			pstmt.setLong(2, ordersNo);
			pstmt.executeUpdate();
			int count =pstmt.executeUpdate();
			result  =(count==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			allClose(conn, pstmt, rs);
		}
		return result;
	}
	/**
	 *  회원번호를 통해, 모든 주문 리스트 얻기
	 * */
	public List<OrdersVo> getOrderList(Long MemeberNo){
		List<OrdersVo> list=new ArrayList<OrdersVo>();
		try {
			conn=getConnection();
			String sql="select o.code,m.name,o.price,o.address,o.date from "
					+ "orders o , member m where o.member_no=m.member_no and  o.member_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, MemeberNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String code=rs.getString(1);
				String name=rs.getString(2);
				int price=rs.getInt(3);
				String address=rs.getString(4);
				String ordersDate=rs.getString(5);
				OrdersVo vo=new OrdersVo();
				vo.setCode(code);
				vo.setName(name);
				vo.setPirce(price);
				vo.setAddress(address);
				vo.setOrdersDate(ordersDate);
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
