package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import answers.Answer;
import database.DBconnector;
import questions.QuestionResponse;
import questions.QuestionTypes;

public class DBQuestionResponse {
	
	private Connection connection;

	
	public DBQuestionResponse() {
		this.connection = new DBconnector().getConnection();
	}
	public DBQuestionResponse(Connection DBconnection) {
		this.connection = DBconnection;
	}
	
	public List<QuestionResponse> retrieveQuestions(int quiz_id){
		List<QuestionResponse> questions = new ArrayList<QuestionResponse>();
		
		String query = " select question_text, question_data, question_time_limit, score from Questions_QuestionResponse where quiz_id = "
				+ quiz_id
				+ "; ";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				QuestionResponse newQuestion;
				
				// TODO add code for answers
				newQuestion = new QuestionResponse(
								rs.getString(1), 
								QuestionTypes.QuestionResponse,
								rs.getString(2),
								rs.getInt(3),
								rs.getInt(4),
								null
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
	
	public void addQuestion(QuestionResponse question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_QuestionResponse(quiz_id, question_text, question_data, question_time_limit, score) value ("
				+ quiz_id + ", "
				+ question.getQuestiontext() + ", "
				+ question.getQuestiondescription() + ", "
				+ question.getQuestiontimelimit() + ", "
				+ question.getQuestionscore()
				+ ");";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stm.executeQuery();
			
			ResultSet rs = stm.getGeneratedKeys();
			int question_id = rs.getInt("question_id");
			
			for(Answer cur: question.getAnswers()){
				cur.addToDatabase(question_id);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			
			throw new Exception("Error durin insertion in DB");
			//e.printStackTrace();
		}
	}
	
}
