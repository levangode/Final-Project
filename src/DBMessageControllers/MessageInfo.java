package DBMessageControllers;

public class MessageInfo {
	int sender_id;
	int recipient_id;

	String message_text;
	String message_subject;
	
	int message_id;
	
	public MessageInfo(int sender_id, int recipient_id, String text, String subject, int id) {
		
	}
	
	public int getSenderID(){
		return sender_id;
	}
	
	public int getRecipientID(){
		return recipient_id;
	}
	
	public String getText(){
		return message_text;
	}
	
	public String getSubject(){
		return message_subject;
	}
	
}
