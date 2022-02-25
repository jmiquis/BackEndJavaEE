package controllers;

import models.DataAccess;
import models.Producto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Actions {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	Actions (HttpServletRequest request, HttpServletResponse response ){
		this.request = request;
		this.response = response;
	}
	
	void actionCreate() throws ServletException, IOException {
		 Producto product = new Producto();
		 request.setAttribute("orden", "nuevo");
		 request.setAttribute("product", product);
		 request.getRequestDispatcher("/WEB-INF/formularioProductos.jsp").forward(request, response);
	}
	 void actionDelete ( int producto_no){
		 DataAccess db = DataAccess.initModelo();
		 db.deleteProduct(producto_no);	
	}
	void actionModify (int producto_no ) throws ServletException, IOException {
		 DataAccess db = DataAccess.initModelo();
		    Producto product = db.getProduct(producto_no);
		    request.setAttribute("orden", "Modificar");
		    request.setAttribute("product", product);
			request.getRequestDispatcher("/WEB-INF/formularioProductos.jsp").forward(request, response);

	}
	 void accionDetails  (int producto_no ) throws ServletException, IOException {
		    DataAccess db = DataAccess.initModelo();
		    Producto product = db.getProduct(producto_no);
		    request.setAttribute("orden", "Detalles");
		    request.setAttribute("product", product  );
			request.getRequestDispatcher("/WEB-INF/formularioProductos.jsp").forward(request, response);
	}
    void actionTerminate() {
    	System.out.println("->>> accionTerminar   \n");
    }
    
    void actionPostCreate() throws Exception {	   	   	
    	   			  Functions.dataChecker(request);
    	    		  Producto product = Functions.setProductValues(request);
    	    	   	  DataAccess db = DataAccess.initModelo();
    	    	   	  db.insertProduct(product);
    	   		} 	
    
    
    void actionPostModify() throws Exception {
    	    Functions.dataChecker(request);
    		Producto product = Functions.setProductValues(request);
    	   	DataAccess db = DataAccess.initModelo();
    	   	db.modifyProduct(product);
		 
    }
  }
