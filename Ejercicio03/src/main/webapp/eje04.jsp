<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
final String PIEDRA1 = "&#x1F91C;";
final String PIEDRA2 = "&#x1F91B;";
final String TIJERAS = "&#x1F596;";
final String PAPEL   = "&#x1F91A;";

final String msg[] = {"¡Empate !", " Ha ganado el jugador 1"," Ha ganado el jugador 2"};
final String valores[] = {PIEDRA1,TIJERAS,PAPEL,PIEDRA2};

int ganador (String valor1, String valor2){
   
   int ganador =0;
   if (valor1.contentEquals(PIEDRA2)) valor1 = PIEDRA1;
   if (valor2.contentEquals(PIEDRA2)) valor2 = PIEDRA1;
   if (valor1.contentEquals(valor2) ) return ganador; // Empate   

   switch (valor1){
      case PIEDRA1: ganador =  ( valor2 == TIJERAS )?1:2; break; 
      case TIJERAS: ganador =  ( valor2 == PAPEL   )?1:2; break;
      case PAPEL  : ganador =  ( valor2 == PIEDRA1 )?1:2; break;
    }
   return ganador;
}
%>
<%

String jugador1 = valores[(int) Math.round(Math.random()*2)];
String jugador2 = valores[(int) Math.round(Math.random()*2)+1];

// Devuelve 0 empate, 1 ganador 1, 2 ganador 2

%>

<html>
<head>
<title>Online PHP Script Execution</title>
</head>
<body>
<h1>¡Piedra, papel, tijera!</h1>

    <p>Actualice la página para mostrar otra partida.</p>

    <table>
      <tr>
        <th>Jugador 1</th>
        <th>Jugador 2</th>
      </tr>
      <tr>
        <td><span style="font-size: 7rem"><%= jugador1 %></span></td>
        <td><span style="font-size: 7rem"><%= jugador2 %></span></td>
      </tr>
      <tr>
        <th colspan="2"><%= msg[ganador(jugador1,jugador2)] %></th>
      </tr>
    </table>
</body>
</html>