package fr.univ.jee.ex3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletDevinnetteRes
 */
@WebServlet("/ServletDevinnetteRes")
public class ServletDevinnetteRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDevinnetteRes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<html><body><center>");
		response.getWriter().append("""
				<form action="/TD1/ServletDevinetteRes" method="POST">
				Bien joué chef !<br>
				<input type="submit" value="Rejouer">
				</form>
				""");
		response.getWriter().append("</center></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");/*
		ServletDevinette sd = new ServletDevinette();
		String res = sd.getTested() + "";
		HttpSession ses = request.getSession();
		ses.setAttribute("res", request.getParameter(res));*/
		response.sendRedirect("/TD1/ServletDevinette");
	}

}
