package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import DBQuizActivityContollers.DBQuizTake;
import DBQuizControllers.DBQuizController;
import DBQuizControllers.QuizInfoController;
import answers.Answer;
import backend.Quiz;
import questions.MultipleChoiceQuestion;
import questions.Question;
import quizInfoes.UserActivity;

/**
 * Servlet implementation class GradeQuiz
 */
@WebServlet("/GradeQuiz")
public class GradeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GradeQuiz() {
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
		Timestamp endTime = new Timestamp(new Date().getTime());
		HttpSession session = request.getSession();
		Timestamp startTime = (Timestamp) session.getAttribute("Starttime");
		String login = (String) session.getAttribute("user_name");
		System.out.println("Gadavedi doGetshi!");
		Quiz quiz = (Quiz) session.getAttribute("Quiz");
		if (quiz == null) {
			response.sendRedirect("HomePage.jsp");
			return;
		}
		double score = 0;
		double maxScore = 0;
		ArrayList<Question> questions = quiz.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			double curScore = q.gradeAnswer(request, i);
			System.out.println("gradedAnswers= " + curScore);
			double questionScore = q.getQuestionscore();

			maxScore += questionScore;
			score += curScore;
		}
		System.out.println("Maxscore:" + maxScore);
		System.out.println("CurScore:" + score);
		request.getSession().setAttribute("userScore", score);
		request.getSession().setAttribute("maxScore", maxScore);
		DBQuizController contr = new DBQuizController();
		contr.incrementTimesTaken(Integer.parseInt(request.getParameter("id")));
		session.removeAttribute("Quiz");
		session.removeAttribute("Starttime");
		// UserActivity activity = new UserActivity(endTime, startTime, score,
		// login);
		DBQuizController db = new DBQuizController();
		int id = db.getAuthorId(login);
		DBQuizTake adb = new DBQuizTake();
		adb.addActivity(id, Integer.parseInt(request.getParameter("id")),
				(int) (endTime.getTime() - startTime.getTime()), score);
		response.sendRedirect("MyGrade.jsp");
		doGet(request, response);
	}

}
