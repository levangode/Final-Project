package DBAnswerControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import answers.ResponseAnswer;
import database.DBconnector;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class DBQuestionResponseAnswer {
	private Connection connection;

	
	public DBQuestionResponseAnswer() {
		this.connection = new DBconnector().getConnection();
	}
	
	public DBQuestionResponseAnswer(Connection con) {
		this.connection = con;
	}
	
	public List<ResponseAnswer> retrieveAnswers(int answer_id){
		List<ResponseAnswer> answers = new ArrayList<ResponseAnswer>();
		
		String query = "select answer_text from answers_questionresponse where question_id = "
					+ answer_id + " ;";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				ResponseAnswer newAnswer = new ResponseAnswer(rs.getString(1));
				
				answers.add(newAnswer);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
		
		return answers;
	}
	
	public void addAnswer(ResponseAnswer ans, int question_id){
		String query = "insert into answers_questionresponse(answer_text, question_id) values("
				+ ans
				+ ", "
				+ question_id
				+ ");";
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			stm.executeQuery();
			
		
		
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
	
	}
	

}
