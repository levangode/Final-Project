package answers;

public class MultipleAnswer implements AnswerParent {
	private String answer_text;
	private int answer_num;
	public MultipleAnswer(String answer_text, int answer_num){
		this.answer_text=answer_text;
		this.answer_num=answer_num;
	}
}
