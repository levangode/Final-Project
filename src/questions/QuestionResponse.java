package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answers.AnswerParent;

public class QuestionResponse extends QuestionParent{

	public QuestionResponse(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, ArrayList<AnswerParent> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}

	@Override
	public QuestionParent retrieveQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
