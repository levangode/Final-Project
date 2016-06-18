package questions;

import java.util.ArrayList;

import answers.AnswerParent;

public class QuestionFactory {
	public static BlankQuestion getBlankQuestion(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score) {
		ArrayList<AnswerParent> answers = new ArrayList<AnswerParent>();
		return new BlankQuestion(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}
	public static ImageQuestion getImageQuestion(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score){
		ArrayList<AnswerParent> answers = new ArrayList<AnswerParent>();
		return new ImageQuestion(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}
	public static MultipleChoiceQuestion getMultipleChoiceQuesion(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score, int answers_to_show){
		ArrayList<AnswerParent> answers = new ArrayList<AnswerParent>();
		return new MultipleChoiceQuestion(question_text, question_type, question_description, question_time_limit, question_score, answers_to_show, answers);
	}
	
	public static QuestionWithMultipleAnswers getQuestionWithMultipleAnswers(String question_text, String question_type,
			String question_description, long question_time_limit, int question_score){
		ArrayList<AnswerParent> answers = new ArrayList<AnswerParent>();
		return new QuestionWithMultipleAnswers(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}
}
