package DBTest;

import java.util.List;

import org.junit.Test;

import DBMessageControllers.DBMessage;
import backend.Message;
import backend.User;

public class DBMessagesTest {

	
	@Test
	public void DBMessageAddTest(){
		
		DBMessage dbm = new DBMessage();
		
		User sender = new User("goscha", 0, "faskunji1");
		
		User recipient = new User("", 0, "mike" );
		
		Message m = new Message("mze", "adasdasdfsdfsd", sender, recipient);
		
		dbm.sendMessage(m);
		
		//System.out.println(m);
		
	}
	
	@Test
	public void DBMessageGetTest1(){
		
		System.out.println("Test1");
		
		DBMessage dbm = new DBMessage();
		
		List<Message> mess = dbm.getMessagesSent(2);
				
		for(Message m: mess){
			System.out.println(m);
		}
		
		
		System.out.println("\n\n\n");
	}
	
	@Test
	public void DBMessageGetTest2(){
		
		System.out.println("Test2");
		
		DBMessage dbm = new DBMessage();
		
		List<Message> mess = dbm.getMessagesSent(1);
		
		for(Message m: mess){
			System.out.println(m);
		}
				
		System.out.println("\n\n\n");
	}
	
	
}