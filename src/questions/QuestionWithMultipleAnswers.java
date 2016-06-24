package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answers.Answer;

public class QuestionWithMultipleAnswers extends Question {
	private boolean order;
	private int numAnswers;

	public QuestionWithMultipleAnswers(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, int numAnswers, boolean order, ArrayList<Answer> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
		this.order = order;
		this.numAnswers = numAnswers;
	}

	
	public String toString(){
		String result = super.toString()+"order: "+order+"\n"+"answers required: "+numAnswers+"\n";
		return result;
	}

	public int getNumanswers() {
		return numAnswers;
	}

	public boolean getAnswersordered() {
		return order;
	}

	@Override
	public String getQuestionHtml(int id) {
		String html = "";
		html += "<div id='question-" + id + "'>" + "<p>" + getQuestiontext() + "</p> ";
		for (int i = 0; i < getNumanswers(); i++) {
			html += "<input type='text' name='q" + id + "' id='q" + id + "-" + i + "'><br/>";
		}
		html += "</div>";
		return html;
	}

}
