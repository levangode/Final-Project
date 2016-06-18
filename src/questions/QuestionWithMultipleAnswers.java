package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answers.AnswerParent;

public class QuestionWithMultipleAnswers extends QuestionParent{
	private boolean order;
	public QuestionWithMultipleAnswers(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, boolean order, ArrayList<AnswerParent> answers){
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
		this.order=order;
	}
	@Override
	public QuestionParent retrieveQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
