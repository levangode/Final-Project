package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBconnector;
import questions.MultipleChoiceQuestion;
import questions.QuestionResponse;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class DBQuestionMultipleChoice {
	
	private Connection connection;

	
	public DBQuestionMultipleChoice() {
		this.connection = new DBconnector().getConnection();
	}

	public DBQuestionMultipleChoice(Connection con) {
		this.connection = con;
	}
	
	public List<MultipleChoiceQuestion> retrieveQuestions(int quiz_id){
		List<MultipleChoiceQuestion> questions = new ArrayList<MultipleChoiceQuestion>();
		
		String query = " select question_text, question_data, question_time_limit, score, num_answers_display, num_answers_corrent from Questions_MultipleChoice where quiz_id = "
				+ quiz_id
				+ "; ";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				MultipleChoiceQuestion newQuestion;
				
				
				newQuestion = new MultipleChoiceQuestion(
								rs.getString(1),
								QuestionTypes.MultipleChoiceMultipleAnswer,
								rs.getString(2),
								rs.getLong(3),
								rs.getInt(4),
								rs.getInt(5),
								rs.getInt(6),
								null
								//TODO add answers controller
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
	
	public void addQuestion(MultipleChoiceQuestion question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_QuestionResponse(quiz_id, question_text, question_data, question_time_limit, score, num_answers_display, num_answers_corrent) value ("
				+ quiz_id + ", "
				+ question.getQuestiontext() + ", "
				+ question.getQuestiondescription() + ", "
				+ question.getQuestiontimelimit() + ", "
				+ question.getQuestionscore() + ", "
				+ question.getNumanswersdisplay() + ", "
				+ question.getNumanswerscorrect() + ", "
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
