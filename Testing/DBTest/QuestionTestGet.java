package DBTest;

import java.util.List;

import org.junit.Test;

import DBQuestionControllers.DBQuestionFillInTheBlanks;
import DBQuestionControllers.DBQuestionMultipleChoice;
import DBQuestionControllers.DBQuestionResponse;
import DBQuestionControllers.DBQuestionWithMultipleAnswers;
import answers.Answer;
import questions.FillTheBlankQuestion;
import questions.MultipleChoiceQuestion;
import questions.Question;
import questions.QuestionFactory;
import questions.QuestionResponse;
import questions.QuestionWithMultipleAnswers;

public class QuestionTestGet {
//	@Test
	public void Test_QuestionResponse_Get(){
		DBQuestionResponse z = new DBQuestionResponse();
		
		List<QuestionResponse> questions = z.retrieveQuestions(41);
		
		System.out.println(questions.isEmpty());
		
		for(Question q: questions){
			System.out.println(q);
			for(Answer a: q.getAnswers()){
				System.out.println(a);
			}
		}
		
	}
	
//	@Test
	public void Test_MultipleChoice_Get(){
		DBQuestionMultipleChoice z = new DBQuestionMultipleChoice();
		
		List<MultipleChoiceQuestion> questions = z.retrieveQuestions(27);
		
		System.out.println(questions.isEmpty());
		
		for(Question q: questions){
			System.out.println(q);
			for(Answer a: q.getAnswers()){
				System.out.println(a);
			}
		}
		
	}
	
	@Test
	public void Test_MultipleAnswers_Get(){
		DBQuestionWithMultipleAnswers z = new DBQuestionWithMultipleAnswers();
		
		List<QuestionWithMultipleAnswers> questions = z.retrieveQuestions(8);
		
		System.out.println(questions.isEmpty());
		
		for(Question q: questions){
			System.out.println(q);
			
			for(Answer a: q.getAnswers()){
				System.out.println(a);
			}
		}
		
	}
	
//	@Test
	public void Test_FillInTheBlanks_Get(){
		DBQuestionFillInTheBlanks z = new DBQuestionFillInTheBlanks();
		
		List<FillTheBlankQuestion> questions = z.retrieveQuestions(27);
		
		System.out.println(questions.isEmpty());
		
		for(Question q: questions){
			System.out.println(q);
			for(Answer a: q.getAnswers()){
				System.out.println(a);
			}
		}
		
	}
	
}
