package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import answers.Answer;


public abstract class Question implements PrintableQuestion, DatabaseAddable, Comparable {

	private String question_type;
	private String question_data;
	private String question_text;
	private int question_score;
	private ArrayList<Answer> answers;
	
	private int question_number;

	public Question(String question_text, String question_type, String question_description,
			int question_score, ArrayList<Answer> answers, int question_number) {
		this.question_type = question_type;
		this.question_text = question_text;
		this.question_data = question_description;
		this.question_score = question_score;
		this.answers = answers;
		this.question_number = question_number;
		
	}

	public String toString() {
		String result = "" + "text: " + question_text + "\n" + "description: " + question_data + "\n" + "type: "
				+ question_type + "\n" + "score: " + question_score + "\n";
		return result;
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
	}

	public String getQuestiontype() {
		return question_type;
	}

	public double getQuestionscore() {
		return question_score;
	}

	public abstract double gradeAnswer(HttpServletRequest request, int questionIndex);

	public void setQuestiontype(String question_type) {
		this.question_type = question_type;
	}

	public String getQuestiondescription() {
		return question_data;
	}

	public void setQuestiondescription(String question_description) {
		this.question_data = question_description;
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
	
	public int getQuestionnumber(){
		return question_number;
	}
	
	@Override
	public int compareTo(Object o) {
		if (this.question_number < ((Question)o).getQuestionnumber() ){
			return -1;
		}
		
		if (this.question_number > ((Question)o).getQuestionnumber() ){
			return 1;
		}
		
		return 0;
	}

}
