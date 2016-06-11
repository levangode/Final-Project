package Testing;

import backend.User;
import database.UserController;

public class UserTest {
	private static void printAllUsers(){
		UserController uc = new UserController();
		
		User tmp = uc.getUserByID(1);
		
		System.out.println(tmp.getId());
		System.out.println(tmp.getLogin());
		System.out.println(tmp.getName());
		System.out.println(tmp.getImageURL());
		
		
	}
	
	public static void main(String[] args){
		printAllUsers();
	}
}
