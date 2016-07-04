package StructureClassesTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import answers.Answer;
import answers.AnswerFactory;
import answers.MultipleChoiceAnswer;
import questions.FillTheBlankQuestion;
import questions.MultipleChoiceQuestion;
import questions.Question;
import questions.QuestionFactory;
import questions.QuestionResponse;
import questions.QuestionWithMultipleAnswers;

public class QuestionTest {

	@Test
	public void FillTheBlank1() {
		String question_text = "blankQ1";
		String question_type = "blank";
		String question_description = "descr1";
		int question_score = 10;
		int question_number = 1;
		FillTheBlankQuestion a = QuestionFactory.getBlankQuestion(question_text, question_type, question_description,
				question_score, question_number);
		assertEquals("blankQ1", a.getQuestiontext());
		assertEquals("blank", a.getQuestiontype());
		assertEquals("descr1", a.getQuestiondescription());
		assertEquals(10, a.getQuestionscore());
		assertEquals(1, a.getQuestionnumber());
	}

	@Test
	public void FillTheBlank2() {
		String question_text = "blankQ1";
		String question_type = "blank";
		String question_description = "descr1";
		int question_score = 10;
		int question_number = 1;
		FillTheBlankQuestion a = QuestionFactory.getBlankQuestion(question_text, question_type, question_description,
				question_score, question_number);
		String answer_text = "blank1";
		int blank_position = 1;
		Answer b = AnswerFactory.getBlankAnswer(answer_text, blank_position);
		a.addAnswer(b);
		assertEquals(1, a.getAnswerNum());
		a.addAnswer(b);
		assertEquals(2, a.getAnswerNum());
	}

	@Test
	public void MultipleChoice1() {
		String question_text = "multiChoice1";
		String question_type = "multiChoice";
		String question_description = "descr";
		int question_score = 10;
		int answers_to_show = 3;
		int answers_to_be_correct = 2;
		int question_number = 1;
		MultipleChoiceQuestion a = QuestionFactory.getMultipleChoiceQuesion(question_text, question_type,
				question_description, question_score, answers_to_show, answers_to_be_correct, question_number);
		assertEquals("multiChoice1", a.getQuestiontext());
		assertEquals("multiChoice", a.getQuestiontype());
		assertEquals("descr", a.getQuestiondescription());
		assertEquals(10, a.getQuestionscore());
		assertEquals(1, a.getQuestionnumber());
		assertEquals(3, a.getNumanswersdisplay());
		assertEquals(2, a.getNumanswerscorrect());
	}

	@Test
	public void MultipleChoice2() {
		String question_text = "multiChoice1";
		String question_type = "multiChoice";
		String question_description = "descr";
		int question_score = 10;
		int answers_to_show = 3;
		int answers_to_be_correct = 2;
		int question_number = 1;
		MultipleChoiceQuestion a = QuestionFactory.getMultipleChoiceQuesion(question_text, question_type,
				question_description, question_score, answers_to_show, answers_to_be_correct, question_number);
		String answer_text = "choic1";
		boolean answer_correct = false;
		Answer b = AnswerFactory.getMultipleChoiceAnswer(answer_text, answer_correct);
		a.addAnswer(b);
		assertEquals(1, a.getAnswerNum());
		a.addAnswer(b);
		assertEquals(2, a.getAnswerNum());

	}

	@Test
	public void Response1() {
		String question_text = "respQ1";
		String question_type = "response";
		String question_description = "descr";
		int question_score = 10;
		int question_number = 1;
		QuestionResponse a = QuestionFactory.getQuestionResponse(question_text, question_type, question_description,
				question_score, question_number);
		assertEquals("respQ1", a.getQuestiontext());
		assertEquals("response", a.getQuestiontype());
		assertEquals("descr", a.getQuestiondescription());
		assertEquals(10, a.getQuestionscore());
		assertEquals(1, a.getQuestionnumber());

	}

	@Test
	public void Response2() {
		String question_text = "respQ1";
		String question_type = "response";
		String question_description = "descr";
		int question_score = 10;
		int question_number = 1;
		QuestionResponse a = QuestionFactory.getQuestionResponse(question_text, question_type, question_description,
				question_score, question_number);
		String answer_text = "resp1";
		Answer b = AnswerFactory.getResponseAnswer(answer_text);
		a.addAnswer(b);
		assertEquals(1, a.getAnswerNum());
		a.addAnswer(b);
		assertEquals(2, a.getAnswerNum());
	}

	@Test
	public void multiAns() {
		String question_text = "mult1";
		String question_type = "mult";
		String question_description = "descr";
		int num_answers = 3;
		boolean order = false;
		int question_score = 10;
		int question_number = 1;
		QuestionWithMultipleAnswers a = QuestionFactory.getQuestionWithMultipleAnswers(question_text, question_type,
				question_description, question_score, num_answers, order, question_number);
		assertEquals("mult1", a.getQuestiontext());
		assertEquals("mult", a.getQuestiontype());
		assertEquals("descr", a.getQuestiondescription());
		assertEquals(10, a.getQuestionscore());
		assertEquals(1, a.getQuestionnumber());
		assertEquals(false, a.getAnswersordered());
		assertEquals(3, a.getNumanswers());

	}

	@Test
	public void multiAns2() {
		String question_text = "mult1";
		String question_type = "mult";
		String question_description = "descr";
		int num_answers = 3;
		boolean order = false;
		int question_score = 10;
		int question_number = 1;
		QuestionWithMultipleAnswers a = QuestionFactory.getQuestionWithMultipleAnswers(question_text, question_type,
				question_description, question_score, num_answers, order, question_number);
		String answer_text = "mult1";
		int answer_num = 1;
		Answer b = AnswerFactory.getMultipleAnswerAnswer(answer_text, answer_num);
		a.addAnswer(b);
		assertEquals(1, a.getAnswerNum());
		a.addAnswer(b);
		assertEquals(2, a.getAnswerNum());

	}
}
