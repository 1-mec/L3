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
 * Servlet implementation class MoifyServlet
 */
@WebServlet("/ModifyServlet")
public class MoifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database db = (Database)request.getServletContext().getAttribute("database");
		String id = request.getParameter("id");
		System.out.println("--------------------------" + id);
		Cours c = db.findByID(Integer.parseInt(id));
		db.delete(c);
		response.sendRedirect("CoursServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
