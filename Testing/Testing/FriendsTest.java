package Testing;

import java.util.List;

import database.DBFriendController;
import database.UserController;

public class FriendsTest {
	public void addFakeUsers(){
		UserController DBU = new UserController();
		DBU.addNewUser("gocha", "123", "gocha");
	}
	
	public void getFriendsList(String userLogin){

		DBFriendController dbf = new DBFriendController();
		
		List<String> z = dbf.getFriendsUserNamesList(1);
		System.out.println(z.get(0));
	}
	
	
}
