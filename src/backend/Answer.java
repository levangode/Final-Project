package backend;

public class Answer {
	private int answer_id;
	private int quiz_id;
	private String answer_text;
	private String answer_description;
	private boolean answer_correct;
	private String answer_type;

	public Answer(int answer_id, int quiz_id, String answer_text, String answer_description, boolean answer_correct,
			String answer_type) {
		this.answer_id = answer_id;
		this.quiz_id = quiz_id;
		this.answer_text = answer_text;
		this.answer_description = answer_description;
		this.answer_correct = answer_correct;
		this.answer_type = answer_type;
	}

	public String getAnswerType() {
		return answer_type;
	}

	public boolean getAnswerCorrect() {
		return answer_correct;
	}

	public String answerDescription() {
		return answer_description;
	}

	public String answerText() {
		return answer_text;
	}

	public int getQuizId() {
		return quiz_id;
	}

	public int getAnswerId() {
		return answer_id;
	}

}
