package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBQuestionControllers.DBQuestionMultipleChoice;
import DBQuestionControllers.DBQuestionResponse;
import DBQuestionControllers.DBQuestionWithMultipleAnswers;
import answers.Answer;
import answers.MultipleAnswer;
import answers.MultipleChoiceAnswer;

import database.DBconnector;

public class QuestionWithMultipleAnswers extends Question {
	private boolean order;
	private int numAnswers;

	public QuestionWithMultipleAnswers(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, int numAnswers, boolean order, ArrayList<Answer> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
		this.order = order;
		this.numAnswers = numAnswers;
	}

	public String toString() {
		String result = super.toString() + "order: " + order + "\n" + "answers required: " + numAnswers + "\n";
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
			html += "<input type='text' name='q" + id + "-" + i + "' id='q" + id + "-" + i + "'><br/>";
		}
		html += "</div>";
		System.out.println("Generated HTML: " + html);
		return html;
	}

	@Override
	public int gradeAnswer(HttpServletRequest request, int questionIndex) {
		int counter = 0;
		ArrayList<Answer> answers = getAnswers();
		for (int i = 0; i < getNumanswers(); i++) {
			String name = "q" + questionIndex + "-" + i;
			String res = request.getParameter(name);
			if (getAnswersordered()) {
				if (res.equals(answers.get(i).getAnswerText())) {
					counter++;
				}
			} else {
				for (int j = 0; j < answers.size(); j++) {
					if (answers.get(j).getAnswerText().equals(res)) {
						counter++;
						answers.remove(j);
					}
				}
			}
		}
		return counter;

	}

	@Override
	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionWithMultipleAnswers db = new DBQuestionWithMultipleAnswers(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);
	}

}
