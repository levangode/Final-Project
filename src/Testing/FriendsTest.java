package Testing;

import java.util.List;

import database.DBFriendController;
import database.UserController;

public class FriendsTest {
	public static void addFakeUsers(){
		UserController DBU = new UserController();
		DBU.addNewUser("gocha", "123", "gocha");
	}
	
	public static void getFriendsList(String userLogin){

		DBFriendController dbf = new DBFriendController();
		
		List<String> z = dbf.getFriendsUserNamesList(1);
		System.out.println(z.get(0));
	}
	
	public static void main(String[] args){
	//	addFakeUsers();
		getFriendsList("asd");
		System.out.println("asdasda");
	}
}
