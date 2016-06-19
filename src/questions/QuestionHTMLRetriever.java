package questions;

import javax.servlet.http.HttpServletRequest;

public class QuestionHTMLRetriever {
	private QuestionParent getBlankQuestion(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		QuestionParent newOne=QuestionFactory.getBlankQuestion(question_text, question_type, question_description, question_time_limit, question_score);
		return newOne;
	}
	private QuestionParent getMultiAnswerQuestion(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		boolean order=false;
		if(request.getParameter("order").equals("Ordered")){
			order=true;
		}
		QuestionParent newOne=QuestionFactory.getQuestionWithMultipleAnswers(question_text, question_type, question_description, question_time_limit, question_score, order);
		return newOne;
	}
	private QuestionParent getQuestionResponse(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		QuestionParent newOne=QuestionFactory.getQuestionResponse(question_text, question_type, question_description, question_time_limit, question_score);
		return newOne;
	}
	private QuestionParent getMultiChoiceMultiAnswer(HttpServletRequest request, String question_type){
		String question_text=request.getParameter("question");
		String question_description=request.getParameter("description");
		long question_time_limit = Integer.parseInt(request.getParameter("timeLimit")) * 60000;
		int question_score=Integer.parseInt(request.getParameter("score"));
		int answers_to_show=Integer.parseInt(request.getParameter("questionsToShow"));
		int answers_to_be_correct=Integer.parseInt(request.getParameter("correctNeeded"));
		QuestionParent newOne=QuestionFactory.getMultipleChoiceQuesion(question_text, question_type, question_description, question_time_limit, question_score, answers_to_show, answers_to_be_correct);
		return newOne;
	}
	public QuestionParent getQuestion(String type, HttpServletRequest request){
		QuestionParent question=null;
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
