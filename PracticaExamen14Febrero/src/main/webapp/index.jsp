<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Compra Online</h1>
<h4>Introduzca los datos de su compra</h4>

	<form action="RealizarCompra" method="POST">
		C�digo de cliente      <input type="text" name="cod_cliente"><br>
		Clave					      <input type="text" name="clave"><br>
		C�digo de producto  <input type="text" name="cod_producto"><br>
		<input type="submit" name="enviar" value="enviar"><br>
	</form>
</body>
</html>