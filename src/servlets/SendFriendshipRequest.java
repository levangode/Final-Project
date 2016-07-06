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
 * Servlet implementation class SendFriendShiprequest
 */
@WebServlet("/SendFriendshipRequest")
public class SendFriendshipRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendFriendshipRequest() {
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
		
		System.out.println("Shemovedi aq");
		int friendId = (int) request.getSession().getAttribute("friendId");
		DBQuizController db = new DBQuizController();
		int yourId = db.getAuthorId((String) request.getSession().getAttribute("user_name"));
		DBFriendController fdb = new DBFriendController(con);
		fdb.addFriendshpRequestByUserID(yourId, friendId);
		request.getSession().removeAttribute("friendId");
		response.sendRedirect("UserPage.jsp?id=" + friendId);
		
		Connector.returnConnection(con);
		
		doGet(request, response);
	}

}
