package StructureClassesTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import answers.Answer;
import answers.AnswerFactory;
import answers.BlankAnswer;
import answers.MultipleAnswer;
import answers.MultipleChoiceAnswer;
import answers.ResponseAnswer;


public class AnswerTest {
	@Test
	public void fillTheBlankBasic1(){
		String answer_text = "blank1";
		int blank_position = 1;
		Answer a = AnswerFactory.getBlankAnswer(answer_text, blank_position);
		assertEquals("blank1", a.getAnswerText());
		BlankAnswer b = AnswerFactory.getBlankAnswer(answer_text, blank_position);
		assertEquals("blank1", b.getAnswerText());
		assertEquals(1, b.getBlankpos());
	}
	
	@Test
	public void multipleAnswerBasic1(){
		String answer_text = "mult1";
		int answer_num = 1;
		Answer a = AnswerFactory.getMultipleAnswerAnswer(answer_text, answer_num);
		assertEquals("mult1", a.getAnswerText());
		MultipleAnswer b = AnswerFactory.getMultipleAnswerAnswer(answer_text, answer_num);
		assertEquals("mult1", b.getAnswerText());
		assertEquals(1, b.getAnswernum());
	}
	
	@Test
	public void multipleChoiceBasic1(){
		String answer_text="choic1";
		boolean answer_correct=false;
		Answer a = AnswerFactory.getMultipleChoiceAnswer(answer_text, answer_correct);
		assertEquals("choic1", a.getAnswerText());
		MultipleChoiceAnswer b = AnswerFactory.getMultipleChoiceAnswer(answer_text, answer_correct);
		assertEquals("choic1", b.getAnswerText());
		assertEquals(false, b.getAnswercorrect());
	}
	
	@Test
	public void ResponseAnswerBasic1(){
		String answer_text="resp1";
		Answer a = AnswerFactory.getResponseAnswer(answer_text);
		assertEquals("resp1", a.getAnswerText());
		ResponseAnswer b = AnswerFactory.getResponseAnswer(answer_text);
		assertEquals("resp1", b.getAnswerText());
		
		
	}
	
	
}
