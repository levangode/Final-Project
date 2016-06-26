package DBQuizControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import backend.QuizInfo;
import backend.QuizInfoFactory;
import database.DBconnector;

public class QuizInfoController {
	private static final int LIMIT_RESULTS = 10;
	private Connection connection;

	public QuizInfoController() {
		this.connection = new DBconnector().getConnection();
	}

	public QuizInfoController(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<QuizInfo> getPopularQuizzes() {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "select quiz_id, quiz_name, user_login, date_created, times_taken from Quizzes "
				+ "join Users on author_id=user_id " + "order by times_taken limit " + LIMIT_RESULTS;
		ResultSet res = null;
		try {
			PreparedStatement stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				int quiz_id = res.getInt("quiz_id");
				String quiz_name = res.getString("quiz_name");
				String quiz_author = res.getString("user_login");
				Timestamp quiz_date = res.getTimestamp("date_created");
				int times_taken = res.getInt("times_taken");
				result.add(QuizInfoFactory.getQuizInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<QuizInfo> getLatestQuizzes() {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "SELECT quiz_id, quiz_name, user_login, date_created, times_taken " + "FROM Quizzes "
				+ "JOIN Users ON author_id = user_id " + "ORDER BY date_created DESC " + "LIMIT " + LIMIT_RESULTS;
		ResultSet res = null;
		try {
			PreparedStatement stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				int quiz_id = res.getInt("quiz_id");
				String quiz_name = res.getString("quiz_name");
				String quiz_author = res.getString("user_login");
				Timestamp quiz_date = res.getTimestamp("date_created");
				int times_taken = res.getInt("times_taken");
				result.add(QuizInfoFactory.getQuizInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<QuizInfo> getMyQuizzes(String user_login) {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "SELECT quiz_id, quiz_name, user_login, date_created, times_taken " + "FROM Quizzes "
				+ "JOIN Users ON author_id = user_id " + "AND user_login =  " + "'" + user_login + "' "
				+ "ORDER BY date_created DESC " + "LIMIT " + LIMIT_RESULTS;
		ResultSet res = null;
		try {
			PreparedStatement stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				int quiz_id = res.getInt("quiz_id");
				String quiz_name = res.getString("quiz_name");
				String quiz_author = res.getString("user_login");
				Timestamp quiz_date = res.getTimestamp("date_created");
				int times_taken = res.getInt("times_taken");
				result.add(QuizInfoFactory.getQuizInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
