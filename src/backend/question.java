package backend;

import java.util.HashSet;

public class question {
	private String questionText;
	private String questionType;
	private HashSet<String> answers;
	private HashSet<String> correctAnswers;

	public question() {// better not use this one

	}

	public question(String questionText, String questionType, HashSet<String> answers, HashSet<String> correctAnswers) {
		this.questionText = questionText;
		this.questionType = questionType;
		this.answers = answers;
		this.correctAnswers = correctAnswers;
	}

	public void setQuestionText(String txt) {
		questionText = txt;
	}

	public void setQuestionType(String type) {
		questionType = type;
	}

	public void setAnswers(HashSet<String> answers) {
		this.answers = answers;
	}

	public void setCorrectAnswers(HashSet<String> correctAnswers) {
		this.correctAnswers = correctAnswers;

	}

	public HashSet<String> getAnswers() {
		return answers;
	}

	public HashSet<String> getCorrectAnswers() {
		return correctAnswers;
	}

	boolean isCorrectAnswer(String ans) {
		return correctAnswers.contains(ans);
	}

	public String getQuestionText() {
		return questionText;
	}
}
