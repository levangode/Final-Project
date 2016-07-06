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
 * Servlet implementation class RemoveFriend
 */
@WebServlet("/RemoveFriend")
public class RemoveFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFriend() {
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
		
		Connection con = null;
		
		try {
			con = Connector.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int fid = (int) request.getSession().getAttribute("friendId");
		int myId = new DBQuizController().getAuthorId((String) request.getSession().getAttribute("user_name"));
		new DBFriendController(con).cancelFriendshipByID(myId, fid);
		request.getSession().removeAttribute("friendId");
		response.sendRedirect("UserPage.jsp?id=" + fid);
		
		Connector.returnConnection(con);
		
		doGet(request, response);
	}

}
