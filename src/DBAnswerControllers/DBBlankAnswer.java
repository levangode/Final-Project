package DBAnswerControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import answers.BlankAnswer;
import database.DBconnector;

public class DBBlankAnswer {
	private Connection connection;

	
	public DBBlankAnswer() {
		this.connection = new DBconnector().getConnection();
	}
	
	public DBBlankAnswer(Connection con) {
		this.connection = con;
	}
	
	public List<BlankAnswer> retrieveAnswers(int question_id){
		List<BlankAnswer> answers = new ArrayList<BlankAnswer>();
		
		String query = "select answer_text, blank_pos from Answers_FillInTheBlanks where question_id = "
					+ question_id + " ;";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				BlankAnswer newAnswer = new BlankAnswer(rs.getString(1), rs.getInt(2));
				
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
	
	public void addAnswer(BlankAnswer ans, int question_id){
		String query = "insert into Answers_FillInTheBlanks(answer_text, blank_pos, question_id) values("
				+ "'" + ans.getAnswerText() + "'"
				+ ", "
				+ ans.getBlankpos()
				+ ", "
				+ question_id
				+ ");";
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = connection.prepareStatement(query);
			stm.executeUpdate();
					
			
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
	
	}
	
	public void addAnswers(Collection<BlankAnswer> answers, int question_id){
		Iterator<BlankAnswer> it = answers.iterator();
		
		while(it.hasNext()){
			addAnswer(it.next(), question_id);
		}
		
	}

}
