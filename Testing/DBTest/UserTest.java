package DBTest;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import DBConnector.Connector;
import backend.User;
import database.UserController;

public class UserTest {

//	@Test
	public void printAllUsers() {
		
		Connection con = null;
		
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		UserController uc = new UserController(con);

		User tmp = uc.getUserByID(1);

		System.out.println(tmp.getLogin());
		System.out.println(tmp.getName());
		System.out.println(tmp.getImageURL());

		Connector.returnConnection(con);
		
	}
	
	@Test
	public void getUserIDByLoginTest(){
		Connection con = null;
		
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		UserController c = new UserController(con);
		
		System.out.println( c.getUserIDByLogin("mike") );
				
		Connector.returnConnection(con);
		
	}

	
}
