

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Prueba
 */
@WebServlet("/Prueba")
public class Prueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prueba() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		


	PrintWriter salida   = response.getWriter();
	Cookie         cook    = new Cookie("imagen", "cashu");
		
		if(request.getParameter("nuevatarjeta")!=null) {
			cook.setValue(request.getParameter("nuevatarjeta"));
		}
		
		
		try         {
			String      URL            = "http://localhost:8080/Ejercicios01/TarjetasCookies";

			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta http-equiv=\'Content-Type\' content=\'text/html; charset=UTF-8\'>");
			salida.println("<title>Forma de pago</title> </head><body>");
			salida.println("<H2> SU FORMA DE PAGO ACTUAL ES</H2> </br>");
			salida.println("<img src='imagenes/" + cook.getValue() + ".gif' alt='" + cook.getValue() + "'></a>");
			salida.println("<h2>SELECCIONE UNA NUEVA TARJETA DE CREDITO </h2><br>");
			salida.println("<a href='" + URL + "?nuevatarjeta=cashu'><img  src='imagenes/cashu.gif'/></a>&ensp;");
			salida.println("<a href='" + URL
					+           "?nuevatarjeta = cirrus1'>		  <img  src='imagenes/cirrus1.gif' 	    /></a>&ensp;");
			salida.println("<a href='" + URL
					+           "?nuevatarjeta = dinersclub'>	  <img  src='imagenes/dinersclub.gif'   /></a>&ensp;");
			salida.println("<a href='" + URL
					+           "?nuevatarjeta = mastercard1'>	  <img  src='imagenes/mastercard1.gif'  /></a>&ensp;");
			salida.println("<a href='" + URL
					+           "?nuevatarjeta = paypal'>		  <img  src='imagenes/paypal.gif'       /></a>&ensp;");
			salida.println("<a href='" + URL
					+           "?nuevatarjeta = visa1'>		  <img  src='imagenes/visa1.gif'        /></a>&ensp;");
			salida.println("<a href='" + URL
					+           "?nuevatarjeta = visa_electron'> <img  src='imagenes/visa_electron.gif'/></a></body></html>");
			
			
	
		
			
		}finally {
			salida.close();
		}
	}

}
