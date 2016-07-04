package DBMessageControllers;

public class SendMessageInfo {
	String sender_login;
	String recipient_login;
	
	String text;
	String subject;
	
	public SendMessageInfo(	String sender_login, String recipient_login, String text, String subject){
		this.sender_login = sender_login;
		this.recipient_login = recipient_login;
		this.text = text;
		this.subject = subject;
	}
	
	public String getSnederLogin(){
		return sender_login;
	}
	
	public String getRecipientLogin(){
		return recipient_login;
	}
	
	public String getText(){
		return text;
	}
	
	public String getSubject(){
		return subject;
	}
	
	
}
