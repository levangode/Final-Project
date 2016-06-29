package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.User;

public class UserController {
	private Connection connection;
	private DBconnector db;

	public UserController() {

	}

	public User getUserByID(int ID) {
		db = new DBconnector();
		connection = db.getConnection();
		String order = "select user_name, user_id, user_login, user_profile_image from Users " + "where user_id = " + ID
				+ ";";
		PreparedStatement stm = null;
		ResultSet myRes = null;
		User thisUser = null;

		try {
			stm = connection.prepareStatement(order);
			myRes = stm.executeQuery();

			if (myRes.next()) {
				String user_name = myRes.getString(1);
				int user_id = myRes.getInt(2);
				String user_login = myRes.getString(3);
				String user_profile_image = myRes.getString(4);
				if (user_profile_image == null) {
					user_profile_image = User.DEFAULT_IMAGE;
				}

				thisUser = new User(user_name, user_login, user_profile_image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();

		return thisUser;
	}

	public User getUserByLogin(String login) {
		db = new DBconnector();
		connection = db.getConnection();
		String order = "select user_name, user_id, user_login, user_profile_image from Users " + "where user_login = '"
				+ login + "';";
		PreparedStatement stm = null;
		ResultSet myRes = null;
		User thisUser = null;

		try {
			stm = connection.prepareStatement(order);
			myRes = stm.executeQuery();

			if (myRes.next()) {
				String user_name = myRes.getString(1);
				int user_id = myRes.getInt(2);
				String user_login = myRes.getString(3);
				String user_profile_image = myRes.getString(4);
				if (user_profile_image == null) {
					user_profile_image = User.DEFAULT_IMAGE;
				}

				thisUser = new User(user_name, user_login, user_profile_image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();

		return thisUser;
	}

	public void editImgUrl(String login, String newUrl) {
		db = new DBconnector();
		connection = db.getConnection();
		String order = "Update Users Set user_profile_image = '" + newUrl + "' where user_login ='" + login + "'";
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(order);
			System.out.println(stm);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();

	}

	public void editUserName(String login, String newUserName) {
		db = new DBconnector();
		connection = db.getConnection();
		String order = "Update Users Set user_name = '" + newUserName + "' where user_login ='" + login + "'";
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(order);
			System.out.println(stm);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
	}

	public boolean containsUser(String user_login) {
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

	public void addNewUser(String user_login, String user_password, String user_name) {
		db = new DBconnector();
		connection = db.getConnection();
		String order = "" + "insert into Users(user_login, user_password, user_name) values " + "	('" + user_login
				+ "', '" + user_password + "', '" + user_name + "');";
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

	public boolean passwordMatch(String user_login, String user_password) {
		boolean result = false;
		db = new DBconnector();
		connection = db.getConnection();
		String order = "" + "select user_password from Users where user_login = '" + user_login + "'";
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
			if (user_password.equals(myRes.getString(1)))
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return result;
	}
}
