package questions;

import javax.servlet.http.HttpServletRequest;

public class QuestionHTMLRetriever {
	private Question getBlankQuestion(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		Question newOne=QuestionFactory.getBlankQuestion(question_text, question_type, question_description, question_time_limit, question_score);
		return newOne;
	}
	private Question getMultiAnswerQuestion(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		boolean order=false;
		int num_answers=Integer.parseInt(request.getParameter("numAnswers"));
		if(request.getParameter("order").equals("Ordered")){
			order=true;
		}
		Question newOne=QuestionFactory.getQuestionWithMultipleAnswers(question_text, question_type, question_description, question_time_limit, question_score, num_answers, order);
		return newOne;
	}
	private Question getQuestionResponse(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		Question newOne=QuestionFactory.getQuestionResponse(question_text, question_type, question_description, question_time_limit, question_score);
		return newOne;
	}
	private Question getMultiChoiceMultiAnswer(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		int answers_to_show=Integer.parseInt(request.getParameter("questionsToShow"));
		int answers_to_be_correct=Integer.parseInt(request.getParameter("correctNeeded"));
		Question newOne=QuestionFactory.getMultipleChoiceQuesion(question_text, question_type, question_description, question_time_limit, question_score, answers_to_show, answers_to_be_correct);
		return newOne;
	}
	public Question getQuestion(String type, HttpServletRequest request){
		Question question=null;
		switch (type) {
		case QuestionTypes.FillInTheBlanks:
			question=getBlankQuestion(request, type);
			break;
		case QuestionTypes.MultiAnswer:
			question=getMultiAnswerQuestion(request, type);
			break;
		case QuestionTypes.QuestionResponse:
			question=getQuestionResponse(request, type);
			break;
		case QuestionTypes.MultipleChoiceMultipleAnswer:
			question=getMultiChoiceMultiAnswer(request, type);
			break;
			
		default:
			break;
		}
		return question;
	}
}
