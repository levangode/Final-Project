package DBTest;

import java.util.List;

import org.junit.Test;

import DBAnswerControllers.DBBlankAnswer;
import DBAnswerControllers.DBMultipleChoiceAnswers;
import DBAnswerControllers.DBQuestionResponseAnswer;
import answers.Answer;
import answers.BlankAnswer;
import answers.MultipleChoiceAnswer;
//import answers.Answer;
import answers.ResponseAnswer;

public class AnswerTest {
//	@Test
	public void Test_ResponseAnswer_get(){
		DBQuestionResponseAnswer z = new DBQuestionResponseAnswer();
		
		List<ResponseAnswer> answers = z.retrieveAnswers(14);
		
		for(Answer tmpAns: answers){
			System.out.println(tmpAns);
		}
	}

//	@Test
	public void Test_BlankAnswer_get(){
		DBBlankAnswer z = new DBBlankAnswer();
		
		List<BlankAnswer> answers = z.retrieveAnswers(7);
		
		System.out.println(answers.isEmpty());
		for(Answer tmpAns: answers){
			System.out.println(tmpAns);
		}
	}
	
	@Test
	public void Test_MultipleChoiceAnswer_get(){
		DBMultipleChoiceAnswers z = new DBMultipleChoiceAnswers();
		
		List<MultipleChoiceAnswer> answers = z.retrieveAnswers(10);
		
		System.out.println(answers.isEmpty());
		for(Answer tmpAns: answers){
			System.out.println(tmpAns);
		}
		
	}
}
