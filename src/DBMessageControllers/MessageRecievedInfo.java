package DBMessageControllers;

import java.sql.Timestamp;

public class MessageRecievedInfo {
	String userLogin;
	String userName;
	
	String messageText;
	String messageSubject;
	int messageID;
	
	boolean messageSeen;
	
	Timestamp dateSent;
	
	public MessageRecievedInfo(String userLogin, String userName, String messageText, String messageSubject, int messageID, boolean messageSeen, Timestamp dateSent){
		this.userLogin = userLogin;
		this.userName = userName;
		this.messageText = messageText;
		this.messageSubject = messageSubject;
		this.messageID = messageID;
		this.messageSeen = messageSeen;
		this.dateSent = dateSent;
	}
	
	public String getSenderLogin(){
		return userLogin;
	}
	
	public String getSenderName(){
		return userName;
	}
	
	public String getMessageText(){
		return messageText;
	}
	
	public String getMessageText(int charLimit){
		if(messageText.length() > charLimit){
			return messageText.substring(0, charLimit);
		}
		
		return messageText;
	}
	
	public String getMessageSubject(){
		return messageSubject;
	}

	public String getMessageSubject(int charLimit){
		if (messageSubject.length() > charLimit){
			return messageSubject.substring(0, charLimit);
		}
		return messageSubject;
	}
	
	public int getMessageID(){
		return messageID;
	}
	
	public Timestamp getDatesent(){
		return dateSent;
	}
	
	public boolean getMessageseen(){
		return messageSeen;
	}
	
	@Override
	public String toString(){
		return getSenderLogin() + getMessageText() + messageID + dateSent; 
	}
	
	private final int PREVIEW_CHAR_LIMIT_TEXT = 100;
	private final int PREVIEW_CHAR_LIMIT_SUBJECT = 50;
	
	public String getPreviewHTML(int id){
		StringBuilder scr = new StringBuilder("");
		
		scr.append("<div>");
			scr.append("<p>");
				scr.append("" + this.getSenderLogin());
				scr.append("</br>");
				scr.append("" + this.getMessageSubject(PREVIEW_CHAR_LIMIT_SUBJECT));
				scr.append("</br>");
				scr.append("" + this.getMessageText(PREVIEW_CHAR_LIMIT_TEXT));
			scr.append("</p>");
		scr.append("</div>");
		
		return scr.toString();
	}
}
