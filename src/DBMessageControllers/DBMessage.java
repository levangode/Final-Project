package DBMessageControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

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
	
	public int getNumUnread(String login){
		
		int numUnread = 0;
		
		connection = new DBconnector().getConnection();
		
		UserController dbu = new UserController();
		
		int user_id = dbu.getUserIDByLogin(login);
		
		String query = "select count(*) from messages where recipient_id = "
				+ user_id
				+ " and " 
				+ " message_seen = 0;";
		
		PreparedStatement stm;
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				numUnread = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(query);
		
		return numUnread;
	}
	
	public boolean sendMessage(SendMessageInfo mes){
		connection = new DBconnector().getConnection();
				
		int sender_id = getSenderID(mes.getSnederLogin());
		int recipient_id = getRecipientID(mes.getRecipientLogin());
		
		String query = "insert into Messages(sender_id, recipient_id, message_text, message_subject) value("
				+ sender_id + ", "
				+ recipient_id + ", "
				+ "'" + mes.getText() + "', "
				+ "'" + mes.getSubject() + "' "
				+");";
		
		PreparedStatement stm;
		
		try {
			stm = connection.prepareStatement(query);
			
			stm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
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
						
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private int getRecipientID(Message m) {
		String recipient_login = m.getRecipient().getLogin();
		
		UserController u = new UserController();
		
		int recipient_id = u.getUserIDByLogin(recipient_login);
		
		return recipient_id;
	}
	
	private int getRecipientID(String recipient_login) {
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
	
	private int getSenderID(String sender_login) {
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return messages;
	}
	
	public List<Message> getMessagesRecieved(User recipient){
		UserController usr = new UserController(); 
		
		int recipient_id = -1;
		
		recipient_id = usr.getUserIDByLogin(recipient.getLogin());
		
		return getMessagesSent(recipient_id);
	}
	
	public Message getMessageByID(int message_id){
		
		Message message = null;
		
		String query = "select sender_id, recipient_id, message_text, message_subject from Messages where "
				+ "message_id = " + message_id
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
				
				message = m;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return message;
	}
	
	public MessageInfo getMessageInfoByID(int message_id){
		
		MessageInfo message = null;
		
		String query = "select sender_id, recipient_id, message_text, message_subject, message_id from Messages where "
				+ "message_id = " + message_id
				+ ";";
		
		connection = new DBconnector().getConnection();
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				int sender_id = rs.getInt(1);
				int recipient_id = rs.getInt(2);
				
				String message_text = rs.getString(3);
				String message_subject = rs.getString(4);
				
				int tmp_message_id = rs.getInt(5);
				
				MessageInfo m = new MessageInfo(sender_id, recipient_id, message_text, message_subject, tmp_message_id);
				
				message = m;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return message;
	}
	
	public MessageInfo getRecievedMessageInfoByID(int recipient_id){
		
		MessageInfo message = null;
		
		String query = "select sender_id, recipient_id, message_text, message_subject, message_id from Messages where "
				+ "recipient_id = " + recipient_id
				+ ";";
		
		connection = new DBconnector().getConnection();
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				int sender_id = rs.getInt(1);
				//int recipient_id1 = rs.getInt(2);
				
				String message_text = rs.getString(3);
				String message_subject = rs.getString(4);
				
				int tmp_message_id = rs.getInt(5);
				
				MessageInfo m = new MessageInfo(sender_id, recipient_id, message_text, message_subject, tmp_message_id);
				
				message = m;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		return message;
	}
	
	public List<MessageRecievedInfo> getRecievedMessagesInfo(int recipient_id){
		connection = new DBconnector().getConnection();
		
		List<MessageRecievedInfo> messageInfo = new ArrayList<MessageRecievedInfo>();
		
		String query = "select m.message_id, m.`message_text`, m.message_subject, m.message_seen, m.date_sent, send.user_login, send.user_name "
				+ "from messages m, users send "
				+ "where m.recipient_id = " + recipient_id
				+ " and m.sender_id = send.user_id";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				int messageID = rs.getInt("message_id");
				String messageText = rs.getString("message_text");
				String messageSubject = rs.getString("message_subject");
				boolean messageSeen = rs.getBoolean("message_seen");
				Timestamp dateSent = rs.getTimestamp("date_sent");
				String senderName = rs.getString("user_name");
				String senderLogin = rs.getString("user_login");
				
				messageInfo.add(new MessageRecievedInfo(senderLogin, senderName, messageText, messageSubject, messageID, messageSeen, dateSent));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return messageInfo;
	}

	
	public List<MessageSentInfo> getSentMessagesInfo(int recipient_id){
		connection = new DBconnector().getConnection();
		
		List<MessageSentInfo> messageInfo = new ArrayList<MessageSentInfo>();
		
		String query = "select m.message_id, m.`message_text`, m.message_subject, m.message_seen, m.date_sent, send.user_login, send.user_name "
					+ " from messages m, users send where "
					+ " m.sender_id = " + recipient_id
					+ " and m.recipient_id = send.user_id; ";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try {
			stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				int messageID = rs.getInt("message_id");
				String messageText = rs.getString("message_text");
				String messageSubject = rs.getString("message_subject");
				boolean messageSeen = rs.getBoolean("message_seen");
				Timestamp dateSent = rs.getTimestamp("date_sent");
				String senderName = rs.getString("user_name");
				String senderLogin = rs.getString("user_login");
				
				messageInfo.add(new MessageSentInfo(senderLogin, senderName, messageText, messageSubject, messageID, messageSeen, dateSent));
				
			}
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messageInfo;
	}
	
	public boolean setMessageSeen(int message_id){
		connection = new DBconnector().getConnection();
		
		String query = "update messages set message_seen = 1 where message_id = "
				+ message_id
				+ ";";
		
		PreparedStatement stm;
		
		try {
			stm = connection.prepareStatement(query);
			
			stm.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
