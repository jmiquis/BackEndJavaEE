package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.DataAccess;
import models.Producto;



public class DataAccess {

	
	private static DataAccess model;
	private static Connection connect = null;
	private PreparedStatement getAllProductsSTM    = null;
    private PreparedStatement getOneProductsSTM  = null;
    private PreparedStatement deleteProductsSTM    = null;
    private PreparedStatement modifyProductsSTM   = null;
    private PreparedStatement createProductsSTM    = null;
	
	public static synchronized  DataAccess initModelo(){
		if (model == null){
			model = new DataAccess();
		}
		return model;
	}
	
	private DataAccess (){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/empresa", "root", "root");

			
			 this.getAllProductsSTM     = connect.prepareStatement("SELECT * FROM productos");
		     this.getOneProductsSTM   = connect.prepareStatement("SELECT * FROM productos  WHERE PRODUCTO_NO=?");
		     this.deleteProductsSTM    = connect.prepareStatement("DELETE FROM productos WHERE PRODUCTO_NO =?");
		     this.modifyProductsSTM   = connect.prepareStatement("UPDATE productos SET DESCRIPCION=?, PRECIO_ACTUAL=?, STOCK_DISPONIBLE=? WHERE PRODUCTO_NO=?");
		     this.createProductsSTM    = connect.prepareStatement("INSERT INTO productos (PRODUCTO_NO,DESCRIPCION,PRECIO_ACTUAL,STOCK_DISPONIBLE) Values(?,?,?,?)");
			

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	public static void closeModel(){
        if (model != null){
           try {
			connect.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        }
    }
	

    // Obtengo un producto
    public Producto getProduct(int producto_no) {
    	Producto fetchedProduct = null;
    	
        ResultSet productResultset; 
        try {
        	this.getOneProductsSTM.setInt(1, producto_no);
        	
        	productResultset   =  this.getOneProductsSTM.executeQuery();
        	
			  if  ( productResultset.next()) {
					  fetchedProduct = new Producto(
							 productResultset.getInt("PRODUCTO_NO"),
							 productResultset.getString("DESCRIPCION"),
							 productResultset.getFloat("PRECIO_ACTUAL"),
							 productResultset.getInt("STOCK_DISPONIBLE")
							 );
				  
			  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
          	return fetchedProduct;
    }
    
    //obtengo todos los productos
    
    public ArrayList<Producto> getAllProducts() {
    	Producto aux = null;
    	
    	ArrayList<Producto> productos = new ArrayList <Producto>();
    	ResultSet productsResultset;
    	
    	try {
			 productsResultset    = this.getAllProductsSTM.executeQuery();
			 while(productsResultset.next()) {
				  aux = new Producto(
						 productsResultset.getInt("PRODUCTO_NO"),
						 productsResultset.getString("DESCRIPCION"),
						 productsResultset.getFloat("PRECIO_ACTUAL"),
						 productsResultset.getInt("STOCK_DISPONIBLE")
						 );
				  productos.add(aux);
			 }
			 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	return productos;
    }
	
    // UPDATE
    public boolean modifyProduct(Producto product){
      
    	boolean resu = false;
        try {
        	modifyProductsSTM.setString(1,product.getDescripcion());
        	modifyProductsSTM.setFloat(2,product.getPrecio_actual());
        	modifyProductsSTM.setInt(3,product.getStock_disponible());
        	modifyProductsSTM.setInt(4,product.getProducto_no());
	        resu = modifyProductsSTM.execute();
	        
		} catch (SQLException e) {
			
			e.printStackTrace();
		}       
        return resu;
    }
    
    //INSERT
    public boolean insertProduct(Producto product){
        boolean resu = false;
    	try {
    		createProductsSTM.setInt(1,product.getProducto_no());
    		createProductsSTM.setString(2,product.getDescripcion());
    		createProductsSTM.setFloat(3,product.getPrecio_actual());
    		createProductsSTM.setInt(4,product.getStock_disponible());
    		resu = createProductsSTM.execute();
        
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
        return resu;
    }
    
    
    // DELETE Elimino un usuario
    public boolean deleteProduct(int producto_no)  {
        boolean resu = false;
    	
        try {
        	this.deleteProductsSTM.setInt (1, producto_no);
			resu = this.deleteProductsSTM.execute();
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
       return resu; 
        
    }  
    
    
    
	
	// Evito que se pueda clonar el objeto.
 @Override
 public DataAccess clone(){
         try {
             throw new CloneNotSupportedException();
         } catch (CloneNotSupportedException ex) {
         	ex.printStackTrace();
         }
         return null; 
     }    
}


