package backend;

import java.util.ArrayList;

import quizInfoes.UserActivity;

public class ChallengeFactory {
	public static Challenge getChallenge(int sender, int receiver, int quiz_id){
		return new Challenge(sender, receiver, quiz_id);
	}
	public static Challenge getChallenge(int sender, int receiver, int quiz_id, ArrayList<UserActivity> sendersHighest){
		return new Challenge(sender, receiver, quiz_id, sendersHighest);
	}
}
