package shop.repository;

import java.sql.*;

/**
 * 
 * @author zhangli
 *
 */
public class SysDataSource {
	
	private static SysDataSource dataSource;
	private Connection conn;
	
	private SysDataSource(){
		
	}
	public static SysDataSource getInstance(){
		if(dataSource == null){
			dataSource = new SysDataSource();
		}
		return dataSource;
	}
	public Connection getConnection() throws SQLException{
		if(conn == null || conn.isClosed())
		{
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				conn = DriverManager.getConnection("jdbc:mysql://localhost/shop","root","abc123");
		}
		return conn;
	}

}
