package fr.univ.tours.jee.td4.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.univ.tours.jee.td4.Dao.Cours;
import fr.univ.tours.jee.td4.Dao.Database;

/**
 * Servlet implementation class SupprimerServlet
 */
@WebServlet("/SupprimerServlet")
public class SupprimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database db = (Database)request.getServletContext().getAttribute("database");
		String intitu = request.getParameter("intitule");
		System.out.println(db);
		int id = Integer.parseInt(request.getParameter("id"));
		int coef = Integer.parseInt(request.getParameter("coef"));
		int duree = Integer.parseInt(request.getParameter("duree"));
		Cours c = new Cours(id,intitu,coef,duree);
		db.update(c);
		response.sendRedirect("CoursServlet");
	}

}
