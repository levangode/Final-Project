package Testing;

import backend.User;
import database.UserController;

public class UserTest {
	private  void printAllUsers(){
		UserController uc = new UserController();
		
		User tmp = uc.getUserByID(1);
		
		System.out.println(tmp.getId());
		System.out.println(tmp.getLogin());
		System.out.println(tmp.getName());
		System.out.println(tmp.getImageURL());
		
		
	}
	
	public  void zmain(String[] args){
		printAllUsers();
	}
}
