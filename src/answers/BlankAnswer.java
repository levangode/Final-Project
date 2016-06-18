package answers;

public class BlankAnswer implements AnswerParent{
	private String answer_text;
	private int blank_position;
	public BlankAnswer(String answer_text, int blank_position){
		this.answer_text=answer_text;
		this.blank_position=blank_position;
	}
	
}
