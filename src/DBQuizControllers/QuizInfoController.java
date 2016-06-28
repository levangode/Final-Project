package DBQuizControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.DBconnector;
import quizInfoes.HighScore;
import quizInfoes.QuizDetailedInfo;
import quizInfoes.QuizFullSummary;
import quizInfoes.QuizInfo;
import quizInfoes.QuizInfoFactory;
import quizInfoes.UserActivity;

public class QuizInfoController {
	private static final int LIMIT_RESULTS_FOR_BLOCKS = 10;
	private static final int LIMIT_RESULTS_FOR_FEED = 100;
	private static final int LIMIT_OF_SCORES = 15;
	private Connection connection;

	public QuizInfoController() {
		this.connection = new DBconnector().getConnection();
	}

	public ArrayList<QuizDetailedInfo> getQuizzes() {
		ArrayList<QuizDetailedInfo> result = new ArrayList<QuizDetailedInfo>();
		String order = ""
				+ "SELECT quiz_id, quiz_name, quiz_description, category_name, user_login, date_created, times_taken, quiz_likes "
				+ "FROM Quizzes q " + "JOIN Users ON author_id = user_id "
				+ "JOIN Categories c ON c.category_id = q.category_id " + "ORDER BY date_created DESC " + "LIMIT "
				+ LIMIT_RESULTS_FOR_FEED;
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
				String quiz_description = res.getString("quiz_description");
				String quiz_category = res.getString("category_name");
				int quiz_likes = res.getInt("quiz_likes");
				result.add(QuizInfoFactory.getDetailedInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id,
						quiz_category, quiz_description, quiz_likes));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<QuizInfo> getPopularQuizzes() {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "select quiz_id, quiz_name, user_login, date_created, times_taken from Quizzes "
				+ "join Users on author_id=user_id " + "order by times_taken limit " + LIMIT_RESULTS_FOR_BLOCKS;
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
				+ "JOIN Users ON author_id = user_id " + "ORDER BY date_created DESC " + "LIMIT "
				+ LIMIT_RESULTS_FOR_BLOCKS;
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

	private int getAuthorID(String authorName) {
		int id = 0;
		String command = "Select user_id from Users where user_login = " + "'" + authorName + "'";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(command);
			ResultSet res = stm.executeQuery();
			if (res.next()) {
				id = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public ArrayList<UserActivity> getUserActivity(String user_login, int quiz_id) {
		ArrayList<UserActivity> act = new ArrayList<UserActivity>();
		int user_id = getAuthorID(user_login);
		String order = "Select time_finished, time_taken, score from Quiz_taken where user_id = " + user_id
				+ " AND quiz_id = " + quiz_id;
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				Timestamp time_finished = res.getTimestamp("time_finished");
				Timestamp time_taken = res.getTimestamp("time_taken");
				int score = res.getInt("score");
				act.add(QuizInfoFactory.getUserActivity(time_finished, time_taken, score, user_login));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return act;
	}

	public ArrayList<HighScore> getHighScores(int quiz_id) {
		ArrayList<HighScore> act = new ArrayList<HighScore>();
		String order = "Select user_login, score from Quiz_taken q join Users u on u.user_id=q.user_id where quiz_id = "
				+ quiz_id + " order by score limit " + LIMIT_OF_SCORES;
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				int score = res.getInt("score");
				String user_login = res.getString("user_login");
				act.add(QuizInfoFactory.getHighScore(score, user_login));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return act;
	}

	public QuizFullSummary getQuizSummary(int quiz_id) {
		String order = ""
				+ "SELECT quiz_name, quiz_description, category_name, user_login, quiz_difficulty, date_created, immediate_correction, quiz_likes, times_taken "
				+ "FROM Quizzes q " + "JOIN Users u ON u.user_id = q.author_id "
				+ "JOIN Categories c ON c.category_id = q.category_id " + "WHERE quiz_id = " + quiz_id;
		PreparedStatement stm = null;
		QuizFullSummary summary = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				String quiz_name = res.getString("quiz_name");
				String quiz_description = res.getString("quiz_description");
				String quiz_category = res.getString("category_name");
				String user_login = res.getString("user_login");
				String quiz_difficulty = res.getString("quiz_difficulty");
				Timestamp date_created = res.getTimestamp("date_created");
				Boolean immediate_correction = res.getBoolean("immediate_correction");
				int quiz_likes = res.getInt("quiz_likes");
				int times_taken = res.getInt("times_taken");
				summary = QuizInfoFactory.getFullSummary(quiz_name, times_taken, user_login, date_created, quiz_id,
						quiz_category, quiz_description, quiz_likes, quiz_difficulty, immediate_correction);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return summary;
	}

	public ArrayList<QuizInfo> getMyQuizzes(String author) {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "SELECT quiz_id, quiz_name, user_login, date_created, times_taken " + "FROM Quizzes "
				+ "JOIN Users ON author_id = user_id AND author_id = " + getAuthorID(author)
				+ " ORDER BY date_created DESC " + "LIMIT " + LIMIT_RESULTS_FOR_BLOCKS;
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
