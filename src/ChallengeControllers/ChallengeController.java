package ChallengeControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.TryCatchFinally;

import DBConnector.Connector;
import DBQuizControllers.QuizInfoController;
import backend.Challenge;
import backend.ChallengeFactory;
import backend.User;
import database.DBconnector;
import database.UserController;
import quizInfoes.UserActivity;

public class ChallengeController {
	private Connection connection;

	public ChallengeController() {
		this.connection = new DBconnector().getConnection();
	}

	public void addChallenge(int from_user, int to_user, int quiz_id) {
		String check = "select * from Challenges where from_user = " + from_user + " AND to_user = " + to_user
				+ " AND quiz_id = " + quiz_id;
		System.out.println(check);
		PreparedStatement checkStm = null;
		ResultSet res = null;
		try {
			checkStm = connection.prepareStatement(check);
			res = checkStm.executeQuery();
			if (res.next()) {
				// Challenge active
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		String order = "insert into Challenges(from_user, to_user, quiz_id) Values (" + from_user + "," + to_user + ","
				+ quiz_id + ");";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Challenge> getChallenges(int user_id) {
		ArrayList<Challenge> result = new ArrayList<Challenge>();
		String order = "select from_user, to_user, quiz_id from Challenges where to_user = " + user_id;
		PreparedStatement stm = null;
		ResultSet res = null;
		
		Connection con1 = null, con2 = null;
		
		try {
			con1 = Connector.getConnection();
			con2 = Connector.getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				UserController c = new UserController(con1);
				UserController d = new UserController(con2);
				User sender = c.getUserByID(res.getInt("from_user"));
				User receiver = d.getUserByID(res.getInt("to_user"));
				int quiz_id = res.getInt("quiz_id");
				QuizInfoController q = new QuizInfoController();
				ArrayList<UserActivity> sendersHighest = q.getUserHighest(sender.getLogin(), quiz_id, 1);
				result.add(ChallengeFactory.getChallenge(sender, receiver, quiz_id, sendersHighest));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			Connector.returnConnection(con1);
			Connector.returnConnection(con2);
		}
		return result;
	}

	public void cancelChallenge(int from_user, int to_user, int quiz_id) {
		String order = "delete from Challenges where from_user = " + from_user + " AND to_user = " + to_user
				+ " AND quiz_id = " + quiz_id;
		PreparedStatement stm = null;
		System.out.println(order);
		try {
			stm = connection.prepareStatement(order);
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
