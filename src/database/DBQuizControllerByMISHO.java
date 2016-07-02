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

import answers.Answer;
import backend.Quiz;
import questions.Question;

public class DBQuizControllerByMISHO {
	private Connection connection;

	public DBQuizControllerByMISHO() {
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

	public int getQuizCategoryId(String category) {
		int id = 0;
		String command = "Select category_id from Categories where category_name =" + "'" + category + "';";

		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command);
			ResultSet res = stm.executeQuery();
			if (res.next()) {
				id = res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public int getAuthorId(String authorName) {
		int id = 0;
		String command = "Select user_id from Users where user_login = " + "'" + authorName + "'";
		// command="Select * from Users";
		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command);
			ResultSet res = stm.executeQuery();
			System.out.println("Trying to find the user: " + stm);
			if (res.next()) {
				id = res.getInt(1);
				System.out.println("ID is :" + id);
			} else {
				System.out.println("User not found!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void addQuiz(Quiz quiz) {
		String command = "INSERT INTO Quizzes (quiz_id,quiz_name,category_id,quiz_description,author_id,quiz_likes,"
				+ "date_created,quiz_difficulty,times_taken,multiple_pages,immediate_correction,random_questions) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, 0);
			stm.setString(2, quiz.getQuiz_name());
			stm.setInt(3, getQuizCategoryId(quiz.getQuiz_category()));
			stm.setString(4, quiz.getQuiz_description());
			stm.setInt(5, getAuthorId(quiz.getQuiz_author()));// getauthorID ver
																// poulobs
																// verafers
			stm.setInt(6, quiz.getQuiz_likes());
			stm.setTimestamp(7, quiz.getDate_created_timestamp());
			stm.setString(8, quiz.getQuiz_difficulty());
			stm.setInt(9, quiz.getTimes_taken());
			stm.setBoolean(10, quiz.isDisplayMultiplePages());
			stm.setBoolean(11, quiz.isImmediateCorrection());
			stm.setBoolean(12, quiz.isRandomQuestions());
			System.out.println("PreparedStatement is: " + stm);
			System.out.println("blaaas");
			stm.executeUpdate();
			System.out.println("FUCK");
			ResultSet res = stm.getGeneratedKeys();
			int quiz_id = 0;
			if (res.next()) {
				quiz_id = res.getInt(1);
				System.out.println("Starting to add questions");
				addQuestions(quiz, quiz_id);
			} else {
				System.out.println("Quiz id not generated!");
				return;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("QUIZ ADDED SUCCESFULLY");

	}

	public void addAnswer(Answer answer, int question_id) {
		String command = "INSERT INTO Answers (answer_text,"
				+ " answer_description , answer_correct,question_id) values(?,?,?,?)";

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
		String command = "Insert INTO Questions ( question_id,quiz_id,   question_text , question_type ,"
				+ "question_description , question_time_limit) values(?,?,?,?,?,?)";
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
			System.out.println("Question statemnt:" + stm);
			stm.executeUpdate();

			ResultSet res = stm.getGeneratedKeys();
			int question_id = 0;
			if (res.next()) {
				System.out.println("starting to add answers");
				question_id = res.getInt(1);
				addAnswers(question.getAnswers(), question_id);
			} else {
				System.out.println("Question ID not generated!");
				return;
			}

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

	public String getQuizCategoryStr(int id) {
		String str = "";
		String command = "Select category_name from Categories where category_id =" + id;
		PreparedStatement stm;

		try {
			stm = connection.prepareStatement(command);
			ResultSet res = stm.executeQuery();
			if (res.next()) {
				str = res.getString(1);
			} else {
				System.out.println("Category not found!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;
	}

	public Quiz getQuiz(int id) {
		ArrayList<Question> questions = getQuestions(id);
		String command = "Select * from Quizzes where quiz_id =" + id;
		Quiz quiz = null;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(command);
			ResultSet rs = stm.executeQuery();
			String quiz_name = rs.getString(2);
			int category_id = rs.getInt(3);
			String quiz_description = rs.getString(4);
			int author_id = rs.getInt(5);
			int quiz_likes = rs.getInt(6);
			java.sql.Timestamp date_created = rs.getTimestamp(7);
			String quiz_difficulty = rs.getString(8);
			int times_taken = rs.getInt(9);
			boolean multiple_pages = rs.getBoolean(10);
			boolean immediate_correction = rs.getBoolean(11);
			boolean random_questions = rs.getBoolean(12);

			quiz = new Quiz(quiz_name, quiz_description, getAuthorStr(author_id), quiz_likes, date_created,
					getQuizCategoryStr(category_id), quiz_difficulty, times_taken, questions, multiple_pages,
					immediate_correction, random_questions);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return quiz;
	}

	public String getAuthorStr(int id) {
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
				/*
				 * Question newQuestion = new Question(question_text,
				 * getQuestionTypeStr(question_type), question_description,
				 * question_time_limit, answers); questions.add(newQuestion);
				 */
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

				/*
				 * Answer newAnswer = new Answer(answer_text,
				 * answer_description, answer_correct); answers.add(newAnswer);
				 */
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return answers;
	}

}
