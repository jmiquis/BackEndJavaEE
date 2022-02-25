package controladores;

import java.io.Console;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter({ "/Filtro", "/*" })
public class Filtro implements Filter {

	/**
	 * Default constructor.
	 */
	public Filtro() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req    = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		System.out.println("--> Filtro");
		System.out.println(req.getParameter("pin"));

		if (session.getAttribute("accesoconcedido") == null) {
			if (req.getParameter("pin") == null) {
				request.getRequestDispatcher("/WEB-INF/layout/formulariopin.jsp").forward(request, response);
				return;
			} else if (!req.getParameter("pin").equals("12345")) {
				request.getRequestDispatcher("/WEB-INF/layout/formulariopin.jsp").forward(request, response);
				return;
			} else {
				// Pin correcto
				session.setAttribute("accesoconcedido", "si");
				session.setAttribute("timeout", System.currentTimeMillis() / 1000);
			}
		}
		// Hay variable de sesion
		else {

			long tiempoactual = System.currentTimeMillis() / 1000; // a segundos
			long tiemposesion = (Long) session.getAttribute("timeout");
			if (tiempoactual - tiemposesion > 6000) {
				session.invalidate();
				request.getRequestDispatcher("/WEB-INF/layout/formulariopin.jsp").forward(request, response);
				return;
			}
		}

		// Mensaje informativo
		String valororden = req.getParameter("orden");
		if (valororden != null) {
			System.out.println(" Procesando orden:" + valororden);
		}
		
		// Continuo la cadena de peticiones  /indice
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
