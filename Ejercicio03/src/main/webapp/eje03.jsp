<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cuadrados de colores</title>
<style>
body {
	background: silver;
	text-align: justify;
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 14px;
	color: #757E82;
}

#container {
	margin: 0 auto;
	width: 500px;
	background: #fff;
	border: solid 1px;
}

#header {
	background: blue;
	text-align: center;
	padding: 20px;
	color: white;
	text-shadow: black 0.1em 0.1em 0.2em;
}

#content {
	background: white;
	clear: left;
	padding: 20px;
	align-content: center;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Tablero de colores</h1>
		</div>
		<div id="content">
			<table>
<%!// Funcion auxiliar !OJO a la marca %!
	String dameColor() {
		int ncolor = (int) (Math.random() * 5 + 1);
		String svalor = "white";
		switch (ncolor) {
		case 1:
			svalor = "red";
			break;
		case 2:
			svalor = "blue";
			break;
		case 3:
			svalor = "green";
			break;
		case 4:
			svalor = "black";
			break;
		}

		return svalor;
	}
%>
<%  // Marca normal 
for (int i = 1; i <= 10; i++) {
		out.println("<tr>");
		for (int j = 1; j <= 10; j++) {
			String color = dameColor();
			out.println("<td style=\"background-color:" + color + "; height:40px; width:40px\"></td>");
			}
		out.println("</tr>");
 }
  %>
</table>
</div>
</div>
</body>
</html>