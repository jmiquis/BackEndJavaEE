<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="models.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%if(request.getAttribute("cliente")!=null){ %>
<h1>Bienvenido/a <%=request.getAttribute("cliente") %></h1>
<%} %>

<%=request.getAttribute("mensaje")%>

</body>
</html>