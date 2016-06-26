package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.IntrospectionUtils;

import DBQuestionControllers.DBQuestionFillInTheBlanks;
import DBQuestionControllers.DBQuestionWithMultipleAnswers;

import answers.Answer;
import database.DBconnector;

public class FillTheBlankQuestion extends Question {

	public FillTheBlankQuestion(String question_text, String question_type, String question_description,
			long question_time_limit, int question_score, ArrayList<Answer> answers) {
		super(question_text, question_type, question_description, question_time_limit, question_score, answers);
	}

	private int numAnswers;

	@Override
	public String getQuestionHtml(int id) {
		String html = "";
		String questionTxt = getQuestiontext();
		for (int i = 0; i < getNumAnswers(); i++) {
			String inputStr = "<input type='text' name='q" + id + "-" + i + "' id='q" + id + "-" + i + "'>";
			questionTxt.replaceFirst("____", inputStr);
		}
		System.out.println("Generated HTML: " + html);
		return html;
	}

	public int getNumAnswers() {
		return numAnswers;
	}

	public void setNumAnswers(int numAnswers) {
		this.numAnswers = numAnswers;
	}

	@Override
	public int gradeAnswer(HttpServletRequest request, int questionIndex) {
		int counter = 0;
		ArrayList<Answer> answers = getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			String name = "q" + questionIndex + "-" + i;
			if (request.getParameter(name) != null) {
				String res = request.getParameter(name);
				if (res.equals(answers.get(i).getAnswerText()))
					counter++;
			}
		}
		return counter;
	}

	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionFillInTheBlanks db = new DBQuestionFillInTheBlanks(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);
	}

}
