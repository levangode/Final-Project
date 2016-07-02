package questions;

import java.util.ArrayList;

import answers.Answer;

public class QuestionFactory {
	public static FillTheBlankQuestion getBlankQuestion(String question_text, String question_type,
			String question_description, int question_score, int question_number) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new FillTheBlankQuestion(question_text, question_type, question_description, 
				question_score, answers, question_number);
	}

	public static MultipleChoiceQuestion getMultipleChoiceQuesion(String question_text, String question_type,
			String question_description,  int question_score, int answers_to_show,
			int answers_to_be_correct, int question_number) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new MultipleChoiceQuestion(question_text, question_type, question_description, 
				question_score, answers_to_show, answers_to_be_correct, answers, question_number);
	}

	public static QuestionWithMultipleAnswers getQuestionWithMultipleAnswers(String question_text, String question_type,
			String question_description,  int question_score, int num_answers, boolean order, int question_number) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new QuestionWithMultipleAnswers(question_text, question_type, question_description, 
				question_score, num_answers, order, answers, question_number);
	}

	public static QuestionResponse getQuestionResponse(String question_text, String question_type,
			String question_description,  int question_score, int question_number) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new QuestionResponse(question_text, question_type, question_description, 
				question_score, answers, question_number);
	}
}
