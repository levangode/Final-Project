package DBQuestionControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBAnswerControllers.DBResponseAnswer;
import answers.Answer;
import database.DBconnector;
import questions.Question;
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
		
		String query = "select question_text, question_data, question_time_limit, score, question_id from Questions_QuestionResponse where quiz_id = "
				+ quiz_id
				+ ";";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				QuestionResponse newQuestion;
				
				ArrayList<Answer> answers = getAnswers(rs.getInt(5));
				
				newQuestion = new QuestionResponse(
								rs.getString(1), 
								QuestionTypes.QuestionResponse,
								rs.getString(2),
								rs.getInt(3),
								rs.getInt(4),
								answers
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
	
	private ArrayList<Answer> getAnswers(int id){
		ArrayList<Answer> res;
		
		DBResponseAnswer db = new DBResponseAnswer();
		
		res = new ArrayList<Answer>( db.retrieveAnswers(id) );
		
		return res;
	}
	
	public void addQuestion(QuestionResponse question, int quiz_id) throws Exception{
		String query = 
				"insert into Questions_QuestionResponse(quiz_id, question_text, question_data, question_time_limit, score) value ("
				+ quiz_id + ", "
				+ "'" + question.getQuestiontext() + "'" + ", "
				+ "'" + question.getQuestiondescription() + "'" + ", "
				+ question.getQuestiontimelimit() + ", "
				+ question.getQuestionscore()
				+ ");";
		
//		System.out.println(query);
		
		PreparedStatement stm;
		
		try{
			
			stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
//			System.out.println("11");
			
			stm.executeUpdate();
			
//			System.out.println("22");
			
			ResultSet rs = stm.getGeneratedKeys();
			int question_id = -1;
			while(rs.next()){
				question_id = rs.getInt(1);
			}
			
			for(Answer cur: question.getAnswers()){
//				System.out.println("processing answer " + cur + " id = " + question_id);
				cur.addToDatabase(question_id);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
			throw new Exception("Error durin insertion in DB");
			//e.printStackTrace();
		}
	}
	
}
