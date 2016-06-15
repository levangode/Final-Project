package backend;

public class AnswerFactory {
	public static Answer getAnswer(String answer_text, String answer_description, boolean answer_correct,
			String answer_type){
		return new Answer(answer_text, answer_description, answer_correct);
	}

}
