package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnector.Connector;

public class DBFriendController {

	private Connection con;

	public DBFriendController() {
		con = new DBconnector().getConnection();
	}

	public List<Integer> getFriendsIDList(int ID) {
		List<Integer> friends = new ArrayList<Integer>();

		String query = "select `to` from Friends where `from` = " + ID + ";";

		PreparedStatement stm;

		try {
			stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Integer friendID = rs.getInt(1);
				friends.add(friendID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.returnConnection(con);
		}
		return friends;
	}

	public List<String> getFriendsUserNamesList(int ID) {
		List<String> friends = new ArrayList<String>();

		String query = "select user_login from Users, Friends where `from` = " + ID + " and `from` = user_id";

		PreparedStatement stm;

		try {
			stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				String friendUserName = rs.getString(1);
				friends.add(friendUserName);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.returnConnection(con);
		}

		return friends;
	}

	public boolean addFriendshpByUserID(int user1ID, int user2ID) {
		if (user1ID == user2ID) {
			return false;
		}

		String query1 = "insert into Friends(`from`, `to`) values (" + user1ID + ", " + user2ID + " );";
		String query2 = "insert into Friends(`from`, `to`) values (" + user2ID + ", " + user1ID + " );";

		PreparedStatement stm;

		try {
			stm = con.prepareStatement(query1);
			stm.executeUpdate();

			stm = con.prepareStatement(query2);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return true;
	}

	public boolean addFriendshpRequestByUserID(int user1ID, int user2ID) {
		if (user1ID == user2ID) {
			return false;
		}

		String query = "insert into FriendshipRequests(`from`, `to`) values (?,?);";
		PreparedStatement stm;

		try {
			stm = con.prepareStatement(query);
			stm.setInt(1, user1ID);
			stm.setInt(2, user2ID);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}
		return true;
	}

	public ArrayList<Integer> getFriendRequests(int id) {
		ArrayList<Integer> list = new ArrayList<>();
		String query = "Select `from` from FriendshipRequests where `to` =" + id;
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				int requestId = res.getInt(1);
				System.out.println("integer: " + requestId);
				list.add(requestId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			Connector.returnConnection(con);
		}
		return list;
	}

	public boolean addFriendshpRequestByUserLogin(int user1Login, int user2Login) {
		if (user1Login == user2Login) {
			return false;
		}

		String query1 = "select user_id from Users where user_login = " + user1Login + ";";
		String query2 = "select user_id from Users where user_login = " + user2Login + ";";

		java.sql.PreparedStatement stm;

		int user1ID;
		int user2ID;

		try {
			stm = con.prepareStatement(query1);
			ResultSet rs1 = stm.executeQuery();

			stm = con.prepareStatement(query2);
			ResultSet rs2 = stm.executeQuery();

			user1ID = rs1.getInt(1);
			user2ID = rs2.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return addFriendshpByUserID(user1ID, user2ID);
		// @SuppressWarnings
		// return true;
	}

	public boolean addFriendByUserLogin(int user1Login, int user2Login) {
		if (user1Login == user2Login) {
			return false;
		}

		String query1 = "select user_id from Users where user_login = " + user1Login + ";";
		String query2 = "select user_id from Users where user_login = " + user2Login + ";";

		java.sql.PreparedStatement stm;

		int user1ID;
		int user2ID;

		try {
			stm = con.prepareStatement(query1);
			ResultSet rs1 = stm.executeQuery();

			stm = con.prepareStatement(query2);
			ResultSet rs2 = stm.executeQuery();

			user1ID = rs1.getInt(1);
			user2ID = rs2.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return addFriendshpByUserID(user1ID, user2ID);
		// @SuppressWarnings
		// return true;
	}

	public boolean cancelFriendshipByID(int user1ID, int user2ID) {
		if (user1ID == user2ID) {
			return false;
		}

		String query1 = "delete from Friends where `from` = " + user1ID + " and " + "`to` = " + user2ID + ";";

		String query2 = "delete from Friends where `from` = " + user2ID + " and " + "`to` = " + user1ID + ";";

		java.sql.PreparedStatement stm;

		try {
			stm = con.prepareStatement(query1);
			stm.executeUpdate();

			stm = con.prepareStatement(query2);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return true;
	}

	public boolean isRequestSent(int user1, int user2) {
		if (user1 == user2) {
			return false;
		}

		String query1 = "SELECT * FROM FriendshipRequests WHERE `from` =" + user1 + " and " + "`to` = " + user2 + ";";

		java.sql.PreparedStatement stm;
		try {
			stm = con.prepareStatement(query1);
			ResultSet res1 = stm.executeQuery();

			;

			if (res1.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return false;
	}

	public boolean isFriend(int user1ID, int user2ID) {
		if (user1ID == user2ID) {
			return false;
		}

		String query1 = "SELECT * FROM Friends WHERE `from` =" + user1ID + " and " + "`to` = " + user2ID + ";";
		String query2 = "SELECT * FROM Friends WHERE `from` =" + user2ID + " and " + "`to` = " + user1ID + ";";

		java.sql.PreparedStatement stm;
		System.out.println("isFriendsdadas?");
		try {
			stm = con.prepareStatement(query1);
			ResultSet res1 = stm.executeQuery();

			stm = con.prepareStatement(query2);
			ResultSet res2 = stm.executeQuery();

			if (res1.next()) {
				if (res2.next()) {
					return true;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return false;

	}

	public boolean cancelFriendshipRequestByID(int user1ID, int user2ID) {
		if (user1ID == user2ID) {
			return false;
		}

		String query1 = "delete from FriendshipRequests where `from` = " + user1ID + " and " + "`to` = " + user2ID
				+ ";";

		String query2 = "delete from FriendshipRequests where `from` = " + user2ID + " and " + "`to` = " + user1ID
				+ ";";

		java.sql.PreparedStatement stm;

		try {
			stm = con.prepareStatement(query1);
			stm.executeUpdate();

			stm = con.prepareStatement(query2);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.returnConnection(con);
		}

		return true;

	}

}
