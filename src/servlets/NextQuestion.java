package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//TODO put question in the quiz;
		String question_text = request.getParameter("question");
		String question_type = request.getParameter("type");
		
		Enumeration<String> parameters = request.getParameterNames();
		String param = "";
		String answer = "";
		boolean isCorrect = false;
		while(parameters.hasMoreElements()){
			answer="";
			isCorrect=false;
			param = parameters.nextElement();
			if(param.contains("answer")){
				answer = request.getParameter(param);
			} else if(param.contains("tick")){
				isCorrect = true;
			}
		}
		response.sendRedirect("Questions.jsp?questionNum="+(Integer.parseInt(request.getParameter("questionNum"))+1));
		doGet(request, response);
	}

}
