package questions;

import java.util.ArrayList;


import answers.Answer;


public class FillTheBlankQuestion extends Question {

	public FillTheBlankQuestion(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, ArrayList<Answer> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}
}
