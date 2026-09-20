package fr.univ.tours.jee.td2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class FacadeServlet
 */
@WebServlet("/FacadeServlet")
public class FacadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(true);

		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username.isBlank() || username == null || password.isBlank() || password == null) {
			response.sendRedirect("connexion.html");
			return;
		}
		
		session.setAttribute("username",request.getParameter("username"));
		session.setAttribute("password", request.getParameter("password"));
		response.sendRedirect("WelcomeServlet");
	
	}

}
