<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <% HttpSession sesion = request.getSession();%>
   <% 
   
   int veces = 0;
	if(sesion.getAttribute("veces")!=null){
		veces= Integer.parseInt(sesion.getAttribute("veces").toString());
		veces++;
	}
		sesion.setAttribute("veces", veces);

	

   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<body>
<h2>Esta es su visita numero <%=veces %> </h2>
<title>CONSULTA DE MOVIMIENTOS</title>
</head>

	<h1>CONSULTA DE MOVIMIENTOS</h1>
	<form action="Servletconsulta" method="GET">
		CODIGO CLIENTE  <input type = "text"  name = "codCliente"> <br>
		IMPORTE MINIMO  <input type = "number"   name = "importeMinimo"> <br>
		IMPORTE MAXIMO  <input type = "number"  name ="importeMaximo"> <br>
		<input type = "submit" name = "accion" value = "enviar">
	</form>
</body>
</html>
