package DBQuizControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.Quiz;
import database.DBconnector;

public class DBQuizController {
	private Connection connection;

	public DBQuizController() {
		this.connection = new DBconnector().getConnection();
	}

	public ArrayList<String> getQuizCategories() {
		ArrayList<String> result = new ArrayList<String>();
		String order = "select category_name from Categories";
		ResultSet res = null;
		try {
			PreparedStatement stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				result.add(res.getString(1));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getQuestionTypes() {
		ArrayList<String> result = new ArrayList<String>();
		String order = "select type_name from QuestionTypes";
		ResultSet res = null;
		try {
			PreparedStatement stm = connection.prepareStatement(order);
			res = stm.executeQuery();
			while (res.next()) {
				result.add(res.getString(1));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private int getQuizCategory(String category) {
		int result = 0;
		String order = "select category_id from Categories where category_name = '" + category + "'";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet myres = stm.executeQuery();
			while (myres.next()) {
				result = myres.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private int getAuthorId(String author) {
		int result = 0;
		String order = "select user_id from Users where user_login = '" + author + "'";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			ResultSet myres = stm.executeQuery();
			while (myres.next()) {
				result = myres.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addQuiz(Quiz quiz) {
		int id = -1;
		String order = "insert into Quizzes(quiz_name, category_id, quiz_description, author_id,"
				+ "quiz_likes, date_created, quiz_difficulty, times_taken, multiple_pages, immediate_correction,"
				+ "random_questions) Values (?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, quiz.getQuiz_name());
			stm.setInt(2, getQuizCategory(quiz.getQuiz_category()));
			stm.setString(3, quiz.getQuiz_description());
			stm.setInt(4, getAuthorId(quiz.getQuiz_author()));
			stm.setInt(5, quiz.getQuiz_likes());
			stm.setTimestamp(6, quiz.getDate_created_timestamp());
			stm.setString(7, quiz.getQuiz_difficulty());
			stm.setInt(8, quiz.getTimes_taken());
			stm.setBoolean(9, quiz.isDisplayMultiplePages());
			stm.setBoolean(10, quiz.isImmediateCorrection());
			stm.setBoolean(11, quiz.isRandomQuestions());
			stm.executeUpdate();
			ResultSet myRes = stm.getGeneratedKeys();
			while (myRes.next()) {
				id = myRes.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public Quiz getQuiz(int id){
		String query = "select quizzes.quiz_name, categories.category_name, quizzes.quiz_description, users.user_name, quizzes.quiz_likes, quizzes.date_created, quizzes.quiz_difficulty, quizzes.times_taken, quizzes.multiple_pages, quizzes.immediate_correction, quizzes.random_questions "
				+ " from quizzes, users, categories where quiz_id = "
				+ id
				+ " and quizzes.author_id = users.user_id and quizzes.category_id = categories.category_id;";

		Quiz tmpQuiz = null;
		
		PreparedStatement stm = null;
		try {			
			stm = connection.prepareStatement(query);
			
			ResultSet res = stm.executeQuery();
			
			while(res.next()){
				tmpQuiz = new Quiz(res.getString(1), res.getString(3), res.getString(4), res.getInt(5), res.getTimestamp(6), res.getString(2), res.getString(7), res.getInt(8), 
							null,
							res.getBoolean(9), res.getBoolean(10), res.getBoolean(11));
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tmpQuiz;
	}
}
