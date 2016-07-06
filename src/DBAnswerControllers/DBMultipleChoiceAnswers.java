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
import database.DBconnector;
import answers.BlankAnswer;
import answers.MultipleChoiceAnswer;

public class DBMultipleChoiceAnswers {
	private Connection connection;

	
	public DBMultipleChoiceAnswers() {
		this.connection = new DBconnector().getConnection();
	}
	
	public DBMultipleChoiceAnswers(Connection con) {
		this.connection = con;
	}
	
	public List<MultipleChoiceAnswer> retrieveAnswers(int answer_id){
		List<MultipleChoiceAnswer> answers = new ArrayList<MultipleChoiceAnswer>();
		
		String query = "select answer_text, answer_correct from Answers_MultipleChoice where question_id = "
					+ answer_id + " ;";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				MultipleChoiceAnswer newAnswer = new MultipleChoiceAnswer(rs.getString(1), rs.getBoolean(2));
				
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
	
	public void addAnswer(MultipleChoiceAnswer ans, int question_id){
		String query = "insert into Answers_MultipleChoice(answer_text, answer_correct, question_id) values("
				+ "'" + ans.getAnswerText() + "'"
				+ ", "
				+ ans.getAnswercorrect()
				+ ", "
				+ question_id
				+ ");";
		PreparedStatement stm;
		
		try{
			System.out.println(query);
			stm = connection.prepareStatement(query);
			stm.executeUpdate();
					
			
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		} finally{
			Connector.returnConnection(connection);
		}
	
	}
	
	public void addAnswers(Collection<MultipleChoiceAnswer> answers, int question_id){
		Iterator<MultipleChoiceAnswer> it = answers.iterator();
		
		while(it.hasNext()){
			addAnswer(it.next(), question_id);
		}
		
	}

}
