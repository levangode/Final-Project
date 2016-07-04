package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBMessageControllers.DBMessage;
import DBMessageControllers.SendMessageInfo;

/**
 * Servlet implementation class MessageSend
 */
@WebServlet("/MessageSend")
public class MessageSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageSend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean res = false;
		
		String sender_login = request.getParameter("sender_login");
		String recipient_login = request.getParameter("recipient_login");
		String text = request.getParameter("message_text");
		String subject = request.getParameter("message_subject");
		
		SendMessageInfo mes = new SendMessageInfo(sender_login, recipient_login, text, subject);
		DBMessage dbm = new DBMessage();
		
		res = dbm.sendMessage(mes);
		
		String resp = null;
				
		if(res){
			resp = "Message Sent";
			resp += " " + sender_login + " " + recipient_login;
		} else {
			resp = "Error";
		}
		
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
