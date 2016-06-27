package answers;


import DBAnswerControllers.DBMultipleAnswers;
import database.DBconnector;

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

	
	public int getAnswernum(){
		return answer_num;
	}

	@Override
	public void addToDatabase(int question_id) {
		DBMultipleAnswers db = new DBMultipleAnswers(new DBconnector().getConnection());
		
		db.addAnswer(this, question_id);
		
	}

	@Override
	public String getAnswerText() {
		return answer_text;
	}
}
