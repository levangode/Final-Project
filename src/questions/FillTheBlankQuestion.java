package questions;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.IntrospectionUtils;

import DBQuestionControllers.DBQuestionFillInTheBlanks;
import DBQuestionControllers.DBQuestionWithMultipleAnswers;

import answers.Answer;
import database.DBconnector;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class FillTheBlankQuestion extends Question {

	public FillTheBlankQuestion(String question_text, String question_type, String question_description,
			int question_score, ArrayList<Answer> answers, int question_number) {
		super(question_text, question_type, question_description, question_score, answers, question_number);
	}

	@Override
	public String getQuestionHtml(int id) {
		String html = "";
		String questionTxt = getQuestiontext();
		for (int i = 0; i < getAnswers().size(); i++) {
			String inputStr = "<input type='text' name='q" + id + "-" + i + "' id='q" + id + "-" + i + "'>";
			questionTxt = questionTxt.replaceFirst("____", inputStr);
		}
		html = questionTxt;
		if (getQuestiondescription() != null) {
			html += "<br><img src='" + getQuestiondescription() + "' alt='Question Image' height='100' width='100'>";
		}
		System.out.println("Generated HTML: " + html);
		return html;
	}

	@Override
	public double gradeAnswer(HttpServletRequest request, int questionIndex) {
		double counter = 0;
		ArrayList<Answer> answers = getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			String name = "q" + questionIndex + "-" + i;
			if (request.getParameter(name) != null) {
				String res = request.getParameter(name);
				if (res.equals(answers.get(i).getAnswerText()))
					counter++;
			}
		}
		if (answers.size() == 0)
			return 0;
		return (counter / (double) answers.size()) * getQuestionscore();
	}

	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionFillInTheBlanks db = new DBQuestionFillInTheBlanks(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);
	}

}
