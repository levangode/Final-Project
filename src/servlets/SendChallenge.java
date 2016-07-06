package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChallengeControllers.ChallengeController;
import backend.Challenge;
import backend.ChallengeFactory;
import backend.User;
import database.UserController;

/**
 * Servlet implementation class SendChallenge
 */
@WebServlet("/SendChallenge")
public class SendChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChallenge() {
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
		int sender = Integer.parseInt(request.getParameter("sender"));
		int receiver = Integer.parseInt(request.getParameter("receiver"));
		UserController first = new UserController();
		User from_user = first.getUserByID(sender);
		UserController second = new UserController();
		User to_user = second.getUserByID(receiver);
		
		int quiz_id = Integer.parseInt(request.getParameter("quiz"));
		Challenge newOne = ChallengeFactory.getChallenge(from_user, to_user, quiz_id);
		newOne.addToDatabase();
		response.sendRedirect("QuizSummaryPage.jsp?id="+quiz_id);
		doGet(request, response);
	}

}
