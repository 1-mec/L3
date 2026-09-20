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
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);

		PrintWriter pw = response.getWriter();
		pw.append("<html><body>");
		if (session == null) {
			pw.append("<h1> There's nothing bruh </h1> ");
		} else {
			pw.append("<h1><center> Hello " + session.getAttribute("username") + " ! It's the " + session.getAttribute("vu")   + " time you do that ");
		}
		pw.append("</body></html>");
	}

}
