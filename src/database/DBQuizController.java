package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;
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

	public void addQuiz(Quiz quiz) {
		String command = "INSERT INTO Quizzes VALUES (";
		long quiz_id = quiz.getQuizID();
		String quiz_name = quiz.getQuizName();
		int category_id = quiz.getCategoryID();
		String quiz_description = quiz.getQuizDescription();
		int author_id = quiz.getAuthorid();
		int quiz_likes = quiz.getQuizLikes();
		java.sql.Timestamp date_created = quiz.getDateCreatedTimestamp();
		String quiz_difficulty = quiz.getQuizDifficulty();
		int times_taken = quiz.getTimesTaken();

		command += quiz_id + ", '" + quiz_name + "', " + category_id + ", " + "'" + quiz_description + "' ," + author_id
				+ ", " + quiz_likes + ", " + date_created + "," + "'" + quiz_difficulty + "'," + times_taken + ");";

		System.out.println(command);
		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
			stm.executeUpdate();
			if (quiz_id == 0) {
				ResultSet res = stm.getGeneratedKeys();
			
				if (res.next())
					quiz_id = res.getLong(1);
			}
			addQuestions(quiz, quiz_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addAnswer(Answer answer, int question_id) {
		String command = "INSERT INTO Answers (answer_id,answer_text,"
				+ " answer_description , answer_correct,question_id) values(?,?,?,?,?)";

		String answer_text = answer.getAnswerText();
		String answer_description = answer.getAnswerDescription();
		boolean answer_correct = answer.getAnswerCorrect();

		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command);
			stm.setInt(1, 0);
			stm.setString(1, answer_text);
			stm.setString(3, answer_description);
			stm.setBoolean(4, answer_correct);
			stm.setInt(5, question_id);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addAnswers(ArrayList<Answer> answers, int question_id) {
		for (int i = 0; i < answers.size(); i++) {
			addAnswer(answers.get(i), question_id);
		}
	}

	public int getQuestionTypeId(String type) {
		int res = 0;
		String command = "select type_id from QuestionTypes where type_name =" + "'" + type + "';";

		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command);
			ResultSet result = stm.executeQuery();
			if (result.next()) {
				res = result.getInt(1);
			} else {
				System.out.println("Type not found!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public void addQuestion(Question question, int quiz_id) {
		String command = "Insert INTO Questions ( quiz_id,   question_text , question_type ,"
				+ "question_description , question_time_limit) values (?,?,?,?,?,?)";
		String question_text = question.getQuestiontext();
		String question_type = question.getQuestiontype();
		String question_description = question.getQuestiondescription();
		int questions_time_limit = (int) question.getQuestiontimelimit();

		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, 0);
			stm.setInt(2, quiz_id);
			stm.setString(3, question_text);
			stm.setInt(4, getQuestionTypeId(question_type));
			stm.setString(5, question_description);
			stm.setInt(6, questions_time_limit);
			stm.executeUpdate();
			ResultSet res = stm.getGeneratedKeys();
			int question_id = 0;
			if (res.next()) {
				question_id = res.getInt(1);
			} else {
				System.out.println("Question ID not generated!");
				return;
			}
			addAnswers(question.getAnswers(), question_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addQuestions(Quiz quiz, int quiz_id) {
		if (quiz.getQuestions() == null) {
			System.out.println("WTF WTF WTF WTF");
			return;
		}
		ArrayList<Question> questions = quiz.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			addQuestion(questions.get(i), quiz_id);
		}
	}

	public Quiz getQuiz(int id) {
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

	public String getQuestionTypeStr(int id) {
		String str = "";
		String command = "select type_name from QuestionTypes where type_id =" + id + ";";

		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command);
			ResultSet result = stm.executeQuery();
			if (result.next()) {
				str = result.getString(1);
			} else {
				System.out.println("Type not found!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;
	}

	public ArrayList<Question> getQuestions(int id) {
		String command = "Select question_id,question_type,question_description,question_text,"
				+ "question_time_limit from Questions where quiz_id =" + id;
		ArrayList<Question> questions = new ArrayList<>();

		try {
			PreparedStatement stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				int question_id = rs.getInt(1);
				int question_type = rs.getInt(2);
				String question_description = rs.getString(3);
				String question_text = rs.getString(4);
				int question_time_limit = rs.getInt(5);
				ArrayList<Answer> answers = getAnswers(question_id);
				Question newQuestion = new Question(question_text, getQuestionTypeStr(question_type),
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
		String command = "Select answer_text,answer_description,answer_correct from Answers where question_id =" + id;
		try {
			PreparedStatement stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				String answer_text = rs.getString(1);
				String answer_description = rs.getString(2);
				boolean answer_correct = rs.getBoolean(3);

				Answer newAnswer = new Answer(answer_text, answer_description, answer_correct);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO close
		return result;
	}

}
