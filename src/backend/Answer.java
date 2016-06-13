package backend;

public class Answer {
	private long answer_id;
	private long quiz_id;
	private String answer_text;
	private String answer_description;
	private boolean answer_correct;
	private String answer_type;
	private long question_id;

	public Answer(long answer_id, long quiz_id, String answer_text, String answer_description, boolean answer_correct,
			String answer_type, long question_id) {
		this.answer_id = answer_id;
		this.quiz_id = quiz_id;
		this.answer_text = answer_text;
		this.answer_description = answer_description;
		this.answer_correct = answer_correct;
		this.answer_type = answer_type;
		this.question_id = question_id;
	}

	public Answer() {

	}

	public long getQuestionid() {
		return question_id;
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

	public String getAnswerDescription() {
		return answer_description;
	}

	public String getAnswerText() {
		return answer_text;
	}

	public long getQuizId() {
		return quiz_id;
	}

	public long getAnswerId() {
		return answer_id;
	}

}
