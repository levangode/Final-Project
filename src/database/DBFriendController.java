package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBFriendController {
	
	private java.sql.Connection con;
	
	DBFriendController(){
		con = new DBconnector().getConnection();
	}
	
	public List<Integer> getFriendsIDList(int ID){
		List<Integer> friends = new ArrayList<Integer>();
		
		String query = "select `to` from Firends where `from` = " + ID + ";";
		
		java.sql.PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				Integer friendID = rs.getInt(1);
				friends.add(friendID);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}
		
		
		return friends;
	}
	
	public List<String> getFriendsUserNamesList(int ID){
		List<String> friends = new ArrayList<String>();
		
		String query = "select user_login from Users, Friends where `from` = " + ID + " and `from` = user_id";
		
		java.sql.PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				String friendUserName = rs.getString(1);
				friends.add(friendUserName);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}

		return friends;
	}
	
	public boolean addFriendshpByUserID(int user1ID, int user2ID){
		if(user1ID == user2ID){
			return false;
		}
		
		String query1 = "insert into Friends(`from`, `to`) values (" + user1ID + ", " + user2ID +" );";
		String query2 = "insert into Friends(`from`, `to`) values (" + user2ID + ", " + user1ID +" );";
		
		java.sql.PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query1);
			stm.executeQuery();

			stm = con.prepareStatement(query2);
			stm.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			
		}
		
		return true;
	}
	
	public boolean addFriendshpRequestByUserID(int user1ID, int user2ID){
		if(user1ID == user2ID){
			return false;
		}
		
		String query = "insert into FriendshipRequests(`from`, `to`) values (user1ID, user2ID);";
		java.sql.PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query);
			stm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			
		}
		
		return true;
	}
	
	public boolean addFriendshpRequestByUserLogin(int user1Login, int user2Login){
		if(user1Login == user2Login){
			return false;
		}
		
		String query1 = "select user_id from Users where user_login = " + user1Login + ";";
		String query2 = "select user_id from Users where user_login = " + user2Login + ";";

		java.sql.PreparedStatement stm;
		
		int user1ID;
		int user2ID;
		
		try{
			stm = con.prepareStatement(query1);
			ResultSet rs1 = stm.executeQuery();
			
			stm = con.prepareStatement(query2);
			ResultSet rs2= stm.executeQuery();
			
			user1ID = rs1.getInt(1);
			user2ID = rs2.getInt(1);
						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			
		}
		
		return addFriendshpByUserID(user1ID, user2ID);
		//@SuppressWarnings
		//return true;
	}
	

	public boolean addFriendByUserLogin(int user1Login, int user2Login){
		if(user1Login == user2Login){
			return false;
		}
		
		String query1 = "select user_id from Users where user_login = " + user1Login + ";";
		String query2 = "select user_id from Users where user_login = " + user2Login + ";";

		java.sql.PreparedStatement stm;
		
		int user1ID;
		int user2ID;
		
		try{
			stm = con.prepareStatement(query1);
			ResultSet rs1 = stm.executeQuery();
			
			stm = con.prepareStatement(query2);
			ResultSet rs2= stm.executeQuery();
			
			user1ID = rs1.getInt(1);
			user2ID = rs2.getInt(1);
						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			
		}
		
		return addFriendshpByUserID(user1ID, user2ID);
		//@SuppressWarnings
		//return true;
	}
	
	public boolean cancelFriendshipByID(int user1ID, int user2ID){
		if(user1ID == user2ID){
			return false;
		}
		
		String query1 = "delete from Friends where `from` = " + user1ID + " and " + "`to` = " + user2ID + ";";
		
		String query2 = "delete from Friends where `from` = " + user2ID + " and " + "`to` = " + user1ID + ";";
		
		
		java.sql.PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query1);
			stm.executeQuery();

			stm = con.prepareStatement(query2);
			stm.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			
		}
		
		return true;

	}
	
	public boolean cancelFriendshipRequestByID(int user1ID, int user2ID){
		if(user1ID == user2ID){
			return false;
		}
		
		String query1 = "delete from FriendshipRequests where `from` = " + user1ID + " and " + "`to` = " + user2ID + ";";
		
		String query2 = "delete from FriendshipRequests where `from` = " + user2ID + " and " + "`to` = " + user1ID + ";";
		
		
		java.sql.PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query1);
			stm.executeQuery();

			stm = con.prepareStatement(query2);
			stm.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			
		}
		
		return true;

	}

	
}
