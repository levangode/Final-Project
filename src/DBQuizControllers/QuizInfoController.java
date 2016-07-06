package DBQuizControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DBConnector.Connector;
import database.DBconnector;


import quizInfoes.Constants;

import quizInfoes.HighScore;
import quizInfoes.QuizDetailedInfo;
import quizInfoes.QuizFullSummary;
import quizInfoes.QuizInfo;
import quizInfoes.QuizInfoFactory;

import quizInfoes.ShortInfo;

import quizInfoes.Statistics;
import quizInfoes.UserActivity;

public class QuizInfoController {

	private Connection connection;

	public QuizInfoController() {
		this.connection = new DBconnector().getConnection();
	}

	public ArrayList<QuizDetailedInfo> getQuizzes(String category) {
		ArrayList<QuizDetailedInfo> result = new ArrayList<QuizDetailedInfo>();
		String addition = "";
		if(category != null){
			addition = "AND c.category_name = '"+category+"'";
		}
		String order = ""
				+ "SELECT quiz_id, quiz_name, quiz_description, category_name, user_login, date_created, times_taken, quiz_likes "
				+ "FROM Quizzes q " + "JOIN Users ON author_id = user_id "
				+ "JOIN Categories c ON c.category_id = q.category_id " +addition+ "ORDER BY date_created DESC " + "LIMIT "
				+ Constants.LIMIT_RESULTS_FOR_FEED;
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return result;
	}

	public ArrayList<QuizInfo> getPopularQuizzes(String orderBy) {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "select quiz_id, quiz_name, user_login, date_created, times_taken from Quizzes "
				+ "join Users on author_id=user_id " + "order by "+orderBy+" DESC limit " + Constants.LIMIT_RESULTS_FOR_BLOCKS;
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return result;
	}

