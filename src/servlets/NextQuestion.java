package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBQuizControllers.DBQuizController;
import answers.Answer;
import answers.AnswersHTMLRetriever;
import backend.Quiz;
import questions.Question;
import questions.QuestionHTMLRetriever;

/**
 * Servlet implementation class NextQuestion
 */
@WebServlet("/NextQuestion")
public class NextQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NextQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int questionNum = Integer.parseInt(request.getParameter("questionNum"));
		String question_type = request.getParameter("type");
		if(question_type == null){
			Quiz quiz = (Quiz)request.getSession().getAttribute("Quiz");
			if(quiz == null){
				System.out.println("Session Timed Out");
				return;
			}
			DBQuizController ca = new DBQuizController();
			ca.addQuiz(quiz);
			response.sendRedirect("HomePage.jsp");
			return;
		}
		QuestionHTMLRetriever qr=new QuestionHTMLRetriever();
		AnswersHTMLRetriever ar=new AnswersHTMLRetriever();
		Question question=qr.getQuestion(question_type, request);
		
		Enumeration<String> parameters = request.getParameterNames();
		String param = "";
		while (parameters.hasMoreElements()) {
			param=parameters.nextElement();
			if(param.contains("answer")){
				Answer answer=ar.getAnswer(question_type, request, param);
				question.addAnswer(answer);
			}
		}
		Quiz current = (Quiz)request.getSession().getAttribute("Quiz");
		if(current == null){
			System.out.println("Session Timed Out");
			return;
		}
		current.addQuestion(question, questionNum);
		System.out.println(current);
		if (request.getParameter("finalise") != null) {
			Quiz quiz = (Quiz)request.getSession().getAttribute("Quiz");
			quiz.addToDatabase();
			response.sendRedirect("HomePage.jsp");
		} else {
			response.sendRedirect(
					"Questions.jsp?questionNum=" + (Integer.parseInt(request.getParameter("questionNum")) + 1));
		}
		doGet(request, response);
	}

}
