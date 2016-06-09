package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
	private Connection connection;
	private DBconnector db;
	public UserController(){
		
	}
	public boolean containsUser(String user_login){
		db = new DBconnector();
		connection = db.getConnection();
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
		db.closeConnection();
		if (count == 0)
			return false;
		return true;
	}
	
	public void addNewUser(String user_login, String user_password, String user_name){
		db = new DBconnector();
		connection = db.getConnection();
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
		db.closeConnection();
	}
	
	public boolean passwordMatch(String user_login, String user_password){
		boolean result = false;
		db = new DBconnector();
		connection = db.getConnection();
		String order = ""
				+ "select user_password from Users where user_login = '"+user_login+"'";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet myRes = null;
		try {
			myRes = stm.executeQuery();
			myRes.next();
			if(user_password.equals(myRes.getString(1)))
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return result;
	}
}
