package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserController;
import helpers.Hasher;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_login = request.getParameter("user_login");
		UserController db = new UserController();
		if(db.containsUser(user_login)){
			request.setAttribute("user_login", user_login);
			request.getRequestDispatcher("RegAgain.jsp").forward(request, response);
		} else {
			Hasher h = new Hasher();
			String user_password = h.generateHash(request.getParameter("user_password"));
			db.addNewUser(user_login, user_password, "");
			request.getRequestDispatcher("HomePage.jsp").forward(request,response);
		}
		doGet(request, response);
	}

}
