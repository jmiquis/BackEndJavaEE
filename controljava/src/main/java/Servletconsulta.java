
import modelo.AccesoDatosMovimientos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Movimiento;


@WebServlet({ "/Servletconsulta", "/procesarConsulta" })
public class Servletconsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public Servletconsulta() {
        super();
  
    }
    protected String sendsError(String codCliente,int errorCode) {
    	String[] errorCodes = {"El codigo de cliente "+codCliente+" no se encuentra en la base de datos ","Los importes introducidos como importe no son correctos","No se han encontrado movimientos del cliente"+codCliente+ "con los importes seleccionados"};
    	return errorCodes[errorCode];
     	
	}
    
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String codCliente            = request.getParameter("codCliente");
			int       importeMinimo    = Integer.parseInt(request.getParameter("importeMinimo"));
			int       importeMaximo   = Integer.parseInt(request.getParameter("importeMaximo"));
			AccesoDatosMovimientos mimodelo = AccesoDatosMovimientos.initModelo(); 
			
			String msg;
			
		
			
			if(importeMinimo<0 || importeMaximo<0) {
				throw new Exception();
			}
			if (! mimodelo.checkUser(codCliente) ) {
				msg = sendsError(codCliente,0);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
				return;
			}
			else {
				ArrayList<Movimiento> resultados = mimodelo.obtenerMovimientosCliente(codCliente, importeMinimo, importeMaximo);
				if(resultados.size()==0) {
					msg = sendsError(codCliente,2);
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
					return;
				}
				else {
					request.setAttribute("listaResultados", resultados);
					request.getRequestDispatcher("/WEB-INF/informovimiento.jsp").forward(request, response);
				}
			}
			
			
			
			
			
		} catch (Exception e) {
			String msg = sendsError("",0);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
		
		}

	}
	
	  
	  
}
