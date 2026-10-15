package fr.univ.tours.jee.td4.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import fr.univ.tours.jee.td4.Dao.Cours;
import fr.univ.tours.jee.td4.Dao.Database;

/**
 * Servlet implementation class AddCoursServlet
 */
@WebServlet("/AddCoursServlet")
public class AddCoursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCoursServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("AddCours.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database db = (Database)request.getServletContext().getAttribute("database");
		String intitu = request.getParameter("intitule");
		int coef = Integer.parseInt(request.getParameter("coef"));
		int duree = Integer.parseInt(request.getParameter("duree"));
		int id = db.count()+1;
		
		Cours c = new Cours(id,intitu,coef,duree);
		db.insert(c);
		request.setAttribute("database", db);

		Map<Integer,Cours> map = db.findAll();
		request.setAttribute("map", map);
        response.sendRedirect( "CoursServlet");
	}

}
