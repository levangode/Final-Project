package DBTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import answers.Answer;
import answers.ResponseAnswer;
import database.DBconnector;
import questions.Question;
import questions.QuestionResponse;
import questions.QuestionTypes;

public class QuestionTest {
	
	@Test
	public void Test1(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		for(int i=0; i<5; i++){
			answers.add(new ResponseAnswer("" + i));
		}
		
		System.out.println(answers.size());
		
		Question z = new QuestionResponse(
					"zzz waht is your name?",
					QuestionTypes.QuestionResponse,
					"zzz Best Question ever",
					1, 
					1,
					answers
				);
		
		try {
			z.addToDatabase(27);
		} catch (Exception e) {
			System.out.println("Bozeboooo");
			e.printStackTrace();
		}
	}
	
	//@Test
	public void lowLevelQueryTest(){
		Connection connection = new DBconnector().getConnection();
		
		try{
			String query = "select * from Questions_QuestionResponse where 1;";
			
			PreparedStatement stm = connection.prepareStatement(query);//, Statement.RETURN_GENERATED_KEYS);
			
			System.out.println("11");
			
			ResultSet rs = stm.executeQuery();
			
			System.out.println("22");
			
			connection.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//@Test
	public void lowLevelUpdateTest(){
		Connection connection = new DBconnector().getConnection();
		
		try{
			String query = "insert into Questions_QuestionResponse(quiz_id, question_text, question_data, question_time_limit, score) value (27, 'waht is your name?', 'Best Question ever', 1, 1);";
			
			PreparedStatement stm = connection.prepareStatement(query);//, Statement.RETURN_GENERATED_KEYS);
			
			System.out.println("11");
			
			stm.executeUpdate();
			
			System.out.println("22");
//			
//			ResultSet rs = stm.getGeneratedKeys();
//			int question_id = rs.getInt("question_id");
//			
//			for(Answer cur: question.getAnswers()){
//				System.out.println("processing answer " + cur);
//		//		cur.addToDatabase(question_id);
//			}
			
			connection.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
