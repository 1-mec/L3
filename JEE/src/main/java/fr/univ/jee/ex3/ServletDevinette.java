package fr.univ.jee.ex3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletDevinette
 */
@WebServlet("/ServletDevinette")
public class ServletDevinette extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String str;
	private boolean tested;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDevinette() {
		super();
		this.str = "";
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return A random number between 100 and 0
	 */
	private int genRes() {
		return (int) (Math.random() * 100);
	}

	int res = genRes();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<html><body>");
		response.getWriter().append("""
				<form action="/TD1/ServletDevinette" method="POST">
				Enter a number : <input name="number" id="numer" type="text"><br>
				<input type="submit" value="OK">
				</form>
				""");
		if (tested)response.getWriter().append("<br><br> " + getStr() + " !");
		response.getWriter().append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		tested = true;
		response.setContentType("text/html");
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println(res);
		System.out.println(tested);
		if (number == res) {
			response.sendRedirect("/TD1/ServletDevinnetteRes");
		} else if (number > res) {
			setStr("<br>Le chiffre est plus PETIT que ça");
		} else if (number < res) {
			setStr("<br>Le chiffre est plus GRAND que ça");
		}
		doGet(request, response);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String s) {
		this.str = s;
	}

	public boolean getTested() {
		return tested;
	}

	public void setTested(boolean b ) {
		this.tested = b;
	}
}
