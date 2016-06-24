package questions;

import java.util.ArrayList;


import answers.Answer;

public abstract class Question {

	private String question_type;
	private String question_data;
	private String question_text;
	private long question_time_limit;
	private int question_score;
	private ArrayList<Answer> answers;

	public Question(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, ArrayList<Answer> answers) {
		this.question_type = question_type;
		this.question_text = question_text;
		this.question_data = question_description;
		this.question_time_limit = question_time_limit;
		this.question_score = question_score;
		this.answers = answers;
	}
	
	public String toString() {
		String result = "" + "text: " + question_text + "\n" + "description: " + question_data + "\n" + "type: "
				+ question_type + "\n" + "limit: " + question_time_limit + "\n" + "score: " + question_score + "\n";
		return result;
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
	}

	public String getQuestiontype() {
		return question_type;
	}
	
	public int getQuestionscore(){
		return question_score;
	}

	public void setQuestiontype(String question_type) {
		this.question_type = question_type;
	}

	public String getQuestiondescription() {
		return question_data;
	}

	public void setQuestiondescription(String question_description) {
		this.question_data = question_description;
	}

	public long getQuestiontimelimit() {
		return question_time_limit;
	}

	public void setQuestiontimelimit(long question_time_limit) {
		this.question_time_limit = question_time_limit;
	}

	public String getQuestiontext() {
		return question_text;
	}

	public void setQuestiontext(String question_text) {
		this.question_text = question_text;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

}
