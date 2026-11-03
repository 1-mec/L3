package fr.univ.jee.tp_note;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		if(getServletContext().getAttribute("db") ==null) {
			getServletContext().setAttribute("db",new DataBase());
		}
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
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession();
		DataBase db = (DataBase)request.getServletContext().getAttribute("db");
		Map<Integer,Journal> map = db.findAll();
		request.setAttribute("map", map);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		sess.setAttribute("username",username);
		sess.setAttribute("password",password);
		System.out.println("-> "+map);
		System.out.println("-> "+db);
		
		if ( username == null || password == null || username.isBlank() || password.isBlank() || db.findByUser(username) == null ) {
			response.getWriter().println("<h1>Erreur d'autentification</h1>");
			response.sendRedirect("Login.jsp");
			return;
		} 
		String token = "Auth_"+ username+ "_"+ LocalDateTime.now().plusMinutes(1);
		sess.setAttribute("token", token);
		response.sendRedirect("/JournalServlet");
	}

}
