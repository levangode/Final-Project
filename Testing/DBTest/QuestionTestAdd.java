package DBTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import answers.Answer;
import answers.BlankAnswer;
import answers.MultipleAnswer;
import answers.MultipleChoiceAnswer;
import answers.ResponseAnswer;
import database.DBconnector;
import questions.FillTheBlankQuestion;
import questions.MultipleChoiceQuestion;
import questions.Question;
import questions.QuestionFactory;
import questions.QuestionResponse;
import questions.QuestionTypes;
import questions.QuestionWithMultipleAnswers;

public class QuestionTestAdd {
	
//	@Test
	public void Test_QuestionResponse_add(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		for(int i=0; i<5; i++){
			answers.add(new ResponseAnswer("zanswer " + i));
		}
		
		System.out.println(answers.size());
		
		Question z = new QuestionResponse(
					"zzz waht is your name?",
					QuestionTypes.QuestionResponse,
					"zzz Best Question ever",
					1, 
					1,
					answers,
					5
				);
		
		try {
			z.addToDatabase(1);
		} catch (Exception e) {
			System.out.println("Bozeboooo");
			e.printStackTrace();
		}
	}
	
	
//	@Test
	public void Test_multpleChoice_add(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		for(int i=0; i<5; i++){
			answers.add(new MultipleChoiceAnswer("ans " + i, false));
		}
		
		answers.add(new MultipleChoiceAnswer("ans " + 99, true));
		
		System.out.println(answers.size());
		
		Question z = new MultipleChoiceQuestion("how much is 5 + 5?", QuestionTypes.MultipleChoiceMultipleAnswer, "", 1, 1, 5, 1, answers, 7);
		
		try {
			z.addToDatabase(1);
		} catch (Exception e) {
			System.out.println("Bozeboooo");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void Test_MultipleAnswers_add(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		for(int i=1; i<5; i++){
			answers.add(new MultipleAnswer("its: " + i, i));
		}
		
		answers.add(new MultipleAnswer("its: " + "5", 5));
		
		for(Answer ans: answers){
			System.out.println(ans.getAnswerText());
		}
		
		System.out.println(answers.size());
		
		Question z = new QuestionWithMultipleAnswers("how much is 5 + 0?", QuestionTypes.MultiAnswer, "", 1, 1, 5, false, answers, 7);
		
		try {
			z.addToDatabase(27);
		} catch (Exception e) {
			System.out.println("Bozeboooo");
			e.printStackTrace();
		}
	}
	
//	@Test
	public void Test_fillInTheBlanks_add(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		answers.add(new BlankAnswer("cow", 1));
		answers.add(new BlankAnswer("sheep", 2));
		answers.add(new BlankAnswer("ox", 3));
		answers.add(new BlankAnswer("squrrel", 4));
		answers.add(new BlankAnswer("unicorn", 5));
		
		Question z = new FillTheBlankQuestion("the prettiest animal is ? others are ? ? ? ?", QuestionTypes.FillInTheBlanks, "", 1, 5, answers, 8);
		
		try{
			z.addToDatabase(1);
		} catch (SQLException e){
			System.out.println("sqlError!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Fill in the blanks error");
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
