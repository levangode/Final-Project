package answers;

import DBAnswerControllers.DBBlankAnswer;
import database.DBconnector;

public class BlankAnswer extends Answer {
	private String answer_text;
	private int blank_position;

	public BlankAnswer(String answer_text, int blank_position) {
		this.answer_text = answer_text;
		this.blank_position = blank_position;
	}

	public String toString() {
		return "answer: " + answer_text + "\n" + "position: " + blank_position + "\n";
	}

	public int getBlankpos() {
		return blank_position;
	}

	public String getAnswerText() {
		return answer_text;
	}

	@Override
	public void addToDatabase(int question_id) {
		DBBlankAnswer db = new DBBlankAnswer(new DBconnector().getConnection());

		db.addAnswer(this, question_id);
	}

}
