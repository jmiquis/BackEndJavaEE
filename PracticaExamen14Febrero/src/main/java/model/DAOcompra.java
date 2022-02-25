package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class DAOcompra {

	
	private Connection conexion;
	private static DAOcompra modelo;
	
	private PreparedStatement getSocioSTM;
	private PreparedStatement getProductoSTM;
	private PreparedStatement updateSocioSTM;
	
	
	//metodo que devueve la instancia con patron singleton
		public static synchronized DAOcompra initModelo() {
			if (modelo == null) {
				modelo = new DAOcompra();
			}
			return modelo;
		}
		
		//constructor privado
		private DAOcompra() {
			try {
				// Class.forName("org.apache.derby.jdbc.ClientDriver");
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/comprasdb", "root", "root");
				//se da valor a las sentencias preparadas
				getSocioSTM        = conexion.prepareStatement("SELECT * FROM socios WHERE cod_socio=? AND clave=?");
				getProductoSTM  = conexion.prepareStatement("SELECT * FROM productos WHERE cod_pro = ?  ");
				updateSocioSTM  = conexion.prepareStatement("UPDATE socios SET cantidad_max=(cantidad_max-?) WHERE cod_socio=?");

			} catch (Exception ex) {
				System.err.println(" Error - En la base de datos.");
				ex.printStackTrace();
			}
		}
		
		
		public Socio getOnePartner (String cod_socio,String clave) {
			
			try {
				//se da valor a las interrogantes 
				getSocioSTM.setString(1,cod_socio);
				getSocioSTM.setString(2,clave);
				//se obtienen resultados
				ResultSet onePartnerResultSet=getSocioSTM.executeQuery();
				//se recorre UNA sola vez porque sabemos de antemano que solo debe haber un resultado como maximo
				if(onePartnerResultSet.next()) {
					//se da valor al Socio auxiliar que se devolverá si se encuentra y null si no
					Socio partner = new Socio(
							onePartnerResultSet.getString("cod_socio"),
							onePartnerResultSet.getString("nombre"),
							onePartnerResultSet.getString("clave"),
							onePartnerResultSet.getInt("cantidad_max")
							);
					return partner;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		public Producto getOneProduct (String cod_producto) {
			
			try {
				//se da valor a las interrogantes 
				getProductoSTM.setString(1,cod_producto);
				//se obtienen resultados
				ResultSet oneProductResultSet=getProductoSTM.executeQuery();
				//se recorre UNA sola vez porque sabemos de antemano que solo debe haber un resultado como maximo
				if(oneProductResultSet.next()) {
					//se da valor al producto auxiliar que se devolverá si se encuentra y null si no
					Producto product = new Producto(
							oneProductResultSet.getString("cod_pro"),
							oneProductResultSet.getString("nombre_pro"),
							oneProductResultSet.getInt("precio")
							);
					return product;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		public boolean realizarCompra(Producto product,Socio partner) throws SQLException {
			updateSocioSTM.setInt(1, product.getPrecio());
			updateSocioSTM.setString(2, partner.getCod_socio());
			return updateSocioSTM.execute();
		}
		
		
		public void destroy(PreparedStatement statement) {
			try {
				statement.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				try {
					conexion.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
		// Evito que se pueda clonar el objeto.
	 @Override
	 public DAOcompra clone(){
	         try {
	             throw new CloneNotSupportedException();
	         } catch (CloneNotSupportedException ex) {
	         	ex.printStackTrace();
	         }
	         return null;
	     }  
	
}
