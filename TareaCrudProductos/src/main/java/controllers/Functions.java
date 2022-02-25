package controllers;
import java.util.ArrayList;
import java.util.Map;
import models.Producto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import models.DataAccess;


public class Functions {
   
	
	static String  showData (){
	    
		String msg ="";
	    String titulos []= { "producto_no","descripcion","precio_actual","stock_disponible"};
	    msg += "<table>\n";
	     // Identificador de la tabla
	    msg += "<tr>";
	    for (int j=0; j < titulos.length; j++){
	        msg += "<th>"+titulos[j]+"</th>";
	    }  
	    msg += "</tr>";
	    DataAccess db = DataAccess.initModelo();
	    ArrayList <Producto> allProducts = db.getAllProducts();
	    for (Producto product: allProducts) {
	        msg += "<tr>";
	        msg += "<td>"+product.getProducto_no()  +"</td>";
	        msg += "<td>"+product.getDescripcion()   +"</td>";
	        msg += "<td>"+product.getPrecio_actual()+"</td>";
	        msg += "<td>"+product.getStock_disponible()+"</td>";
	        msg +="<td><a href='#' onclick=\"confirmarBorrar('"+product.getDescripcion()+"','"+product.getProducto_no()+"');\" >Borrar</a></td>\n";
	        msg +="<td><a href='?orden=Modificar&producto_no="+ product.getProducto_no() +"'>Modificar</a></td>\n";
	        msg +="<td><a href='?orden=Detalles&producto_no=" + product.getProducto_no() +"' >Detalles</a></td>\n";
	        msg +="</tr>\n";
	        
	    }
	    msg += "</table>";
	   
	    return msg;    
	}
	
	
	public static void dataChecker(HttpServletRequest request) throws Exception {
		HttpSession sesion = request.getSession();
		Map<String,String[]> valoresRequest  = request.getParameterMap();
		
		for (String key : valoresRequest.keySet()) {
			if(valoresRequest.get(key) == null || valoresRequest.get(key).toString().isEmpty() ) {
				sesion.setAttribute("errorMsg", "el valor "+key+" es incorrecto");
				throw new Exception();
			}
		}
		if(Float.parseFloat(request.getParameter("precio_actual"))<1.0 || Integer.parseInt(request.getParameter("stock_disponible").toString())<1) {
			sesion.setAttribute("errorMsg", "algún valor de los introducidos es incorrecto");
				throw new Exception();
		}
	}
	
	
	public static  Producto setProductValues(HttpServletRequest request) {
		 Producto product = new Producto();
	      product.setProducto_no( Integer.parseInt(request.getParameter("producto_no")));
	      product.setDescripcion(request.getParameter("descripcion"));
	      product.setPrecio_actual(Float.parseFloat(request.getParameter("precio_actual")));
	      product.setStock_disponible(Integer.parseInt(request.getParameter("stock_disponible")));
	      return product;
	}
	
}
