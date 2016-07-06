package DBAnswerControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import DBConnector.Connector;
import answers.MultipleAnswer;
import database.DBconnector;

public class DBMultipleAnswers {
	private Connection connection;

	
	public DBMultipleAnswers() {
		this.connection = new DBconnector().getConnection();
	}
	
	public DBMultipleAnswers(Connection con) {
		this.connection = con;
	}
	
	public List<MultipleAnswer> retrieveAnswers(int question_id){
		List<MultipleAnswer> answers = new ArrayList<MultipleAnswer>();
		
		String query = "select answer_text, answer_num from Answers_MultipleAnswers where question_id = "
				+ question_id
				+ " order by answer_num ASC;";
					
		PreparedStatement stm;
		
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				MultipleAnswer newAnswer = new MultipleAnswer(rs.getString(1), rs.getInt(2));				
				
				answers.add(newAnswer);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
		
		return answers;
	}
	
	public void addAnswer(MultipleAnswer ans, int question_id){
		String query = "insert into Answers_MultipleAnswers(answer_text, answer_num, question_id) values("
				+ "'" + ans.getAnswerText() + "'"
				+ ", "
				+ ans.getAnswernum()
				+ ", "
				+ question_id
				+ ");";
		PreparedStatement stm;
		
				
		try{
			stm = connection.prepareStatement(query);
			stm.executeUpdate();
			
		
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}
	
	}
	
	public void addAnswers(Collection<MultipleAnswer> answers, int question_id){
		Iterator<MultipleAnswer> it = answers.iterator();
		
		while(it.hasNext()){
			addAnswer(it.next(), question_id);
		}
		
	}

}