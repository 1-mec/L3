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

/**
 * Servlet Filter implementation class FilterLogin
 */
@WebFilter("/WelcomeServlet")
public class FilterLogin extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here


		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		boolean loggedIn = session != null && session.getAttribute("username") != null;
		
		if (loggedIn) {
			chain.doFilter(request, response);
		} else {
			rep.sendRedirect("connexion.html");
		}

		// pass the request along the filter chain
	}


}
