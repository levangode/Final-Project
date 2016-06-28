package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBQuizControllers.DBQuizController;

/**
 * Servlet implementation class SubmitLike
 */
@WebServlet("/SubmitLike")
public class SubmitLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitLike() {
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
		DBQuizController qc = new DBQuizController();
		int quiz_id=Integer.parseInt(request.getParameter("like_id"));
		qc.addLike((String)request.getSession().getAttribute("user_name"), quiz_id);
		response.sendRedirect("QuizSummaryPage.jsp?id="+quiz_id);
		doGet(request, response);
	}

}
