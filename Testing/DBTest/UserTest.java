package DBTest;

import org.junit.Test;

import backend.User;
import database.UserController;

public class UserTest {

	@Test
	private void printAllUsers() {
		UserController uc = new UserController();

		User tmp = uc.getUserByID(1);

		System.out.println(tmp.getLogin());
		System.out.println(tmp.getName());
		System.out.println(tmp.getImageURL());

	}

	@Test
	public void zmain() {
		// printAllUsers();
	}
}
