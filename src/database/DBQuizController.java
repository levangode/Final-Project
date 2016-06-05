package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

import backend.*;

public class DBQuizController {
	private Connection connection;

	public DBQuizController() {
		this.connection = (new DBconnector()).getConnection();
	}

	public ArrayList<Integer> getTopQuizesID(int num, boolean today) {
		ArrayList<Integer> quizes = new ArrayList<>();
		String command = null;
		if (!today) {
			command = "SELECT id FROM Quizzes ORDER BY likeCount DESC LIMIT " + num;
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String curDate = (String) dateFormat.format(date);
			command = "SELECT id FROM quizes where upDate=" + curDate + " ORDER BY likeCount DESC LIMIT " + num;
		}
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet res = null;
		try {
			res = stm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < num; i++) {
			try {
				int cur = res.getInt(1);
				res.next();
				quizes.add(cur);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return quizes;
	}

	public ArrayList<String> getTopQuizesNames(ArrayList<String> ids) {
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0; i < ids.size(); i++) {
			String command = "Select name from Quizzes where id = " + ids.get(i);
			PreparedStatement stm = null;

			try {
				stm = connection.prepareStatement(command);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet res = null;

			try {
				res = stm.executeQuery();
				String cur = res.getString(1);
				res.next();
				names.add(cur);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return names;
	}

	public Quiz buildQuiz(int id) {
		ArrayList<Question> questions = getQuestions(id);
		String command = "Select * from Quizzes where quiz_id =" + id;
		Quiz quiz = null;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();
			int quiz_id = rs.getInt(1);
			String quiz_name = rs.getString(2);
			int category_id = rs.getInt(3);
			String quiz_description = rs.getString(4);
			int author_id = rs.getInt(5);
			int quiz_likes = rs.getInt(6);
			java.sql.Timestamp date_created_timestamp = rs.getTimestamp(7);
			String quiz_difficulty = rs.getString(8);
			int times_taken = rs.getInt(9);
			String quiz_author = getAuthor(author_id);
			quiz = new Quiz(quiz_id, quiz_name, category_id, quiz_description, quiz_author, date_created_timestamp,
					quiz_likes, quiz_difficulty, times_taken, questions);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return quiz;
	}

	public String getAuthor(int id) {
		String authorName = null;

		String command = "Select user_name from Users where user_id =" + id;

		try {
			PreparedStatement stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();
			authorName = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authorName;
	}

	public ArrayList<Question> getQuestions(int id) {
		String command = "Select * from Questions where quiz_id =" + id;
		ArrayList<Question> questions = new ArrayList<>();
		try {
			PreparedStatement stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String question_text = rs.getString(3);
				int question_id = rs.getInt(1);
				int quiz_id = rs.getInt(2);
				String question_type = rs.getString(4);
				String question_description = rs.getString(5);
				int question_time_limit = rs.getInt(6);
				ArrayList<Answer> answers = getAnswers(id);
				Question newQuestion = new Question(question_text, question_id, quiz_id, question_type,
						question_description, question_time_limit, answers);
				questions.add(newQuestion);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}

	public ArrayList<Answer> getAnswers(int id) {
		ArrayList<Answer> answers = new ArrayList<>();
		String command = "Select * from Answers where quiz_id =" + id;
		try {
			PreparedStatement stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				int answer_id = rs.getInt(1);
				int quiz_id = rs.getInt(2);
				String answer_text = rs.getString(3);
				String answer_description = rs.getString(4);
				boolean answer_correct = rs.getBoolean(5);
				String answer_type = rs.getString(6);
				Answer newAnswer = new Answer(answer_id, quiz_id, answer_text, answer_description, answer_correct,
						answer_type);
				answers.add(newAnswer);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return answers;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
