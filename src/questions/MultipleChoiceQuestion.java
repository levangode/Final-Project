package questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import DBQuestionControllers.DBQuestionMultipleChoice;
import answers.Answer;
import answers.MultipleChoiceAnswer;
import database.DBconnector;

public class MultipleChoiceQuestion extends Question {
	private int answers_to_show;
	private int answers_to_be_correct;

	public MultipleChoiceQuestion(String question_text, String question_type, String question_description,
			 int question_score, int answers_to_show, int answers_to_be_correct,
			ArrayList<Answer> answers, int question_number) {
		super(question_text, question_type, question_description, question_score, answers,
				question_number);
		this.answers_to_show = answers_to_show;
		this.answers_to_be_correct = answers_to_be_correct;
	}

	public String toString() {
		String result = super.toString() + "answers to show: " + answers_to_show + "\n" + "answers correct: "
				+ answers_to_be_correct + "\n";
		return result;

	}

	public ArrayList<Answer> getFormattedAnswers() {
		ArrayList<Answer> res = new ArrayList<Answer>();

		List<Answer> tmp = this.getAnswers();
		Collections.shuffle(tmp);

		int total = answers_to_show;
		int correct = answers_to_be_correct;

		for (Answer ans : tmp) {
			if (((MultipleChoiceAnswer) ans).isCorrect()) {
				if (correct > 0) {
					correct--;
					total--;
					res.add(ans);
				}
			} else if (!((MultipleChoiceAnswer) ans).isCorrect()) {
				if (total > 0) {
					total--;
					res.add(ans);
				}
			} else if (correct == 0 && total == 0) {
				break;
			} else {
				System.out.println("something went wrong int getFormattedAnswers Methd!!!!!!!!!!!!!!");
			}
		}

		return res;
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

	@Override
	public double gradeAnswer(HttpServletRequest request, int questionIndex) {
		int counter = 0;
		ArrayList<Answer> answers = getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			MultipleChoiceAnswer ans = (MultipleChoiceAnswer) answers.get(i);
			String name = "q" + questionIndex + "-" + i;
			if (ans.getAnswercorrect()) {
				if (request.getParameter(name) != null) {
					counter++;
				}
			}
		}
		return (counter / getNumanswerscorrect()) * getQuestionscore();
	}

	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionMultipleChoice db = new DBQuestionMultipleChoice(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);
	}

}
