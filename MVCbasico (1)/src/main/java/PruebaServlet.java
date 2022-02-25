
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.AccesoDatos;
import Modelo.Producto;



// URL a la que responder 
@WebServlet({ "/PruebaServlet", "/listar", "/consultar" })

public class PruebaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public PruebaServlet() {
		super();
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccesoDatos mimodelo = AccesoDatos.initModelo();

		// En funcion de la URL de la peticion obtengo los datos del modelo y se los
		// envio a la vista JSP
		if (request.getServletPath().equals("/listar")) {

			// Obtengo la lista de producto
			ArrayList<Producto> resultado = mimodelo.ObtenerProductos();
			// A�ado el atributo a la peticion
			request.setAttribute("listaProductos", resultado);
			// Invoco a la vista que muestra las lista de producto
			// El directorio /WEB-INF no es accesible directamente desde el navegador
			request.getRequestDispatcher("/WEB-INF/listarProductos.jsp").forward(request, response);
		}

		else if (request.getServletPath().equals("/consultar")) {
			// Simplicacion no hay comprobación de errores
			String idstring = request.getParameter("id");
			int idint = Integer.parseInt(idstring);
			// Obtengo el producto a partir de su id
			Producto resultado = mimodelo.ObtenerProductoId(idint);
			// Añado el atributo a la petición.
			request.setAttribute("productoItem", resultado);
			request.getRequestDispatcher("consultarProducto.jsp").forward(request, response);
		}

		else {
			response.sendRedirect("inicio.html");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
