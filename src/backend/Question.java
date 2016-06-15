package backend;

import java.util.ArrayList;

public class Question {
	private String question_type;
	private String question_description;
	private String question_text;
	private long question_time_limit;
	private ArrayList<Answer> answers;

	public Question(String question_text, String question_type,
			String question_description, long question_time_limit, ArrayList<Answer> answers) {
		this.question_type=question_type;
		this.question_text=question_text;
		this.question_description=question_description;
		this.question_time_limit=question_time_limit;
		this.answers=answers;
		
	}
	
	public void addAnswer(Answer answer){
		answers.add(answer);
	}

	public String getQuestiontype() {
		return question_type;
	}

	public void setQuestiontype(String question_type) {
		this.question_type = question_type;
	}

	public String getQuestiondescription() {
		return question_description;
	}

	public void setQuestiondescription(String question_description) {
		this.question_description = question_description;
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
