package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	private Connection connection;

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String server = INFO.MYSQL_DATABASE_SERVER;
		String userName = INFO.MYSQL_USERNAME;
		String password = INFO.MYSQL_PASSWORD;
		String dbName = INFO.MYSQL_DATABASE_NAME;
		try {
			connection = DriverManager.getConnection(server + "/" + dbName, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean containsUser(String user_login){
		String order = "select count(*) from Users " + "where user_login = " + "'" + user_login + "'";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet myRes = null;
		int count = 0;
		try {
			myRes = stm.executeQuery();
			myRes.next();
			count = myRes.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count == 0)
			return false;
		return true;
	}
	
	public void addNewUser(String user_login, String user_password, String user_name){
		String order = ""
				+ "insert into Users(user_login, user_password, user_name) values "
				+ "	('"+user_login+"', '"+user_password+"', '"+user_name+"');";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean passwordMatch(String user_login, String user_password){
		boolean result = false;
		
		return result;
	}
}
