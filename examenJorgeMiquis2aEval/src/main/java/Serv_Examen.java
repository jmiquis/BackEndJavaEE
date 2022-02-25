

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AccesoDatosExamen;
import models.Cliente;
import models.Vehiculo;

/**
 * Servlet implementation class Serv_Examen
 */
@WebServlet({ "/Serv_Examen", "/reservar" })
public class Serv_Examen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccesoDatosExamen DAO = AccesoDatosExamen.initModelo();
    
    public Serv_Examen() {
        super();
  
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mensaje;
		//se busca el cliente
		
		Cliente clienteAux = DAO.getCliente(request.getParameter("cod_cli"),request.getParameter("clave"));
		
		//si no es correcto clave o nombre
		if(clienteAux==null) {
			mensaje = "ERROR .Los valores de código de cliente y contraseña no son válidos ";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/VistaSalida.jsp").forward(request, response);
			return;
		}
		
		//si existe pero tiene un vehiculo asignado
		
		if(clienteAux.getCod_car()!=0) {
			mensaje = "Ya tiene reservado el vehículo "+clienteAux.getCod_car();
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/VistaSalida.jsp").forward(request, response);
			return;
		}
		
		//si no hay vehiculos en la cuidad
		String localidad =request.getParameter("localidad");
		Vehiculo vehiculoAux = DAO.getVehiculo(localidad);
		if (vehiculoAux==null) {
			mensaje = "Actualmente no hay vehículos disponibles en  "+ localidad +clienteAux.getCod_car();
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/VistaSalida.jsp").forward(request, response);
			return;
		}
		
		//todo esta ok
		
		request.setAttribute("cliente", clienteAux.getNombre());
		mensaje = "Dispone en "+localidad+" del vehículo "+vehiculoAux.getCod_car();
		request.setAttribute("mensaje", mensaje);
		DAO.alquiler(vehiculoAux, clienteAux);
		request.getRequestDispatcher("/WEB-INF/VistaSalida.jsp").forward(request, response);
		return;
		
		

	}

}
