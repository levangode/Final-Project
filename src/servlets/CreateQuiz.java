package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Quiz;

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

		System.out.println(quiz_name);
		System.out.println(quiz_description);
		System.out.println(quiz_category);
		System.out.println(quiz_difficulty);
		
		response.sendRedirect("Questions.jsp?questionNum="+1);
		
		doGet(request, response);
	}

}
