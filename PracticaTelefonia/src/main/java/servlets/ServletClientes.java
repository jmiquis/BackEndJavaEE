package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Cliente;
import models.DAO;

@WebServlet({ "/ServletClientes", "/verpuntos" })
public class ServletClientes extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DAO accesoDatos = DAO.initModel();
       
  
    public ServletClientes() {
        super();
   
    }
    
    private boolean checkPuntos(String puntos) throws Exception {
		return Integer.parseInt(puntos)>0;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			if(!checkPuntos(request.getParameter("puntos"))) {	
					request.setAttribute("error", "Los puntos introducidos no pueden ser negativos");
					request.getRequestDispatcher("/WEB-INF/errorTelefonia.jsp").forward(request, response);
					return;
			}
		}
		 catch (Exception e) {
			 	request.setAttribute("error", "Utilice solo numero enteros");
				request.getRequestDispatcher("/WEB-INF/errorTelefonia.jsp").forward(request, response);
				return;
		}
		
		int limitePuntos = Integer.parseInt(request.getParameter("puntos"));
		ArrayList <Cliente> listaClientes = accesoDatos.getClientes(limitePuntos); 
		
		if(listaClientes.size()==0) {
			request.setAttribute("error", "No existe ningun cliente con ese numero de puntos");
			request.getRequestDispatcher("/WEB-INF/errorTelefonia.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("listaClientes", listaClientes);
		request.getRequestDispatcher("/WEB-INF/listadoClientesTelefonia.jsp").forward(request, response);
		
		
		
	}

}
