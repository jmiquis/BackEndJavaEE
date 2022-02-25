<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	Cookie[] todasLasCookies = request.getCookies();
    String fechaUltimoAcceso="";
    String mensaje ="";
    boolean encontrado = false; 
    
    // Busco si existe el cookie)
    if (todasLasCookies != null) {
        for (Cookie cookie : todasLasCookies) {
            if ( cookie.getName().contentEquals("ultimoacceso") ){
            	  fechaUltimoAcceso = cookie.getValue();
            	  encontrado = true;
            }
        }           
    }
	if ( encontrado ){
		mensaje = "Fecha último acceso = "+fechaUltimoAcceso+"<br>";
	} else {
		mensaje = " Bienvenido <br>";
	}
	
	String fechaactual = new java.util.Date().toString();
	Cookie nuevo = new Cookie("ultimoacceso",fechaactual);
	nuevo.setMaxAge(60*60*24*365); // 5 minutos
	response.addCookie(nuevo);
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMPRA DE SOCIOS</title>
</head>
<body>
   
	<div align="center">
	    <img src="tienda-online.png" width="200"  height="100"><br>
	     <%= mensaje %>
		<h1>
			INTRODUZCA LOS DATOS DE SU COMPRA
		</h1>
	</div>
	<div align="center">
		<fieldset>
			<legend> Datos de usuario </legend>
			<form name="formulario" method="post" action="realizarcompra">
				<table>
					<tr>
						<td>Código de Socio:</td>
						<td><input type="text" name="cod_socio" size="10"
							maxlength="10"></td>
					</tr>
					<tr>
						<td>Contraseña:</td>
						<td><input type="password" name="clave" size="10"
							maxlength="10"></td>
					</tr>
					<tr>
						<td>Código de producto:</td>
						<td><input type="text" name="cod_pro" size="12"
							maxlength="10"></td>
					</tr>

					<tr>
						<td colspan="2" align="center" class="formulario"><input
							type="submit" name="enviar" value="Enviar"></td>
					</tr>

				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>