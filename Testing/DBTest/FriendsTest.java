package DBTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import DBConnector.Connector;
import database.DBFriendController;
import database.UserController;

public class FriendsTest {

	
	private void getFriendsList(String userLogin){

		Connection con = null;
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBFriendController dbf = new DBFriendController(con);
		
		Connector.returnConnection(con);
		
		List<String> z = dbf.getFriendsUserNamesList(1);
		System.out.println(z);
	}
	
	@Test
	public void testFriends(){
		getFriendsList("gocha");
	}
	
	
}
