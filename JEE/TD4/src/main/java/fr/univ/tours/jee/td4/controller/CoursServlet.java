package fr.univ.tours.jee.td4.controller;

import jakarta.servlet.ServletConfig;
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
 * Servlet implementation class CoursServlet
 */
@WebServlet("/CoursServlet")
public class CoursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoursServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		if(getServletContext().getAttribute("database") == null) {
			getServletContext().setAttribute("database", new Database());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Database db = (Database) request.getServletContext().getAttribute("database");
		Map<Integer,Cours> map = db.findAll();
		request.setAttribute("map", map);
		request.setAttribute("database", db);
		System.out.println(db + "\n" + map);
		
		request.getRequestDispatcher("ListeCours.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        doGet(request, response);
		
	}

}
