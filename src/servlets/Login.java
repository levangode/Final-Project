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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String user_login = request.getParameter("user_login");
		String user_password = request.getParameter("user_password");
		UserController db = new UserController();
		Hasher h = new Hasher();
		if(db.containsUser(user_login)){
			if(db.passwordMatch(user_login, h.generateHash(user_password))){
				request.getSession().setAttribute("user_name", user_login);
				request.getSession().setAttribute("logged_in", true);
				response.sendRedirect("homePage.jsp");
			} else {
				request.getRequestDispatcher("TryAgain.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("TryAgain.jsp").forward(request, response);
		}
		doGet(request, response);
	}

}
