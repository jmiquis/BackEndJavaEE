
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ELECTRONCAR</title>
</head>
<body>
<div align="center">
<img src="Imagenes/logo.jpg" height="100" width="100"></img>
<h2>
RESERVA DE VEH�CULO <br>
</h2>
<form method="post" action="reservar" name=" formulario">
C�digo:<input name="cod_cli" size=10><br>
Clave :<input name="clave"   size=10><br>
Localidad: 

<input name="localidad" value="MADRID"     type="radio" checked="checked">MADRID
<input name="localidad" value="BARCELONA"  type="radio">BARCELONA
<input name="localidad" value="BILBAO"     type="radio">BILBAO
<br>
<input type="submit"  value="Enviar">
</form>
</div>
</body>
</html>