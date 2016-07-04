package backend;

import java.util.ArrayList;

import ChallengeControllers.ChallengeController;
import quizInfoes.UserActivity;

public class Challenge {
	private int from_user;
	private int to_user;
	private int quiz_id;
	private ArrayList<UserActivity> sendersHighest;
	
	public Challenge(int sender, int receiver, int quiz_id, ArrayList<UserActivity> sendersHighest){
		from_user=sender;
		to_user=receiver;
		this.quiz_id=quiz_id;
		this.sendersHighest=sendersHighest;
	}
	
	public String getLink(){
		return "QuizSummaryPage.jsp?id="+quiz_id;
	}
	public Challenge(int sender, int receiver, int quiz_id){
		from_user=sender;
		to_user=receiver;
		this.quiz_id=quiz_id;
	}

	public int getFrom_user() {
		return from_user;
	}

	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}

	public int getTo_user() {
		return to_user;
	}

	public void setTo_user(int to_user) {
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
		ch.addChallenge(from_user, to_user, quiz_id);
	}
	
	
}
