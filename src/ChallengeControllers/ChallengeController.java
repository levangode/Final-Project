package ChallengeControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBQuizControllers.QuizInfoController;
import backend.Challenge;
import backend.ChallengeFactory;
import backend.User;
import database.DBconnector;
import database.UserController;
import quizInfoes.UserActivity;

public class ChallengeController {
	private Connection connection;
	
	public ChallengeController(){
		this.connection=new DBconnector().getConnection();
	}
	
	public void addChallenge(int from_user, int to_user, int quiz_id){
		String order="insert into Challenges(from_user, to_user, quiz_id) Values ("+from_user+","+to_user+","+quiz_id+");";
		PreparedStatement stm = null;
		try {
			stm=connection.prepareStatement(order);
			stm.executeUpdate();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<Challenge> getChallenges(int user_id){
		ArrayList<Challenge> result=new ArrayList<Challenge>();
		String order="select from_user, to_user, quiz_id from Challenges where to_user = "+user_id;
		PreparedStatement stm = null;
		ResultSet res = null;
		try {
			stm = connection.prepareStatement(order);
			res=stm.executeQuery();
			while(res.next()){
				UserController c = new UserController();
				UserController d = new UserController();
				User sender = c.getUserByID(res.getInt("from_user"));
				User receiver = d.getUserByID(res.getInt("to_user"));
				int quiz_id = res.getInt("quiz_id");
				QuizInfoController q = new QuizInfoController();
				ArrayList<UserActivity> sendersHighest = q.getUserHighest(sender.getLogin(), quiz_id, 1);
				result.add(ChallengeFactory.getChallenge(sender, receiver, quiz_id, sendersHighest));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
