package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answers.AnswerParent;

public class MultipleChoiceQuestion extends QuestionParent{
	private int answers_to_show;
	private int answers_to_be_correct;
	public MultipleChoiceQuestion(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, int answers_to_show, ArrayList<AnswerParent> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
		this.answers_to_show=answers_to_show;
		this.answers_to_be_correct=answers_to_be_correct;
	}
	@Override
	public QuestionParent retrieveQuestion(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
}
