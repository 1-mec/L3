package fr.univ.jee.tp_note;

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
import java.time.LocalDateTime;

/**
 * Servlet Filter implementation class FiltreSecure
 */
@WebFilter("/JournalServlet")
public class FiltreSecure extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public FiltreSecure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession sess = req.getSession();
		String token = (String) sess.getAttribute("token");
		boolean expire =  false ;//(token.endsWith(LocalDateTime.now()) > LocalDateTime.now());
		if (sess == null || token == null || expire ) {
			resp.getWriter().append("Authentification requise");
			resp.sendRedirect("Login");
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("JournalServlet");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
