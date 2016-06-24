package answers;

import javax.servlet.http.HttpServletRequest;

import questions.QuestionTypes;

public class AnswersHTMLRetriever {
	
	private Answer getBlankAnswer(HttpServletRequest request, String param){
		String answer_text=request.getParameter(param);
		int blank_position=Character.getNumericValue(param.charAt(param.length()-1));
		Answer newOne = AnswerFactory.getBlankAnswer(answer_text, blank_position);
		return newOne;
	}
	private Answer getMultiAnswerAnswer(HttpServletRequest request, String param){
		String answer_text=request.getParameter(param);
		int answer_num=Character.getNumericValue(param.charAt(param.length()-1));
		Answer newOne=AnswerFactory.getMultipleAnswerAnswer(answer_text, answer_num);
		return newOne;
	}
	private Answer getResponseAnswer(HttpServletRequest request, String param){
		String answer_text=request.getParameter(param);
		Answer newOne=AnswerFactory.getResponseAnswer(answer_text);
		return newOne;
	}
	private Answer getMultiChoiceAnswer(HttpServletRequest request, String param){
		String answer_text=request.getParameter(param);
		int answer_num=Character.getNumericValue(param.charAt(param.length()-1));
		boolean answer_correct=false;
		if(request.getParameter("tick"+answer_num) != null)
			answer_correct=true;
		Answer newOne=AnswerFactory.getMultipleChoiceAnswer(answer_text, answer_correct);
		return newOne;
	}
	public Answer getAnswer(String type, HttpServletRequest request, String param){
		Answer answer = null;
		switch (type) {
		case QuestionTypes.FillInTheBlanks:
			answer=getBlankAnswer(request, param);
			break;
		case QuestionTypes.MultiAnswer:
			answer=getMultiAnswerAnswer(request, param);
			break;
		case QuestionTypes.QuestionResponse:
			answer=getResponseAnswer(request, param);
			break;
		case QuestionTypes.MultipleChoiceMultipleAnswer:
			answer=getMultiChoiceAnswer(request, param);
			break;
		default:
			break;
		}
		return answer;
	}
}
