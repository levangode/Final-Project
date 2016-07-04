package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.User;

public class UserController {
	private Connection connection;

	public UserController() {
		connection = new DBconnector().getConnection();
	}

	public User getUserByID(int ID) {
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
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return thisUser;
	}

	public User getUserByLogin(String login) {
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
				// int user_id = myRes.getInt(2);
				String user_login = myRes.getString(3);
				String user_profile_image = myRes.getString(4);
				if (user_profile_image == null) {
					user_profile_image = User.DEFAULT_IMAGE;
				}

				thisUser = new User(user_name, user_login, user_profile_image);
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return thisUser;
	}

	public List<User> getUserList(String fragment) {

		List<User> users = new ArrayList<User>();

		String query = "select user_name, user_id, user_login, user_profile_image from Users "
				+ "where user_login like '%" + fragment + "%' or user_name like '%" + fragment + "%';";

		PreparedStatement stm;

		System.out.println(query);

		try {
			stm = connection.prepareStatement(query);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				User tmp = new User(rs.getString(1), rs.getString(3), rs.getString(4));
				users.add(tmp);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public void editImgUrl(String login, String newUrl) {
		String order = "Update Users Set user_profile_image = '" + newUrl + "' where user_login ='" + login + "'";
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(order);
			stm.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editUserName(String login, String newUserName) {
		String order = "Update Users Set user_name = '" + newUserName + "' where user_login ='" + login + "'";
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(order);
			System.out.println(stm);
			stm.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean containsUser(String user_login) {
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
		} finally {
			
		}
		if (count == 0)
			return false;
		return true;
	}

	public void addNewUser(String user_login, String user_password, String user_name) {
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
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean passwordMatch(String user_login, String user_password) {
		boolean result = false;
		String order = "" + "select user_password from Users where user_login = '" + user_login + "'";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ait");
		ResultSet myRes = null;
		try {
			myRes = stm.executeQuery();
			myRes.next();
			if (user_password.equals(myRes.getString(1)))
				result = true;
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getUserIDByLogin(String login) {

		int user_id = -1;


		String query = "select user_id from Users where user_login = " + "'" + login + "'" + ";";

		PreparedStatement stm = null;


		try {
			stm = connection.prepareStatement(query);
			System.out.println("ait");
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				user_id = rs.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user_id;
	}

	public int getUserQuizCount(int user_id) {
		int result = 0;

		String order = "select count(*) from Quizzes where author_id = "+user_id;
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(order);
			ResultSet rs = stm.executeQuery(order);

			while (rs.next()) {
				result = rs.getInt(1);
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
