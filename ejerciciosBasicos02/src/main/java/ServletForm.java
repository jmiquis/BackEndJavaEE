

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletForm() {
        super();
   
    }
    
    public boolean checkUserAndPassword(String user, String password) {  	
    	return user.equals("alumno") && password.equals("alumno") && !user.isBlank() && !password.isBlank();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter salida = response.getWriter();
		
		if(request.getParameter("user")!=null && request.getParameter("password")!=null) {
			String userName = request.getParameter("user");
			String password  = request.getParameter("password");
			
			if (checkUserAndPassword(userName,password)) {
				salida.println("Acceso concedido");
				
			}
			else {
				salida.println("Nombre de usuario y contraseña incorrectos");
				response.setHeader("Refresh", "3; URL=eje02.jsp");
				return;
			}
		}
		
		
		
		
		
	}

}
