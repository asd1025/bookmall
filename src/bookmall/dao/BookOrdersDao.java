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

public class BookOrdersDao {
	// insert order (Ordervo)
	// insert orderBook (List<OrdersBook>)
	// 오더를 보여주고 눌렀을떄 List getOrderList(Long MemeberNo)
	// List getOrderBookList (Long orderNo) --> service에서 잘 조합하도록
	/// 여기는 CRUD만
	// select last_insert_id(); -> 방금 넣은 것의 pk 를 찾아줌

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 주문 번호를 통해 주문된 책들을 확인하는 리스트
	 **/
	public List<BookOrdersVo> getOrderBookList(Long orderNo) {

		List<BookOrdersVo> list = new ArrayList<BookOrdersVo>();
		try {
			conn = getConnection();
			String sql = "select o.code,b.title,bo.count,(b.price*bo.count),c.name from book_orders bo, book b, category c , orders o"
					+ "		where bo.book_no=b.book_no " + " and c.category_no=b.category_no and o.orders_no=bo.orders_no "
					+ "		 and bo.orders_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String ordersBookCode = rs.getString(1);
				String bookName = rs.getString(2);
				int count = rs.getInt(3);
				int price = rs.getInt(4);
				String categoryName = rs.getString(5);
				BookOrdersVo vo = new BookOrdersVo();
				vo.setOrdersBookCode(ordersBookCode);
				vo.setBookName(bookName);
				vo.setCount(count);
				vo.setPrice(price);
				vo.setCategoryName(categoryName);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			allClose(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 각각의 책 주문
	 */
	public boolean insert(BookOrdersVo vo) {
		boolean result = false;
		try {
			conn = getConnection();
			String sql = " insert into book_orders values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getBookNo());
			pstmt.setLong(2, vo.getOrdersNo());
			pstmt.setInt(3, vo.getCount());
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			allClose(conn, pstmt, rs);
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mariadb://192.168.1.145:3307/bookmall";
		conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		return conn;
	}

	private void allClose(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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
