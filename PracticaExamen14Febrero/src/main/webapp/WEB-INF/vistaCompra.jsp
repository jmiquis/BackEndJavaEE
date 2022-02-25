<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="model.Socio" %>

<% Cookie cookie =new Cookie ("ultimaVisita", "esta es su primera visita"); %>

<%if(request.getCookies()!=null){
	
	Cookie[] cookies=request.getCookies();
	
	for(Cookie cook: cookies){
		String nombre = cook.getName();
		if(cook.getName().equals("ultimaVisita")){
			cookie.setValue(cook.getValue());
		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Socio socio =  (Socio)request.getAttribute("socio");%>
<h1>Bienvenido <%=socio.getNombre() %></h1>

<%=request.getAttribute("message") %>
Su limite actual de compra es de <%=socio.getCantidad_max() %> Euros <br>
Su ultima visita: <%=cookie.getValue()%>
<%
	cookie.setValue(new java.util.Date().toString()); 
    response.addCookie(cookie);
%>



</body>
</html>