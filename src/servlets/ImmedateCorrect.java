package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Quiz;
import questions.Question;

/**
 * Servlet implementation class ImmedateCorrect
 */
@WebServlet("/ImmedateCorrect")
public class ImmedateCorrect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImmedateCorrect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int question_num = (Integer.parseInt((String)request.getAttribute("question_num")));
		
		System.out.println(question_num);
		
		Question curQuestion = ((Quiz)request.getSession().getAttribute("Quiz")).getQuestions().get(question_num);
		
		Double score_got = curQuestion.gradeAnswer(request, question_num);
		
		
		String resp = "you got " + score_got + " out of " + curQuestion.getQuestionscore();
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
