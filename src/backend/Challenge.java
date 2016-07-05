package backend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

import ChallengeControllers.ChallengeController;
import DBQuizControllers.DBQuizController;
import quizInfoes.DrawableInfo;
import quizInfoes.UserActivity;

public class Challenge implements DrawableInfo{
	private User from_user;
	private User to_user;
	private int quiz_id;
	private ArrayList<UserActivity> sendersHighest;
	
	public Challenge(User sender, User receiver, int quiz_id, ArrayList<UserActivity> sendersHighest){
		from_user=sender;
		to_user=receiver;
		this.quiz_id=quiz_id;
		this.sendersHighest=sendersHighest;
	}
	
	public String getLink(){
		return "QuizSummaryPage.jsp?id="+quiz_id;
	}
	public Challenge(User sender, User receiver, int quiz_id){
		from_user=sender;
		to_user=receiver;
		this.quiz_id=quiz_id;
	}

	public User getFrom_user() {
		return from_user;
	}

	public void setFrom_user(User from_user) {
		this.from_user = from_user;
	}

	public User getTo_user() {
		return to_user;
	}

	public void setTo_user(User to_user) {
		this.to_user = to_user;
	}

	public int getLinkToQuiz() {
		return quiz_id;
	}

	public void setLinkToQuiz(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public ArrayList<UserActivity> getSendersHighest() {
		return sendersHighest;
	}

	public void setSendersHighest(ArrayList<UserActivity> sendersHighest) {
		this.sendersHighest = sendersHighest;
	}
	public void addToDatabase(){
		ChallengeController ch = new ChallengeController();
		DBQuizController first = new DBQuizController();
		DBQuizController second = new DBQuizController();
		int from = first.getAuthorId(from_user.getLogin());
		int to = second.getAuthorId(to_user.getLogin());
		ch.addChallenge(from, to, quiz_id);
	}

	
	public void showOnCard(JspWriter out) {
		DBQuizController first = new DBQuizController();
		DBQuizController second = new DBQuizController();
		int from = first.getAuthorId(from_user.getLogin());
		int to = second.getAuthorId(to_user.getLogin());
		String additionalParameters = ""
				+ "<input type='hidden' name='sender' value='"+from+"'>"
				+ "<input type='hidden' name='receiver' value='"+to+"'>"
				+ "<input type='hidden' name='quiz' value='"+this.quiz_id+"'>";
		try {
			out.print("<li> <img border='0' alt='FriendImage' src='" + from_user.getImageURL() + "' width='100' height='100'>"
					+ "<h3><a href='UserPage.jsp?id=" + from + "'>" + from_user.getLogin() + "</a> challenged you to take quiz!</h3>"
					+ "<div style='height:50px;'><form action='AcceptChallenge' method='post'>"
					+additionalParameters
					+ "<input type='submit' value='Accept' class='button tick'></form> ");
			out.print("<form action='RejectChallenge' method='post'>"
					+additionalParameters
					+ "<input type='submit' value='Reject' class='button tick' style='float:right' ></form></div>"
					+ "<div style='text-align:center;'><h4><br>"
			+from_user.getLogin()+"'s max score for the <a href='"+getLink()+"'>Quiz: </a>");
			for(int i=0; i<sendersHighest.size(); i++){
				out.print(sendersHighest.get(i).getScore());
			}
			out.print("</h4></div></li>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
