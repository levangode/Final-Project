package questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import DBQuestionControllers.DBQuestionMultipleChoice;
import DBQuestionControllers.DBQuestionWithMultipleAnswers;
import answers.Answer;
import answers.MultipleAnswer;
import answers.MultipleChoiceAnswer;
import database.DBconnector;

public class MultipleChoiceQuestion extends Question {
	private int answers_to_show;
	private int answers_to_be_correct;


	ArrayList<Answer> formAnswers;
	boolean answersFormatted = false;
	
	public MultipleChoiceQuestion(String question_text, String question_type, String question_description,
			int question_score, int answers_to_show, int answers_to_be_correct, ArrayList<Answer> answers,
			int question_number) {
		super(question_text, question_type, question_description, question_score, answers, question_number);
		this.answers_to_show = answers_to_show;
		this.answers_to_be_correct = answers_to_be_correct;
	}

	public String toString() {
		String result = super.toString() + "answers to show: " + answers_to_show + "\n" + "answers correct: "
				+ answers_to_be_correct + "\n";
		return result;

	}


	public ArrayList<Answer> getFormattedAnswers() {
		
		if(answersFormatted){
			return formAnswers;
		} 
		
		
		ArrayList<Answer> res = new ArrayList<Answer>();

		List<Answer> tmp = this.getAnswers();
		Collections.shuffle(tmp);

		int correct = answers_to_be_correct;
		int rest = answers_to_show - correct;

		// correct = 2;
		// rest = 3;

		System.out.println(correct);
		System.out.println(rest);

		// System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + total);

		for (Answer ans : tmp) {
			if (correct == 0 && rest == 0) {
				break;
			}

			if (((MultipleChoiceAnswer) ans).isCorrect()) {
				if (correct > 0) {
					correct--;
					res.add(ans);
					System.out.println("cor");
				}
			} else {
				if (rest > 0) {
					rest--;
					res.add(ans);
					System.out.println("wrong");
				}
			}
		}
		
		formAnswers = res;
		answersFormatted = true;
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
		// ArrayList<Answer> answers = getAnswers();
		ArrayList<Answer> answers = getFormattedAnswers();
		html += "<ul>";
		for (int i = 0; i < answers.size(); i++) {
			MultipleChoiceAnswer ans = (MultipleChoiceAnswer) answers.get(i);
			html += ans.getHtml(id, i);
		}
		html += "</ul>";
		if (!getQuestiondescription().equals("")) {
			html += "<br><img src='" + getQuestiondescription() + "' alt='Question Image' height='100' width='100'>";
		}
		html += "</div>";
		return html;
	}

	@Override
	public double gradeAnswer(HttpServletRequest request, int questionIndex) {
		double counter = 0;
		ArrayList<Answer> answers = getFormattedAnswers();
		for (int i = 0; i < answers.size(); i++) {
			MultipleChoiceAnswer ans = (MultipleChoiceAnswer) answers.get(i);
			String name = "q" + questionIndex + "-" + i;
			if (ans.getAnswercorrect()) {
				if (request.getParameter(name) != null) {
					counter++;
				}
			} else if (request.getParameter(name) != null) {
				return 0;
			}
		}
		if (getNumanswerscorrect() == 0)
			return 0;
		return (counter / (double) getNumanswerscorrect()) * getQuestionscore();
	}

	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionMultipleChoice db = new DBQuestionMultipleChoice(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);
	}

}
