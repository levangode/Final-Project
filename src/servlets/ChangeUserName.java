package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBQuizControllers.DBQuizController;
import database.UserController;

/**
 * Servlet implementation class ChangeUserName
 */
@WebServlet("/ChangeUserName")
public class ChangeUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserName() {
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
		String newName = (String) request.getParameter("userName");
		String login = (String) request.getSession().getAttribute("user_name");
		UserController contr = new UserController();
		DBQuizController db = new DBQuizController();
		int id = db.getAuthorId(login);
		contr.editUserName(login, newName);
		response.sendRedirect("UserPage.jsp?id=" + id);
		doGet(request, response);
	}

}
