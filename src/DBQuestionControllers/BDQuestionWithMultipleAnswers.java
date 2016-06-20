package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBconnector;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class BDQuestionWithMultipleAnswers {
	private Connection connection;

	
	public BDQuestionWithMultipleAnswers() {
		this.connection = new DBconnector().getConnection();
	}
	
	public List<QuestionWithMultipleAnswers> retrieveQuestions(int quiz_id){
		List<QuestionWithMultipleAnswers> questions = new ArrayList<QuestionWithMultipleAnswers>();
		
		String query = "select question_text, question_data, question_time_limit, num_answers, score, answers_ordered from Questions_MultipleAnswers where quiz_id = "
					+ quiz_id + " ;";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				QuestionWithMultipleAnswers newQuestion;
				
				newQuestion = new QuestionWithMultipleAnswers(
								rs.getString(1),
								QuestionTypes.MultiAnswer,
								rs.getString(2),
								rs.getLong(3),
								rs.getInt(4),
								rs.getInt(5),// this is for numAnswers nut it is not in constructor
								rs.getBoolean(6),
								null // TODO here should be arraylist of answers
							);
				
				questions.add(newQuestion);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
		
		return questions;
	}
	
	public void addQuestion(QuestionWithMultipleAnswers question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_MultipleAnswers(quiz_id, question_text, question_data, question_time_limit, score, num_answers, answers_ordered) value ("
				+ quiz_id + ", "
				+ question.getQuestiontext() + ", "
				+ question.getQuestiondescription() + ", "
				+ question.getQuestiontimelimit() + ", "
				+ question.getQuestionscore() + ", "
				+ question.getNumanswers() + ", "
				+ question.getAnswersordered()
				+ ");";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stm.executeQuery();
			
			ResultSet rs = stm.getGeneratedKeys();
			int question_id = rs.getInt("question_id");
			// TODO insert answers into its table.			
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			
			throw new Exception("Error durin insertion in DB");
			//e.printStackTrace();
		}
	}
}
