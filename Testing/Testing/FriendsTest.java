package Testing;

import java.util.List;

import org.junit.Test;

import database.DBFriendController;
import database.UserController;

public class FriendsTest {
	private void addFakeUsers(){
		UserController DBU = new UserController();
		DBU.addNewUser("gocha", "123", "gocha");
	}
	
	
	private void getFriendsList(String userLogin){

		DBFriendController dbf = new DBFriendController();
		
		List<String> z = dbf.getFriendsUserNamesList(1);
		System.out.println(z.get(0));
	}
	
	@Test
	public void testFriends(){
		addFakeUsers();
		getFriendsList("gocha");
	}
	
	
}
