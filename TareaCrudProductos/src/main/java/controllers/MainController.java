package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({ "/MainControler", "/IndiceProductos" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MainController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 

		String valororden = request.getParameter("orden");
		if (valororden != null) {
            Actions act = new Actions(request,response);
			switch (valororden) {
			case "Nuevo":
				act.actionCreate();
				break;
			case "Borrar":
				int num = Integer.parseInt(request.getParameter("producto_no"));
				act.actionDelete(num);
				break;
			case "Modificar":
				act.actionModify(Integer.parseInt(request.getParameter("producto_no")));
				break;
			case "Detalles":
				act.accionDetails(Integer.parseInt(request.getParameter("producto_no")));
				 break;
			case "Terminar":
				act.actionTerminate();
				break;

			}
		}
		
		// En los demas no es necesario 
		if (valororden == null ||  valororden.equals("Borrar")){
   		String contenido = Functions.showData();
   		request.setAttribute("contenido", contenido);
		request.getRequestDispatcher("/WEB-INF/principalProductos.jsp").forward(request, response);
	   }

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String valororden = request.getParameter("orden");
		
		if (valororden != null) {
			Actions acts = new Actions(request,response);
			switch (valororden) {
			
			case "Nuevo":
				try {
					acts.actionPostCreate();
				} catch (Exception e) {
					e.printStackTrace();
				}				
				break;
				
			case "Modificar":		
				try {
					acts.actionPostModify();
				} catch (Exception e) {
					e.printStackTrace();
				}				
				break;
				
			case "Detalles":
				; // No hago nada
			}

		}
		String contenido = Functions.showData();
   		request.setAttribute("contenido", contenido);
		request.getRequestDispatcher("/WEB-INF/principalProductos.jsp").forward(request, response);
	}
}