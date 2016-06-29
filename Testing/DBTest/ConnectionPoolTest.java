package DBTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import DBConnector.Connector;
import answers.BlankAnswer;
import answers.ResponseAnswer;

public class ConnectionPoolTest {
	
	@Test
	public void basicPoolTest(){
		
		Connection con = null;
		
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<ResponseAnswer> answers = new ArrayList<ResponseAnswer>();
		
		String query = "select answer_text from Answers_QuestionResponse where question_id = "
					+ 1 + " ;";
		
		PreparedStatement stm;
		
		try{
			stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				ResponseAnswer newAnswer = new ResponseAnswer(rs.getString(1));
				
				answers.add(newAnswer);
			}
			
			Connector.returnConnection(con);
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
		
		System.out.println(answers);
		
		System.out.println("Test 1 end");
		
	}
	
	@Test
	public void PoolTest2(){
		Connection con = null;
		
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		List<BlankAnswer> answers = new ArrayList<BlankAnswer>();
		
		String query = "select answer_text, blank_pos from Answers_FillInTheBlanks where question_id = "
					+ 3 + " ;";
		
		PreparedStatement stm;
		
		System.out.println(query);
		
		try{
			stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				BlankAnswer newAnswer = new BlankAnswer(rs.getString(1), rs.getInt(2));
				
				answers.add(newAnswer);
			}
			
			System.out.println(con.getClass());
			
			con.close();
			
			Connector.returnConnection(con);
			
		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		}
		
		System.out.println(answers);
		
		System.out.println("Test2 end");
		
	}
	
}
