package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBQuestionControllers.DBQuestionResponse;
import answers.Answer;
import database.DBconnector;

public class QuestionResponse extends Question {

	public QuestionResponse(String question_text, String question_type, String question_description, int question_score,
			ArrayList<Answer> answers, int question_number) {
		super(question_text, question_type, question_description, question_score, answers, question_number);

	}

	@Override
	public String getQuestionHtml(int id) {
		String html = "";
		html += "<div id='question-" + id + "'>" + "<p>" + getQuestiontext() + "</p> ";
		html += "<input type='text' name='q" + id + "' id='q" + id + "'><br/>";
		html += "</div>";
		System.out.println("Generated HTML: " + html);
		return html;
	}

	@Override
	public double gradeAnswer(HttpServletRequest request, int questionIndex) {
		String name = "q" + questionIndex;
		if (request.getParameter(name) != null) {
			String res = request.getParameter(name);
			ArrayList<Answer> answers = getAnswers();
			for (int i = 0; i < answers.size(); i++) {
				Answer ans = answers.get(i);
				if (ans.getAnswerText().equals(res)) {
					return 1;
				}
			}
		}
		return 0;
	}

	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionResponse db = new DBQuestionResponse(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);

	}

}
