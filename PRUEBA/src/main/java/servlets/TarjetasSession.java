package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Tarjetas
 */
@WebServlet("/TarjetasSession")
public class TarjetasSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TarjetasSession() {
        super();
        
    }
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		PrintWriter salida = response.getWriter();
		
		
		try {
			String URL ="http://localhost:8080/Ejercicios01/TarjetasSession";
	
			
		
			
		
			
			if(request.getParameter("nuevatarjeta")==null) {
				sesion.setAttribute("nuevatarjeta","cirrus1");
			}
			else {
				Object param =request.getParameter("nuevatarjeta");
				sesion.setAttribute("nuevatarjeta",param);
			}
			
			
			
			
			
			salida.println("<html>");
			salida.println("<head>");
			salida.println("<meta http-equiv=\'Content-Type\' content=\'text/html; charset=UTF-8\'>");
			salida.println("<title>Forma de pago</title> </head><body>");
			salida.println("<H2> SU FORMA DE PAGO ACTUAL ES</H2> </br>");
			salida.println("<img src='imagenes/"+sesion.getAttribute("nuevatarjeta")+".gif' alt='"+sesion.getAttribute("nuevatarjeta")+"'></a>");
			salida.println("<h2>SELECCIONE UNA NUEVA TARJETA DE CREDITO </h2><br>");
			salida.println("<a href='"+URL+"?nuevatarjeta=cashu'><img  src='imagenes/cashu.gif'/></a>&ensp;");
			salida.println("<a href='"+URL+"?nuevatarjeta=cirrus1'>		  <img  src='imagenes/cirrus1.gif' 	    /></a>&ensp;");
			salida.println("<a href='"+URL+"?nuevatarjeta=dinersclub'>	  <img  src='imagenes/dinersclub.gif'   /></a>&ensp;");			
			salida.println("<a href='"+URL+"?nuevatarjeta=mastercard1'>	  <img  src='imagenes/mastercard1.gif'  /></a>&ensp;");
			salida.println("<a href='"+URL+"?nuevatarjeta=paypal'>		  <img  src='imagenes/paypal.gif'       /></a>&ensp;");
			salida.println("<a href='"+URL+"?nuevatarjeta=visa1'>		  <img  src='imagenes/visa1.gif'        /></a>&ensp;");
			salida.println("<a href='"+URL+"?nuevatarjeta=visa_electron'> <img  src='imagenes/visa_electron.gif'/></a></body></html>");
			
			
		}finally {
			salida.close();
		}

	}
}