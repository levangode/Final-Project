package answers;

public class MultipleChoiceAnswer implements AnswerParent{
	private String answer_text;
	private boolean answer_correct;
	public MultipleChoiceAnswer(String answer_text, boolean answer_correct){
		this.answer_text=answer_text;
		this.answer_correct=answer_correct;
	}
	
	public String toString(){
		return "answer: "+answer_text+"\n"+"correct: "+answer_correct+"\n";
	}
}
