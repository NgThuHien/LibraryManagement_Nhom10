package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryManagement";
		String user = "sa";
		String password = "ngthu101h";
		
		// Đăng ký thông tin
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	public static void closeConnection(Connection conn) {
//				try {
//					if(conn != null)
//						conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//	}
//	public static void main(String[] args) {
//		
//		Connection connection = JDBCUtil.getConnection();	
//		System.out.println(connection);
//		JDBCUtil.closeConnection(connection);
//	}
}
