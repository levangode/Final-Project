package answers;


public class AnswerFactory {
	public static ResponseAnswer getResponseAnswer(String answer_text){
		return new ResponseAnswer(answer_text);
	}
	public static BlankAnswer getBlankAnswer(String answer_text, int blank_position){
		return new BlankAnswer(answer_text, blank_position);
	}
	public static MultipleChoiceAnswer getMultipleChoiceAnswer(String answer_text, boolean answer_correct){
		return new MultipleChoiceAnswer(answer_text, answer_correct);
	}
	public static MultipleAnswer getMultipleAnswerAnswer(String answer_text, int answer_num){
		return new MultipleAnswer(answer_text, answer_num);
	}

}
