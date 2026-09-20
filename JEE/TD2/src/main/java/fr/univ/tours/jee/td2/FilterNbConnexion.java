package fr.univ.tours.jee.td2;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sun.net.httpserver.HttpsServer;

/**
 * Servlet Filter implementation class FilterNbConnexion
 */
@WebFilter("/WelcomeServlet")
public class FilterNbConnexion extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		
		Integer vuCpt = (Integer) session.getAttribute("vu");
		if (vuCpt == null) {
			vuCpt = 0;
		}
		if (vuCpt >= 3) {
			rep.setContentType("text/html");
			rep.getWriter().append("<center>Nop lil guy, you logged in too many time"
					+ "<br>"
					+ "<a href=\"connexion.html\">Déconnection </a></center>");
			session.invalidate();
			return;
		}
		
		vuCpt++;
		session.setAttribute("vu", vuCpt);
		chain.doFilter(request, response);
		
		
		
	}


}
