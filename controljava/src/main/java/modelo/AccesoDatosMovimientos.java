package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




//Implemento el modelo con Patron singleton
public class AccesoDatosMovimientos {
	
	private static AccesoDatosMovimientos modelo;
	private Connection conexion                                              = null;
	private PreparedStatement buscarUsuarioPreparada     = null;
	private PreparedStatement actualizarVeces                    = null;
    private PreparedStatement buscarMovimientosCliente  = null;


	
	//metodo que devueve la instancia con patron singleton
	public static synchronized AccesoDatosMovimientos initModelo() {
		if (modelo == null) {
			modelo = new AccesoDatosMovimientos();
		}
		return modelo;
	}
	
//constructor privado
	private AccesoDatosMovimientos() {
		try {
			// Class.forName("org.apache.derby.jdbc.ClientDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/cuenta_bancaria", "root", "root");
			//se da valor a las sentencias preparadas
			buscarUsuarioPreparada     = conexion.prepareStatement("SELECT * FROM movimientos WHERE cod_cliente = ?");
			actualizarVeces                    = conexion.prepareStatement("UPDATE movimientos SET veces=veces+1 where cod_cliente = ?");

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	
	public boolean checkUser(String cod_cliente) {
		synchronized(buscarUsuarioPreparada) {
			try {
				buscarUsuarioPreparada.setString(1, cod_cliente);
				ResultSet result= buscarUsuarioPreparada.executeQuery();
				
				if (result.next()) {
					return true;
				}
				
			} catch (SQLException e) {		
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	
	public ArrayList<Movimiento> obtenerMovimientosCliente(String cod_cliente, int minimo, int maximo){
		ArrayList <Movimiento> movimientosCliente = new ArrayList <Movimiento> ();
		
			try {
				
				buscarUsuarioPreparada.setString(1, cod_cliente);
							
				
				ResultSet result= buscarUsuarioPreparada.executeQuery();
				
				while(result.next()) {
					int numMovAux          = result.getInt("num_mov");
					String codClienteAux = result.getString("cod_cliente");
					String conceptoAux   = result.getString("concepto");
					int importeAux           = result.getInt("importe");
					if(importeAux<=maximo && importeAux>=minimo) {
					Movimiento aux = new Movimiento(numMovAux,codClienteAux,conceptoAux,importeAux);
					movimientosCliente.add(aux);
					}
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
		return movimientosCliente;
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
 public AccesoDatosMovimientos clone(){
         try {
             throw new CloneNotSupportedException();
         } catch (CloneNotSupportedException ex) {
         	ex.printStackTrace();
         }
         return null;
     }    
}
