package ott.model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class OttUtil {
	
	static Properties p = new Properties();
	
	static {
		try {
			p.load(new FileInputStream("db.properties"));
			Class.forName(p.getProperty("driver"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection(p.getProperty("url"), p.getProperty("id"), p.getProperty("pw"));
		
	}
	
	/** 자원반환 (DQL) **/
		public static void close(Connection conn, Statement stmt, ResultSet rset) {
			try {
				if(rset != null) {
					rset.close();
					rset = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (SQLException e) {  
				e.printStackTrace();
			}
		}
		
		/** 자원반환 (DML) **/
		public static void close(Connection conn, Statement stmt) {
			try {			
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (SQLException e) {  
				e.printStackTrace();
			}
		}
}

	
