package com.fatalism.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
	
	public static Connection getConnect() throws Exception{
		Connection con = null;
		String user="fatalism";
		String password="123";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver="oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
	public static void disConnect(Connection con, PreparedStatement st){
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void disConnect(Connection con, PreparedStatement st, ResultSet rs){
		try {
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
