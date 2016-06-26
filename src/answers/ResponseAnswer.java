package answers;

import DBAnswerControllers.DBQuestionResponseAnswer;
import database.DBconnector;

public class ResponseAnswer extends Answer {
	private String answer_text;

	public ResponseAnswer(String answer_text) {
		this.answer_text = answer_text;
	}

	public String toString() {
		return "answer: " + answer_text + "\n";
	}

	@Override
	public void addToDatabase(int question_id) {
		DBQuestionResponseAnswer db = new DBQuestionResponseAnswer(new DBconnector().getConnection());

		db.addAnswer(this, question_id);
	}

	public String getAnswerText() {
		return answer_text;
	}
}
