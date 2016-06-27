package DBTest;

import org.junit.Test;

import DBQuizControllers.DBQuizController;
import backend.Quiz;

public class QuizTest {
	
	@Test
	public void QuizTest1(){
		DBQuizController db = new DBQuizController();
		
		Quiz z = db.getQuiz(7);
		
		System.out.println(z);
		//System.out.println(z.getQuiz_name());
	}

	@Test
	public void QuizTest2(){
		DBQuizController db = new DBQuizController();
		
		Quiz z = db.getQuiz(27);
		
		System.out.println(z);
		//System.out.println(z.getQuiz_name());
	}
	
}
