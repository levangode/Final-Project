package questions;

import java.util.ArrayList;


import answers.AnswerParent;


public class FillTheBlankQuestion extends Question {

	public FillTheBlankQuestion(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, ArrayList<AnswerParent> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}
}
