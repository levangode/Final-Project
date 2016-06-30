package DBMessageControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import answers.Answer;
import backend.Message;
import backend.User;
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
	
	public List<Message> getMessagesSent(int sender_id){
		List<Message> messages = new ArrayList<Message>(); 
		
		String query = "select sender_id, recipient_id, message_text, message_subject from Messages where "
				+ "sender_id = " + sender_id
				+ ";";
		
		connection = new DBconnector().getConnection();
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				int sender_id1 = rs.getInt(1);
				int recipient_id = rs.getInt(2);
				String message_text = rs.getString(3);
				String message_subject = rs.getString(4);
				
				User sender = new UserController().getUserByID(sender_id1);
				User recipient = new UserController().getUserByID(recipient_id);
				
				Message m = new Message(message_text, message_subject, sender, recipient);
				
				messages.add(m);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return messages;
	}
	
	public List<Message> getMessagesSent(User sender){
		UserController usr = new UserController(); 
		
		int sender_id = -1;
		
		sender_id = usr.getUserIDByLogin(sender.getLogin());
		
		return getMessagesSent(sender_id);
	}
	
	public List<Message> getMessagesRecieved(int recipient_id){
		List<Message> messages = new ArrayList<Message>(); 
		
		String query = "select sender_id, recipient_id, message_text, message_subject from Messages where "
				+ "recipient_id = " + recipient_id
				+ ";";
		
		connection = new DBconnector().getConnection();
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				int sender_id1 = rs.getInt(1);
				int recipient_id1 = rs.getInt(2);
				String message_text = rs.getString(3);
				String message_subject = rs.getString(4);
				
				User sender = new UserController().getUserByID(sender_id1);
				User recipient = new UserController().getUserByID(recipient_id1);
				
				Message m = new Message(message_text, message_subject, sender, recipient);
				
				messages.add(m);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return messages;
	}
	
	public List<Message> getMessagesRecieved(User recipient){
		UserController usr = new UserController(); 
		
		int recipient_id = -1;
		
		recipient_id = usr.getUserIDByLogin(recipient.getLogin());
		
		return getMessagesSent(recipient_id);
	}
}
