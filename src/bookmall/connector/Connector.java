package bookmall.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {
	public static Connection getConnection() throws SQLException {
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
	public static void allClose(Connection conn,PreparedStatement pstmt, ResultSet rs) {
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
