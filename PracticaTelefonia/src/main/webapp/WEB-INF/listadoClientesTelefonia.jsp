<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page  import="java.util.ArrayList" %>
    <%@ page  import="models.Cliente" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% ArrayList<Cliente> listaClientes=(ArrayList<Cliente>)request.getAttribute("listaClientes");%>
	<table>
	<tr>
		<th>Cliente</th><th>Telefono</th><th>puntos</th>
	</tr>
	<%for(Cliente aux: listaClientes){ %>
		<tr>
			<td><%=aux.getNombre() %></td>
			<td><%=aux.getTelefono() %></td>
			<td><%=aux.getPuntos() %></td>
		</tr> 
	<%} %>
	</table>
</body>
</html>