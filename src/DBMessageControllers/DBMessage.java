package DBMessageControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import answers.Answer;
import backend.Message;
import database.DBconnector;
import database.UserController;
import questions.FillTheBlankQuestion;
import questions.QuestionTypes;

public class DBMessage {
	
	Connection connection = null;
	
	public DBMessage(){
		
	}
	
	public void sendMessage(Message m){
		
		connection = new DBconnector().getConnection();
				
		int sender_id = getSenderID(m);
		int recipient_id = getRecipientID(m);
		
		String query = "insert into Messages(sender_id, recipient_id, message_text, message_subject) value("
				+ sender_id + ", "
				+ recipient_id + ", "
				+ "'" + m.getText() + "', "
				+ "'" + m.getSubject() + "' "
				+");";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query);
			stm.executeUpdate();
						
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
		
		
	}

	private int getRecipientID(Message m) {
		String recipient_login = m.getRecipient().getLogin();
		
		UserController u = new UserController();
		
		int recipient_id = u.getUserIDByLogin(recipient_login);
		
		return recipient_id;
	}

	private int getSenderID(Message m) {
		String sender_login = m.getSender().getLogin();
		
		UserController u = new UserController();
		
		int senderID = u.getUserIDByLogin(sender_login);
		
		return senderID;
	}
	
	public List<Message> getMessages(int semder_id){
		List<Message> messages = new ArrayList<Message>(); 
		
		String query = "select sender_id, recipient_id, message_text, message_subject from Messages where 1;";
		
		connection = new DBconnector().getConnection();
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return messages;
	}
}
