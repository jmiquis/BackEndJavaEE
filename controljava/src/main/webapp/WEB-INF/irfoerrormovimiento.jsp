<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Importo las clases necesarias --%>
<%@ page  import="java.util.ArrayList" %>
<%@ page import="modelo.Movimiento" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	table,th,td,tr{
		border:"1px solid black";
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de movimientos </title>
</head>
<body>
<% ArrayList <Movimiento> resultados=( ArrayList <Movimiento>)request.getAttribute("listaResultados");  %>
	<table>
		<tr>
			<th>numero de movimiento</th> <th>codigo de cliente</th> <th>concepto</th> <th>importe</th>
		</tr>
<%for( Movimiento m: resultados){%>	
		<tr>
			<td><%= m.getNum_mov()%></td>
			<td><%= m.getCod_cliente()%></td>
			<td><%= m.getConcepto()%></td>
			<td><%= m.getImporte()%></td>
		</tr>
<% }%>
</table>
</body>
</html>