package questions;

import java.util.ArrayList;

import answers.Answer;

public class QuestionFactory {
	public static FillTheBlankQuestion getBlankQuestion(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new FillTheBlankQuestion(question_text, question_type, question_description, question_time_limit,
				question_score, answers);
	}

	public static MultipleChoiceQuestion getMultipleChoiceQuesion(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score, int answers_to_show,
			int answers_to_be_correct) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new MultipleChoiceQuestion(question_text, question_type, question_description, question_time_limit,
				question_score, answers_to_show, answers_to_be_correct, answers);
	}

	public static QuestionWithMultipleAnswers getQuestionWithMultipleAnswers(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score, int num_answers, boolean order) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new QuestionWithMultipleAnswers(question_text, question_type, question_description, question_time_limit,
				question_score, num_answers, order, answers);
	}

	public static QuestionResponse getQuestionResponse(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new QuestionResponse(question_text, question_type, question_description, question_time_limit,
				question_score, answers);
	}
}
