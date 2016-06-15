package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Answer;
import backend.AnswerFactory;
import backend.Question;
import backend.QuestionFactory;
import backend.Quiz;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int questionNum=Integer.parseInt(request.getParameter("questionNum"));
		String question_text = request.getParameter("question");
		String question_type = request.getParameter("type");
		String question_description=request.getParameter("description");
		long question_time_limit=Integer.parseInt(request.getParameter("timeLimit"))*60000;
		Question newQuestion = QuestionFactory.getQuestion(question_text, question_type, question_description, question_time_limit);
		
		Enumeration<String> parameters = request.getParameterNames();
		String param = "";
		String answer = "";
		String description = "";
		boolean isCorrect = false;
		while(parameters.hasMoreElements()){
			answer="";
			description="";
			isCorrect=false;
			if(param.contains("answer")){
				answer = request.getParameter(param);
				description = request.getParameter(parameters.nextElement());
				param=parameters.nextElement();
				if(param.contains("tick")){
					isCorrect = true;
					if(parameters.hasMoreElements())
						param=parameters.nextElement();
				}
				Answer newAns = AnswerFactory.getAnswer(answer, description, isCorrect);
				newQuestion.addAnswer(newAns);
			} else {
				param = parameters.nextElement();
			}
		}
		
		Quiz currentQuiz = (Quiz)request.getSession().getAttribute("Quiz");
		currentQuiz.addQuestion(newQuestion, questionNum);
		response.sendRedirect("Questions.jsp?questionNum="+(Integer.parseInt(request.getParameter("questionNum"))+1));
		doGet(request, response);
	}

}
