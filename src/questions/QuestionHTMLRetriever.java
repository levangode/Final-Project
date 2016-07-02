package questions;

import javax.servlet.http.HttpServletRequest;

public class QuestionHTMLRetriever {
	private Question getBlankQuestion(HttpServletRequest request, String question_type) {
		Question newOne = null;
		String question_text = request.getParameter("question");
		String question_description = request.getParameter("description");
		int question_score = 0;
		int question_number = Integer.parseInt(request.getParameter("questionNum"));
		try {
			question_score = Integer.parseInt(request.getParameter("score"));
			newOne = QuestionFactory.getBlankQuestion(question_text, question_type, question_description,
					question_score, question_number);
		} catch (Exception e) {
			System.out.println("ParseInt Error");
		}
		return newOne;
	}

	private Question getMultiAnswerQuestion(HttpServletRequest request, String question_type) {
		Question newOne = null;
		String question_text = request.getParameter("question");
		String question_description = request.getParameter("description");
		int question_score = 0;
		try {
			question_score = Integer.parseInt(request.getParameter("score"));
		} catch (Exception e) {
			System.out.println("ParseInt Error");
		}
		boolean order = false;
		int num_answers = Integer.parseInt(request.getParameter("numAnswers"));
		if (request.getParameter("order").equals("Ordered")) {
			order = true;
		}
		int question_number = Integer.parseInt(request.getParameter("questionNum"));
		newOne = QuestionFactory.getQuestionWithMultipleAnswers(question_text, question_type,
				question_description, question_score, num_answers, order, question_number);
		return newOne;
	}

	private Question getQuestionResponse(HttpServletRequest request, String question_type) {
		Question newOne=null;
		String question_text = request.getParameter("question");
		String question_description = request.getParameter("description");
		int question_score = 0;
		try {
			question_score = Integer.parseInt(request.getParameter("score"));
		} catch (Exception e) {
			System.out.println("ParseInt Error");
		}
		int question_number = Integer.parseInt(request.getParameter("questionNum"));
		newOne = QuestionFactory.getQuestionResponse(question_text, question_type, question_description,
				question_score, question_number);
		return newOne;
	}

	private Question getMultiChoiceMultiAnswer(HttpServletRequest request, String question_type) {
		Question newOne = null;
		String question_text = request.getParameter("question");
		String question_description = request.getParameter("description");
		int question_score=0;
		int answers_to_show=0;
		int answers_to_be_correct=0;
		int question_number=0;
		try {
			question_score = Integer.parseInt(request.getParameter("score"));
			answers_to_show = Integer.parseInt(request.getParameter("questionsToShow"));
			answers_to_be_correct = Integer.parseInt(request.getParameter("correctNeeded"));
			question_number = Integer.parseInt(request.getParameter("questionNum"));
		} catch (Exception e) {
			System.out.println("ParseInt Error");
		}
		newOne = QuestionFactory.getMultipleChoiceQuesion(question_text, question_type, question_description,
				question_score, answers_to_show, answers_to_be_correct, question_number);
		return newOne;
	}

	public Question getQuestion(String type, HttpServletRequest request) {
		Question question = null;
		switch (type) {
		case QuestionTypes.FillInTheBlanks:
			question = getBlankQuestion(request, type);
			break;
		case QuestionTypes.MultiAnswer:
			question = getMultiAnswerQuestion(request, type);
			break;
		case QuestionTypes.QuestionResponse:
			question = getQuestionResponse(request, type);
			break;
		case QuestionTypes.MultipleChoiceMultipleAnswer:
			question = getMultiChoiceMultiAnswer(request, type);
			break;

		default:
			break;
		}

		return question;
	}
}
