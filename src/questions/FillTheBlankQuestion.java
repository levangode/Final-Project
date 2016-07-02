package questions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import DBQuestionControllers.DBQuestionFillInTheBlanks;

import answers.Answer;
import database.DBconnector;

public class FillTheBlankQuestion extends Question {

	public FillTheBlankQuestion(String question_text, String question_type, String question_description,
			 int question_score, ArrayList<Answer> answers, int question_number) {
		super(question_text, question_type, question_description, question_score, answers,
				question_number);
	}

	@Override
	public String getQuestionHtml(int id) {
		String html = "";
		String questionTxt = getQuestiontext();
		// System.out.println("Question text is: " + questionTxt);
		// System.out.println("ANswers size:" + getAnswers().size());
		for (int i = 0; i < getAnswers().size(); i++) {
			String inputStr = "<input type='text' name='q" + id + "-" + i + "' id='q" + id + "-" + i + "'>";
			questionTxt = questionTxt.replaceFirst("____", inputStr);
		}
		html = questionTxt;
		System.out.println("Generated HTML: " + html);
		return html;
	}

	@Override
	public double gradeAnswer(HttpServletRequest request, int questionIndex) {
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
		return counter / answers.size() * getQuestionscore();
	}

	public void addToDatabase(int quiz_id) throws Exception {
		DBQuestionFillInTheBlanks db = new DBQuestionFillInTheBlanks(new DBconnector().getConnection());

		db.addQuestion(this, quiz_id);
	}

}
