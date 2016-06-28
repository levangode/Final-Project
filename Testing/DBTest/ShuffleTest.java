package DBTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import DBQuestionControllers.DBQuestionMultipleChoice;
import answers.Answer;
import answers.MultipleChoiceAnswer;
import questions.MultipleChoiceQuestion;

public class ShuffleTest {
	@Test
	public void shuffleTest(){
		List<Integer> l = new ArrayList<Integer>();
		for(int i=0; i<10; i++){
			l.add(i);
		}
		
		Collections.shuffle(l);
		
		System.out.println(l);
	}
	
	@Test
	public void getFormattedAnswerTest(){
		DBQuestionMultipleChoice z = new DBQuestionMultipleChoice();
		
		List<MultipleChoiceQuestion> p = z.retrieveQuestions(72);

		List<Answer> o = p.get(0).getFormattedAnswers();
		
		System.out.println(o);
		
	}
}
