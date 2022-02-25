<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Procesa un formulario de acceso</title>
</head>
<body>
<center>
	<%
	    boolean exito = false;
		String nombre = request.getParameter("nombre");
		String clave = request.getParameter("clave");
		// Si no se han rellenado los campos no se envian en el POST
		if (nombre == null || clave == null) {	
			out.println("<h1> Introduzca un nombre y una clave </h1> ");
		} else
		// Alguno de los campos o los dos están vacios
		if (nombre.length() == 0 || clave.length() == 0) {
			out.println("<h1> Introduzca un nombre y una clave </h1> ");
		}
		// Ambos parámetros están rellenos
		else {
			if (nombre.contentEquals("alumno")
					&& clave.contentEquals("alumno")) {
				out.println("<h1> ACCESO AUTORIZADO </h1>");
				exito = true;
			} else {
				out.println("<h1> ACCESO NO AUTORIZADO </h1><BR>"
						+ " Introduzca un usuario y contraseña correctos");
			}
		}
		// Si no está autorizado se muestra de nuevo el formulario
		if (! exito )
		{
			%>
			<table align="center">
		    <form method="post">
		    <tr>
		    <td>Nombre:</td>
		    <td><input type="text" name="nombre"/></td>
		    </tr>
		    <tr>
		    <td>Clave:</td>
		    <td><input type="password" name="clave"/></td>
		    </tr>
		    <tr>
		    <td></td>
		    <td><input type="submit" value="Acceder"/></td>
		    </tr>
		    </form>
		    </table>
		    <%
		}
	%>
</center>
</body>
</html>