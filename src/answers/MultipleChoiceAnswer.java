package answers;

public class MultipleChoiceAnswer extends Answer {
	private String answer_text;
	private boolean answer_correct;

	public MultipleChoiceAnswer(String answer_text, boolean answer_correct) {
		this.answer_text = answer_text;
		this.answer_correct = answer_correct;
	}

	public String toString() {
		return "answer: " + answer_text + "\n" + "correct: " + answer_correct + "\n";
	}

	@Override
	public String getAnswerText() {
		return answer_text;
	}

	public String getHtml(int questionNum, int answerNum) {
		String html = "";
		String type = "type='checkbox' ";
		String name = "name='q" + questionNum + "' ";
		String value = "value='" + answerNum + "' ";
		String id = "id='q" + questionNum + answerNum + "' ";
		html += "<input " + type + name + value + id + "><label for=" + id + ">" + getAnswerText() + "</label><br/>";
		return html;
	}

	public boolean getAnswercorrect() {
		return answer_correct;
	}

}
