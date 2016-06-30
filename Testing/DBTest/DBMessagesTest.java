package DBTest;

import org.junit.Test;

import DBMessageControllers.DBMessage;
import backend.Message;
import backend.User;

public class DBMessagesTest {

	
	@Test
	public void DBMessageAddTest(){
		
		DBMessage dbm = new DBMessage();
		
		User sender = new User("goscha", 0, "mike");
		
		User recipient = new User("", 0, "z" );
		
		Message m = new Message("traki", "adasdasdfsdfsd", sender, recipient);
		
		dbm.sendMessage(m);
		
		//System.out.println(m);
		
	}
	
	
}
