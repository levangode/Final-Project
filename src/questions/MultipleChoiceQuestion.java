package questions;

import java.util.ArrayList;

import answers.Answer;
import answers.MultipleAnswer;
import answers.MultipleChoiceAnswer;

public class MultipleChoiceQuestion extends Question {
	private int answers_to_show;
	private int answers_to_be_correct;

	public MultipleChoiceQuestion(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, int answers_to_show, int answers_to_be_correct,
			ArrayList<Answer> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
		this.answers_to_show = answers_to_show;
		this.answers_to_be_correct = answers_to_be_correct;
	}

	public String toString() {
		String result = super.toString() + "answers to show: " + answers_to_show + "\n" + "answers correct: "
				+ answers_to_be_correct + "\n";
		return result;

	}

	public int getNumanswersdisplay() {
		return answers_to_show;
	}

	public int getNumanswerscorrect() {
		return answers_to_be_correct;
	}

	@Override
	public String getQuestionHtml(int id) {
		String html = "";
		html += "<div id='question-" + id + "'>" + "<p>" + getQuestiontext() + "</p> ";
		ArrayList<Answer> answers = getAnswers();
		html += "<ul>";
		for (int i = 0; i < answers.size(); i++) {
			MultipleChoiceAnswer ans = (MultipleChoiceAnswer) answers.get(i);
			html += ans.getHtml(id, i);
		}
		html += "</ul></div>";
		return html;
	}

}
