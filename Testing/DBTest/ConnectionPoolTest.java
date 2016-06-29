package DBTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import DBConnector.Connector;
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
					+ 2 + " ;";
		
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
		
	}
}
