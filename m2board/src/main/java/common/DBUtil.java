package common;

import java.sql.*;

public class DBUtil{
	private DBUtil() {};
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3306/board";
		
		return DriverManager.getConnection(url, "root", "1234");
	}
}