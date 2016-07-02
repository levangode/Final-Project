package DBQuizControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DBAnswerControllers.DBMultipleAnswers;
import DBQuestionControllers.DBQuestionFillInTheBlanks;
import DBQuestionControllers.DBQuestionMultipleChoice;
import DBQuestionControllers.DBQuestionResponse;
import DBQuestionControllers.DBQuestionWithMultipleAnswers;
import backend.Quiz;
import database.DBconnector;
import questions.FillTheBlankQuestion;
import questions.MultipleChoiceQuestion;
import questions.Question;
import questions.QuestionResponse;
import questions.QuestionWithMultipleAnswers;
import quizInfoes.QuizInfo;

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
	

	public int getAuthorId(String author) {
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
				+ "quiz_likes, quiz_difficulty, times_taken, multiple_pages, immediate_correction,"
				+ "random_questions, quiz_score) Values (?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, quiz.getQuiz_name());
			stm.setInt(2, getQuizCategory(quiz.getQuiz_category()));
			stm.setString(3, quiz.getQuiz_description());
			stm.setInt(4, getAuthorId(quiz.getQuiz_author()));
			stm.setInt(5, quiz.getQuiz_likes());
			stm.setString(6, quiz.getQuiz_difficulty());
			stm.setInt(7, quiz.getTimes_taken());
			stm.setBoolean(8, quiz.isDisplayMultiplePages());
			stm.setBoolean(9, quiz.isImmediateCorrection());
			stm.setBoolean(10, quiz.isRandomQuestions());
			int max_score = getMaxScore(quiz);
			stm.setInt(11, max_score);
			stm.executeUpdate();
			ResultSet myRes = stm.getGeneratedKeys();
			while (myRes.next()) {
				id = myRes.getInt(1);
			}

			for (Question cur : quiz.getQuestions()) {
				cur.addToDatabase(id);
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error Adding Questions");
			e.printStackTrace();
		}
		return id;
	}

	private int getMaxScore(Quiz quiz) {
		int counter = 0;
		for(Question q: quiz.getQuestions()){
			counter+=q.getQuestionscore();
		}
		return counter;
	}

	public Quiz getQuiz(int id) {
		String query = "select Quizzes.quiz_name, Categories.category_name, Quizzes.quiz_description, Users.user_name, Quizzes.quiz_likes, Quizzes.date_created, Quizzes.quiz_difficulty, Quizzes.times_taken, Quizzes.multiple_pages, Quizzes.immediate_correction, Quizzes.random_questions, Quizzes.quiz_id, Quizzes.time_limit "
				+ " from Quizzes, Users, Categories where quiz_id = " + id
				+ " and Quizzes.author_id = Users.user_id and Quizzes.category_id = Categories.category_id;";

		Quiz tmpQuiz = null;

		PreparedStatement stm = null;

		System.out.println(query);
		try {
			stm = connection.prepareStatement(query);

			ResultSet res = stm.executeQuery();

			while (res.next()) {
				ArrayList<Question> questions = getQuestions(res.getInt(12));

				tmpQuiz = new Quiz(res.getString(1), res.getString(3), res.getString(4), res.getInt(5),
						res.getTimestamp(6), res.getString(2), res.getString(7), res.getInt(8), questions,
						res.getBoolean(9), res.getBoolean(10), res.getBoolean(11), res.getInt(12));
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tmpQuiz;
	}

	public ArrayList<Quiz> getMyQuizes(String author) {
		String query = "select Quizzes.quiz_name, Categories.category_name, Quizzes.quiz_description, Users.user_name, Quizzes.quiz_likes, Quizzes.date_created, Quizzes.quiz_difficulty, Quizzes.times_taken, Quizzes.multiple_pages, Quizzes.immediate_correction, Quizzes.random_questions, Quizzes.quiz_id, Quizzes.time_limit "
				+ " from Quizzes, Users, Categories where author_id = " + getAuthorId(author)
				+ " and Quizzes.author_id = Users.user_id and Quizzes.category_id = Categories.category_id;";

		ArrayList<Quiz> quizes = new ArrayList<>();
		PreparedStatement stm = null;

		System.out.println(query);
		try {
			stm = connection.prepareStatement(query);

			ResultSet res = stm.executeQuery();

			while (res.next()) {
				Quiz tmpQuiz = null;
				ArrayList<Question> questions = getQuestions(res.getInt(12));

				tmpQuiz = new Quiz(res.getString(1), res.getString(3), res.getString(4), res.getInt(5),
						res.getTimestamp(6), res.getString(2), res.getString(7), res.getInt(8), questions,
						res.getBoolean(9), res.getBoolean(10), res.getBoolean(11), res.getInt(12));
				quizes.add(tmpQuiz);
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizes;
	}

	private ArrayList<Question> getQuestions(int id) {
		ArrayList<Question> questions = new ArrayList<Question>();

		// Response questions
		DBQuestionResponse db1 = new DBQuestionResponse();
		List<QuestionResponse> responseQuestions = db1.retrieveQuestions(id);
		questions.addAll(responseQuestions);

		// Multiple choice questions
		DBQuestionMultipleChoice db2 = new DBQuestionMultipleChoice();
		List<MultipleChoiceQuestion> multipleChoiceQuestions = db2.retrieveQuestions(id);
		questions.addAll(multipleChoiceQuestions);

		// Fill in the blanks questions
		DBQuestionFillInTheBlanks db3 = new DBQuestionFillInTheBlanks();
		List<FillTheBlankQuestion> fillTheBlanksQuestions = db3.retrieveQuestions(id);
		questions.addAll(fillTheBlanksQuestions);

		// Multiple answers questions
		DBQuestionWithMultipleAnswers db4 = new DBQuestionWithMultipleAnswers();
		List<QuestionWithMultipleAnswers> multipleAnswersQuestions = db4.retrieveQuestions(id);
		questions.addAll(multipleAnswersQuestions);

		Collections.sort(questions);
		
		return questions;
	}
	public void incrementTimesTaken(int quiz_id){
		String order1 = "select times_taken from Quizzes where quiz_id = " + quiz_id + " FOR UPDATE;";
		String order2 = "update Quizzes set times_taken = times_taken + 1 where quiz_id = " + quiz_id + ";";
		String order3 = "commit;";
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		try {
			stm1 = connection.prepareStatement(order1);
			stm2 = connection.prepareStatement(order2);
			stm3 = connection.prepareStatement(order3);
			stm1.executeQuery();
			stm2.executeUpdate();
			stm3.executeQuery();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void incrementLikes(int quiz_id) {
		String order1 = "select quiz_likes from Quizzes where quiz_id = " + quiz_id + " FOR UPDATE;";
		String order2 = "update Quizzes set quiz_likes = quiz_likes + 1 where quiz_id = " + quiz_id + ";";
		String order3 = "commit;";
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		try {
			stm1 = connection.prepareStatement(order1);
			stm2 = connection.prepareStatement(order2);
			stm3 = connection.prepareStatement(order3);
			stm1.executeQuery();
			stm2.executeUpdate();
			stm3.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addLike(String user_login, int quiz_id){
		int user_id = getAuthorId(user_login);
		String order = "INSERT INTO `Likes`(`user_id`, `quiz_id`) VALUES ("+user_id+","+quiz_id+")";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(order);
			stm.executeUpdate();
			incrementLikes(quiz_id);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean canLike(String user_login, int quiz_id){
		int user_id = getAuthorId(user_login);
		String order = "select count(*) as count from Likes where quiz_id = "+quiz_id+" AND user_id = "+user_id;
		int count = 0;
		PreparedStatement stm = null;
		ResultSet myResultSet = null;
		try {
			stm = connection.prepareStatement(order);
			myResultSet = stm.executeQuery();
			while(myResultSet.next()){
				count=myResultSet.getInt("count");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count > 0){
			return false;
		} return true;
	}
}
