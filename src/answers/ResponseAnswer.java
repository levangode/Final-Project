package answers;

public class ResponseAnswer implements AnswerParent{
	private String answer_text;
	public ResponseAnswer(String answer_text){
		this.answer_text=answer_text;
	}
	public String toString(){
		return "answer: "+answer_text+"\n";
	}
}