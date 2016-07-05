package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBAnswerControllers.DBMultipleAnswers;
import answers.Answer;
import database.DBconnector;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class DBQuestionWithMultipleAnswers {
	private Connection connection;

	
	public DBQuestionWithMultipleAnswers() {
		this.connection = new DBconnector().getConnection();
	}

	public DBQuestionWithMultipleAnswers(Connection con) {
		this.connection = con;
	}
	
	public List<QuestionWithMultipleAnswers> retrieveQuestions(int quiz_id){
		List<QuestionWithMultipleAnswers> questions = new ArrayList<QuestionWithMultipleAnswers>();
		
		String query = "select question_text, question_data, num_answers, score, answers_ordered, question_id, question_number from Questions_MultipleAnswers where quiz_id = "
					+ quiz_id + " ;";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				QuestionWithMultipleAnswers newQuestion;
				
				ArrayList<Answer> answers = getAnswers(rs.getInt("question_id"));
				
				newQuestion = new QuestionWithMultipleAnswers(
								rs.getString("question_text"),
								QuestionTypes.MultiAnswer,
								rs.getString("question_data"),
								rs.getInt("score"),
								rs.getInt("num_answers"),
								rs.getBoolean("answers_ordered"),
								answers,
								rs.getInt("question_number")
							);
				
				questions.add(newQuestion);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return questions;
	}
	
	private ArrayList<Answer> getAnswers(int id){
		ArrayList<Answer> res;
		
		DBMultipleAnswers db = new DBMultipleAnswers();
		
		res = new ArrayList<Answer>( db.retrieveAnswers(id) );
		
		return res;
	}
	
	public void addQuestion(QuestionWithMultipleAnswers question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_MultipleAnswers(quiz_id, question_text, question_data, score, num_answers, answers_ordered, question_number) value ("
				+ quiz_id + ", "
				+ "'" + question.getQuestiontext() + "'" + ", "
				+ "'" + question.getQuestiondescription() + "'" + ", "
				+ question.getQuestionscore() + ", "
				+ question.getNumanswers() + ", "
				+ question.getAnswersordered() + ", "
				+ question.getQuestionnumber()
				+ ");";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			int question_id = -1;
			
			while(rs.next()){
				question_id = rs.getInt(1);
			}
			
			for(Answer cur: question.getAnswers()){
				cur.addToDatabase(question_id);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			
			throw new Exception("Error durin insertion in DB");
			//e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
