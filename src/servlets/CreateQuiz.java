package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Quiz;
import backend.QuizFactory;

/**
 * Servlet implementation class CreateQuiz
 */
@WebServlet("/CreateQuiz")
public class CreateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO create quiz object into session
		String quiz_name = request.getParameter("quiz_name");
		String quiz_description = request.getParameter("quiz_description");
		String quiz_category = request.getParameter("categories");
		String quiz_difficulty = request.getParameter("difficulty");
		String quiz_author = (String)request.getSession().getAttribute("user_name");
		boolean random_questions = false;
		boolean immediate_correction = false;
		boolean multiplePage = false;
		String show_on = "";
		if(request.getParameter("Random Questions") != null)
			random_questions = true;
		if(request.getParameter("Immediate Correction") != null)
			immediate_correction = true;
		show_on = request.getParameter("Show on");
		if(show_on.equals("Multiple Pages")){
			multiplePage = true;
		}
		
		Quiz newOne=QuizFactory.getQuiz(quiz_name, quiz_description, quiz_author, 0, quiz_category, quiz_difficulty, 0, multiplePage, immediate_correction, random_questions);
		request.getSession().setAttribute("Quiz", newOne);
		response.sendRedirect("Questions.jsp?questionNum="+1);
		
		doGet(request, response);
	}

}
