package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConnector.Connector;
import DBQuizControllers.DBQuizController;
import database.DBFriendController;

/**
 * Servlet implementation class RejectFriendshipRequest
 */
@WebServlet("/RejectFriendshipRequest")
public class RejectFriendshipRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RejectFriendshipRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int friendId = Integer.parseInt(request.getParameter("fid"));
		
		Connection con = null;
		
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBFriendController dbf = new DBFriendController(con);
		String login = (String) request.getSession().getAttribute("user_name");
		DBQuizController db = new DBQuizController();
		int id = db.getAuthorId(login);
		dbf.cancelFriendshipRequestByID(id, friendId);
		response.sendRedirect("UserPage.jsp?id=" + id);
		
		Connector.returnConnection(con);
		
		doGet(request, response);
	}

}
