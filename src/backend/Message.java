package backend;

public class Message {
	private String text;
	private String subject;
	
	private User sender;
	private User recipient;
	
	Message(String text, String subject, User sender, User recipient){
		this.text = text;
		this.subject = subject;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public String getText(){
		return text;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public User getSender(){
		return sender;
	}
	
	public User getRecipient(){
		return recipient;
	}
	
	public String getSenderName(){
		return sender.getName();
	}

	public String getRecipientName(){
		return recipient.getName();
	}
	
	


}
