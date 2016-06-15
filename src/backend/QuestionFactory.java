package backend;

import java.util.ArrayList;

public class QuestionFactory {
	public static Question getQuestion(String question_text, String question_type,
			String question_description, long question_time_limit){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		return new Question(question_text, question_type, question_description, question_time_limit, answers);
	}
}