	public ArrayList<QuizInfo> getLatestQuizzes() {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "SELECT quiz_id, quiz_name, user_login, date_created, times_taken " + "FROM Quizzes "
				+ "JOIN Users ON author_id = user_id " + "ORDER BY date_created DESC " + "LIMIT "
				+ Constants.LIMIT_RESULTS_FOR_BLOCKS;
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
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
	
	public List<QuizDetailedInfo> getQuizListByName(String nameFragment){
		
		List<QuizDetailedInfo> result = new ArrayList<QuizDetailedInfo>();
		
		
		String query = "select quiz_id, quiz_name, quiz_description, category_name, user_login, date_created, times_taken, quiz_likes from Quizzes, Users, Categories "
				+ "where quiz_name like '%" + nameFragment + "%' and Quizzes.category_id = Categories.category_id and author_id = user_id;";
		
		try {
			PreparedStatement stm = connection.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				int quiz_id = rs.getInt("quiz_id");
				String quiz_name = rs.getString("quiz_name");
				String quiz_author = rs.getString("user_login");
				Timestamp quiz_date = rs.getTimestamp("date_created");
				int times_taken = rs.getInt("times_taken");
				String quiz_description = rs.getString("quiz_description");
				String quiz_category = rs.getString("category_name");
				int quiz_likes = rs.getInt("quiz_likes");
				result.add(QuizInfoFactory.getDetailedInfo(quiz_name, times_taken, quiz_author, quiz_date, quiz_id,
						quiz_category, quiz_description, quiz_likes));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return result;
	}


	public ArrayList<UserActivity> getUserActivity(String user_login, int quiz_id) {
		return getUserHighest(user_login, quiz_id, Constants.LIMIT_OF_SCORES);
		
	}
	
	public ArrayList<UserActivity> getUserHighest(String user_login, int quiz_id, int limit){
		ArrayList<UserActivity> act = new ArrayList<UserActivity>();
		int user_id = new DBQuizController().getAuthorId(user_login);
		String order = "Select time_finished, time_taken, score from Quiz_taken where user_id = " + user_id
				+ " AND quiz_id = " + quiz_id + " order by time_finished DESC limit "+limit;
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				Timestamp time_finished = res.getTimestamp("time_finished");
				int time_taken = res.getInt("time_taken");
				double score = res.getDouble("score");
				act.add(QuizInfoFactory.getUserActivity(time_finished, time_taken, score, user_login));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return act;
	}

	public ArrayList<HighScore> getHighScores(int quiz_id, int pastHours) {
		ArrayList<HighScore> act = new ArrayList<HighScore>();
		String timeLimit = "";
		if(pastHours > -1){
			timeLimit = "AND CURRENT_TIMESTAMP() - INTERVAL '"+pastHours+"'"+" HOUR < time_finished ";
		}
		String order = ""
				+ "SELECT user_login, score "
				+ "FROM Quiz_taken q "
				+ "JOIN Users u ON u.user_id = q.user_id "
				+ "WHERE quiz_id = "+quiz_id+" "
				+ timeLimit
				+ "ORDER BY score DESC "
				+ "LIMIT "+Constants.LIMIT_OF_SCORES;
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				double score = res.getDouble("score");
				String user_login = res.getString("user_login");
				act.add(QuizInfoFactory.getHighScore(score, user_login));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return act;
	}

	public QuizFullSummary getQuizSummary(int quiz_id) {
		String order = ""
				+ "SELECT quiz_name, quiz_description, category_name, user_login, quiz_score, quiz_difficulty, date_created, immediate_correction, quiz_likes, times_taken, time_limit "
				+ "FROM Quizzes q " + "JOIN Users u ON u.user_id = q.author_id "
				+ "JOIN Categories c ON c.category_id = q.category_id " + "WHERE quiz_id = " + quiz_id;
		PreparedStatement stm = null;
		QuizFullSummary summary = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				int quiz_score = res.getInt("quiz_score");
				String quiz_name = res.getString("quiz_name");
				String quiz_description = res.getString("quiz_description");
				String quiz_category = res.getString("category_name");
				String user_login = res.getString("user_login");
				String quiz_difficulty = res.getString("quiz_difficulty");
				int time_limit = res.getInt("time_limit");
				Timestamp date_created = res.getTimestamp("date_created");
				Boolean immediate_correction = res.getBoolean("immediate_correction");
				int quiz_likes = res.getInt("quiz_likes");
				int times_taken = res.getInt("times_taken");
				summary = QuizInfoFactory.getFullSummary(quiz_name, times_taken, user_login, quiz_score, date_created, quiz_id,
						quiz_category, quiz_description, quiz_likes, quiz_difficulty, immediate_correction, time_limit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return summary;
	}
	public Statistics getStatistics(int quiz_id){
		Statistics statist = null;
		String order = ""
				+ "SELECT COUNT( * ) AS count, AVG( score ) AS average "
				+ "FROM Quiz_taken where quiz_id = "+quiz_id;
		ResultSet res=null;
		try {
			PreparedStatement stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				int count = res.getInt("count");
				double avgScore = res.getDouble("average");
				statist=QuizInfoFactory.getStatistics(count, avgScore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return statist;
		
	}
	public ArrayList<QuizInfo> getMyQuizzes(String author) {
		ArrayList<QuizInfo> result = new ArrayList<QuizInfo>();
		String order = "" + "SELECT quiz_id, quiz_name, user_login, date_created, times_taken " + "FROM Quizzes "
				+ "JOIN Users ON author_id = user_id AND author_id = " + getAuthorID(author)
				+ " ORDER BY date_created DESC " + "LIMIT " + Constants.LIMIT_RESULTS_FOR_BLOCKS;
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		return result;
	}

	public ArrayList<ShortInfo> getMyQuizesShortInfo(String author) {
		String query = "select Quizzes.quiz_id,Quizzes.quiz_name, Quizzes.quiz_description, Quizzes.quiz_likes, Quizzes.date_created "
				+ " from Quizzes where author_id = " + getAuthorID(author);

		ArrayList<ShortInfo> quizes = new ArrayList<>();
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(query);

			ResultSet res = stm.executeQuery();

			while (res.next()) {
				ShortInfo tmpQuiz = null;
				tmpQuiz = new ShortInfo(res.getInt(1), res.getString(2), res.getTimestamp(5), res.getString(3),
						res.getInt(4));

				quizes.add(tmpQuiz);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
 
		return quizes;
	}

}
