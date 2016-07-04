package StructureClassesTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.Quiz;
import backend.QuizFactory;
import questions.Question;
import questions.QuestionFactory;

public class QuizTest {
	@Test
	public void basicTest1() {
		String quiz_name = "name1";
		String quiz_description = "descr1";
		String quiz_author = "auth1";
		int quiz_likes = 1;
		String quiz_category = "cat1";
		String quiz_difficulty = "diff1";
		int times_taken = 1;
		boolean displayMultiplePages = true;
		boolean immediateCorrection = true;
		boolean randomQuestions = true;
		int time_limit = 1;
		Quiz a = QuizFactory.getQuiz(quiz_name, quiz_description, quiz_author, quiz_likes, quiz_category,
				quiz_difficulty, times_taken, displayMultiplePages, immediateCorrection, randomQuestions, time_limit);
		assertEquals("name1", a.getQuiz_name());
		assertEquals("descr1", a.getQuiz_description());
		assertEquals("auth1", a.getQuiz_author());
		assertEquals(1, a.getQuiz_likes());
		assertEquals("cat1", a.getQuiz_category());
		assertEquals("diff1", a.getQuiz_difficulty());
		assertEquals(1, a.getTimes_taken());
		assertEquals(true, a.isDisplayMultiplePages());
		assertEquals(true, a.isImmediateCorrection());
		assertEquals(true, a.isRandomQuestions());
		assertEquals(1, a.getTime_limit());
	}

	@Test
	public void basicTest17() {
		String quiz_name = "name17";
		String quiz_description = "descr17";
		String quiz_author = "auth17";
		int quiz_likes = 17;
		String quiz_category = "cat17";
		String quiz_difficulty = "diff17";
		int times_taken = 17;
		boolean displayMultiplePages = true;
		boolean immediateCorrection = true;
		boolean randomQuestions = true;
		int time_limit = 17;
		Quiz a = QuizFactory.getQuiz(quiz_name, quiz_description, quiz_author, quiz_likes, quiz_category,
				quiz_difficulty, times_taken, displayMultiplePages, immediateCorrection, randomQuestions, time_limit);
		assertEquals("name17", a.getQuiz_name());
		assertEquals("descr17", a.getQuiz_description());
		assertEquals("auth17", a.getQuiz_author());
		assertEquals(17, a.getQuiz_likes());
		assertEquals("cat17", a.getQuiz_category());
		assertEquals("diff17", a.getQuiz_difficulty());
		assertEquals(17, a.getTimes_taken());
		assertEquals(true, a.isDisplayMultiplePages());
		assertEquals(true, a.isImmediateCorrection());
		assertEquals(true, a.isRandomQuestions());
		assertEquals(17, a.getTime_limit());
	}

	@Test
	public void functionalTest1() {
		String quiz_name = "name1";
		String quiz_description = "descr1";
		String quiz_author = "auth1";
		int quiz_likes = 1;
		String quiz_category = "cat1";
		String quiz_difficulty = "diff1";
		int times_taken = 1;
		boolean displayMultiplePages = true;
		boolean immediateCorrection = true;
		boolean randomQuestions = true;
		int time_limit = 1;
		Quiz a = QuizFactory.getQuiz(quiz_name, quiz_description, quiz_author, quiz_likes, quiz_category,
				quiz_difficulty, times_taken, displayMultiplePages, immediateCorrection, randomQuestions, time_limit);
		Question b=QuestionFactory.getBlankQuestion("", "", "", 0, 0);
		assertEquals(0, a.getQuestions_num());
		a.addQuestionWithoutNum(b);
		assertEquals(1, a.getQuestions_num());
		a.addQuestion(b, 1);
		assertEquals(1, a.getQuestions_num());
		a.addQuestion(b, 2);
		assertEquals(2, a.getQuestions_num());
		a.addQuestion(b, 2);
		assertEquals(2, a.getQuestions_num());
		
	}
}
