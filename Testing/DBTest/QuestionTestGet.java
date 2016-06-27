package DBTest;

import java.util.List;

import org.junit.Test;

import DBQuestionControllers.DBQuestionResponse;
import questions.Question;
import questions.QuestionResponse;

public class QuestionTestGet {
	@Test
	public void Test_QuestionResponse_Get(){
		DBQuestionResponse z = new DBQuestionResponse();
		
		List<QuestionResponse> questions = z.retrieveQuestions(41);
		
		System.out.println(questions.isEmpty());
		
		for(Question q: questions){
			System.out.println(q);
		}
		
	}
	
}
