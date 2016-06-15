package backend;

public class Answer {
	private String answer_text;
	private String answer_description;
	private boolean answer_correct;

	public Answer(String answer_text, String answer_description, boolean answer_correct) {
		this.answer_text = answer_text;
		this.answer_description = answer_description;
		this.answer_correct = answer_correct;
	}



	public boolean getAnswerCorrect() {
		return answer_correct;
	}


	public String getAnswerDescription() {
		return answer_description;
	}

	public String getAnswerText() {
		return answer_text;
	}


}
