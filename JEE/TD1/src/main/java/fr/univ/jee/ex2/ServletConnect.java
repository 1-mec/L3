package fr.univ.jee.ex2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletConnect
 */
@WebServlet("/ServletConnect")
public class ServletConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConnect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<html><body>");
		response.getWriter().append("""
				<form action="/TD1/ServletConnect" method="POST">
				login <input name="login" id="login" type="text"><br>
				password <input name="password" type="password"><br>
				<input type="submit" value="Connect">
				</form>
				""");
		response.getWriter().append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession sess = request.getSession();
		sess.setAttribute("log", request.getParameter("login"));

		response.sendRedirect("/TD1/ServletWelcom");
	}

}
