<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% HttpSession sesion = request.getSession(); %>
    <% String tarjetaSesion="No hay ninguna tarjeta seleccionada"; %>
    <% String tarjetaUsuario = request.getParameter("nuevatarjeta"); %>
    
    <% if(tarjetaUsuario!=null){
    		sesion.setAttribute("tarjetaSesion", tarjetaUsuario);
    		tarjetaSesion   ="<img src ='imagenes/"+ sesion.getAttribute("tarjetaSesion").toString()+".gif'/>";
    		}
    	else{
    			if(sesion.getAttribute("tarjetaSession")!=null){
    				tarjetaSesion = sesion.getAttribute("tarjetaSession").toString();
    			}
    		}
    %>
    
    
    
    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forma de pago</title>
    </head>
    <body>
	 <H2> SU FORMA DE PAGO ACTUAL ES</H2> 
        
         <%=tarjetaSesion %>
         <h2>SELECCIONE UNA NUEVA TARJETA DE CREDITO </h2><br>
         <a href = 'sesiones.jsp?nuevatarjeta=cashu'><img  				src='imagenes/cashu.gif' /></a>&ensp;
         <a href = 'sesiones.jsp?nuevatarjeta=cirrus1'><img  			src='imagenes/cirrus1.gif' /></a>&ensp;
         <a href = 'sesiones.jsp?nuevatarjeta=dinersclub'><img  		src='imagenes/dinersclub.gif' /></a>&ensp;
         <a href = 'sesiones.jsp?nuevatarjeta=mastercard1'><img    src='imagenes/mastercard1.gif'/></a>&ensp;
         <a href = 'sesiones.jsp?nuevatarjeta=paypal'><img  			src='imagenes/paypal.gif' /></a>&ensp;
         <a href = 'sesiones.jsp?nuevatarjeta=visa1'><img  				src='imagenes/visa1.gif' /></a>&ensp;
         <a href = 'sesiones.jsp?nuevatarjeta=visa_electron'><img  src='imagenes/visa_electron.gif'/></a>
    </body>
</html>

