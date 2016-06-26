package answers;

public class MultipleAnswer extends Answer {
	private String answer_text;
	private int answer_num;
	public MultipleAnswer(String answer_text, int answer_num){
		this.answer_text=answer_text;
		this.answer_num=answer_num;
	}
	
	public String toString(){
		return "answer: "+answer_text+"\n"+"num: "+answer_num+"\n";
	}
	@Override
	public String getAnswerText(){
		return answer_text;
	}
	
	public int getAnswernum(){
		return answer_num;
	}
}
