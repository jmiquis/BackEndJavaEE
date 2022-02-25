<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Producto"%>
<!DOCTYPE html>
<% HttpSession sesion = request.getSession();%>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD DE PRODUCTOS</title>
<link href="web/default.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="container" style="width: 600px;">
		<div id="header">
			<h1>GESTIÓN DE PRODUCTOS JAVAEE</h1>
		</div>
		<div id="content">
			<form method="POST">
				<table>
					<tr>
						<td>PRODUCTO_NO</td>
						<td>
							<%
							String orden = request.getAttribute("orden").toString();
							%> <input
							type="number" name="producto_no" value="${product.producto_no}"
							<%= orden.equals("Detalles")?"readonly":"" %> size="20" autofocus>
						</td>
					</tr>
					<tr>
						<td>DESCRIPCION</td>
						<td><input type="text" name="descripcion"
							value="${product.descripcion}"
							<%= orden.equals("Detalles")?"readonly":"" %>
							size="8"></td>
					</tr>
					<tr>
						<td>PRECIO_ACTUAL</td>
						<td><input type="number" name="precio_actual"
							value="${product.precio_actual}"
							<%= orden.equals("Detalles")?"readonly":"" %> size=10></td>
					</tr>
					<tr>
						<td>STOCK_DISPONIBLE</td>
						<td><input type="text" name="stock_disponible"
							value="${product.stock_disponible}"
							<%= orden.equals("Detalles")?"readonly":"" %> size=20></td>
					</tr>
				</table>
				<input type="submit" name="orden" value="<%=orden%>"> <input
					type="button" name="orden" value="Volver" onclick="history.back()">

			</form>
		</div>
	</div>
</body>
</html>
