package DBAnswerControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import answers.MultipleChoiceAnswer;
import answers.ResponseAnswer;
import database.DBconnector;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class DBResponseAnswer {
	private Connection connection;

	
	public DBResponseAnswer() {
		this.connection = new DBconnector().getConnection();
	}
	
	public DBResponseAnswer(Connection con) {
		this.connection = con;
	}
	
	public List<ResponseAnswer> retrieveAnswers(int question_id){
		List<ResponseAnswer> answers = new ArrayList<ResponseAnswer>();
		
		String query = "select answer_text from Answers_QuestionResponse where question_id = "
					+ question_id + " ;";
		
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				ResponseAnswer newAnswer = new ResponseAnswer(rs.getString(1));
				
				answers.add(newAnswer);
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
		
		return answers;
	}
	
	public void addAnswer(ResponseAnswer ans, int question_id){
		String query = "insert into Answers_QuestionResponse(answer_text, question_id) values("
				+ "'" + ans.getAnswerText() + "'"
				+ ", "
				+ question_id
				+ ");";
		PreparedStatement stm;
		
		try{
			stm = connection.prepareStatement(query);
			stm.executeUpdate();
			
		
		
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
	
	}
	
	public void addAnswers(Collection<ResponseAnswer> answers, int question_id){
		Iterator<ResponseAnswer> it = answers.iterator();
		
		while(it.hasNext()){
			addAnswer(it.next(), question_id);
		}
		
	}
	

}
