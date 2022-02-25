package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOcompra;
import model.Producto;
import model.Socio;


@WebServlet("/RealizarCompra")
public class RealizarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOcompra DAO=DAOcompra.initModelo();
	private Socio partner; 
	private Producto product;
       
 
    public RealizarCompra() {
        
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			this.partner=DAO.getOnePartner(request.getParameter("cod_cliente"),request.getParameter("clave"));
			
			//si me devuelve nulo es porque no existe
			if(partner==null) {
				request.setAttribute("error", "Los valores de código de cliente y contraseña no son válidos");
				request.getRequestDispatcher("/WEB-INF/errorCompra.jsp").forward(request, response);
				return;
			}
			//si me devuelve nulo es porque no existe
			this.product=DAO.getOneProduct(request.getParameter("cod_producto"));
			if(product==null) {
				request.setAttribute("error", "No se encuentra ningún producto con el código "+request.getParameter("cod_producto")+" en nuestro catálogo");
				request.getRequestDispatcher("/WEB-INF/errorCompra.jsp").forward(request, response);
				return;
			}
			
			//Si el producto no tiene stock
			if(product.getStock()<1) {
				request.setAttribute("error", "El producto no tiene stock para ser comprado "+request.getParameter("cod_producto")+" en nuestro catálogo");
				request.getRequestDispatcher("/WEB-INF/errorCompra.jsp").forward(request, response);
				return;
			}
			
			
			
			//si es mas caro que lo que se puede gastar el socio
			if(product.getPrecio()>partner.getCantidad_max()) {
				request.setAttribute("socio",partner);
				request.setAttribute("message", "NO se ha podido efectuar la compra del producto: "+product.getNombre_pro()+" por "+product.getPrecio()+" Euros");
				request.getRequestDispatcher("/WEB-INF/vistaCompra.jsp").forward(request, response);
				return;
			}
			
	
			try {
				DAO.realizarCompra(product, partner);
				//lo hago otra vez para que se actualice despues de la compra
				partner = DAO.getOnePartner(partner.getCod_socio(),partner.getClave());
				request.setAttribute("socio",partner);
				request.setAttribute("message", "	Se ha efectuado la compra del producto: "+product.getNombre_pro()+" por "+product.getPrecio()+" Euros");
				request.getRequestDispatcher("/WEB-INF/vistaCompra.jsp").forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			
			

		
	}

}
