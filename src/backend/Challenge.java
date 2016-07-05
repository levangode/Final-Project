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

	@Override
	public void showOnCard(JspWriter out) {
		try {
			out.print("<li> <img border='0' alt='FriendImage' src='" + from_user.getImageURL() + "' width='100' height='100'>"
					+ "<h3><a href='UserPage.jsp?id=" + 9 + "'>" + from_user.getLogin() + "</a> challenged you to take quiz!</h3>"
					+ "<div><form action='AcceptChallenge' method='post'>"
					+ "<input type='submit' value='Accept' class='button tick'><input type='hidden' name='fid' value='"
					+ 9 + "'></form> ");
			out.print("<form action='RejectChallenge' method='post'>"
					+ "<input type='submit' value='Reject' class='button tick' style='float:right' ><input type='hidden' name='fid' value='"
					+ 9 + "'></form></div>"
							+ "<div><h3><a href='"+getLink()+"'>Link</a></h3></div>"
							+ "</li>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
