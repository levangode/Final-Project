package backend;

import java.util.ArrayList;

public class Question {
	private int question_id;
	private int quiz_id;
	private String question_type;
	private String question_description;
	private int question_time_limit;
	private String question_text;
	private ArrayList<Answer> answers;

	public Question(String question_text, int question_id, int quiz_id, String question_type,
			String question_description, int question_time_limit, ArrayList<Answer> answers) {
		this.question_id = question_id;
		this.answers = answers;
		this.quiz_id = quiz_id;
		this.question_text = question_text;
		this.question_type = question_type;
		this.question_description = question_description;
		this.question_time_limit = question_time_limit;
	}

	public String getQuestionText() {
		return question_text;
	}

	public int getQuestionTimeLimit() {
		return question_time_limit;
	}

	public String getQuestionDescription() {
		return question_description;
	}

	public String questionType() {
		return question_type;
	}

	public int getQuestionId() {
		return question_id;
	}

	public int getQuizId() {
		return quiz_id;
	}

}
