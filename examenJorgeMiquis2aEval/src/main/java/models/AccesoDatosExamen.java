package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.TryCatchFinally;



//Implemento el modelo con Patron singleton
public class AccesoDatosExamen{
	
	private static AccesoDatosExamen modelo;
	private Connection conexion                                = null;
	private PreparedStatement getClienteSTM         = null;
	private PreparedStatement getVehiculoSTM       = null;
	private PreparedStatement updateVehiculoSTM = null;
	private PreparedStatement updateClienteSTM   = null;
	


	
	//metodo que devueve la instancia con patron singleton
	public static synchronized AccesoDatosExamen initModelo() {
		if (modelo == null) {
			modelo = new AccesoDatosExamen();
		}
		return modelo;
	}
	
//constructor privado
	private AccesoDatosExamen() {
		try {
			// Class.forName("org.apache.derby.jdbc.ClientDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/mycardb", "root", "root");
			//se da valor a las sentencias preparadas
			getClienteSTM         = conexion.prepareStatement("SELECT * FROM clientes WHERE cod_cli = ? AND clave = ?");
			getVehiculoSTM       = conexion.prepareStatement("SELECT * from vehiculos WHERE localidad = ?  AND bateria > 0 and estado = 0 ORDER BY bateria DESC LIMIT 1");
			updateVehiculoSTM = conexion.prepareStatement("UPDATE vehiculos SET estado=1 where cod_car = ?");
			updateClienteSTM          = conexion.prepareStatement("UPDATE clientes SET cod_car = ? where cod_cli = ?");
	
		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	
	//OBTENGO CLIENTE
	public Cliente getCliente(String cod_cli, String clave) {
		
		try {
			
			getClienteSTM.setString(1,cod_cli);
			getClienteSTM.setString(2, clave);
			
			ResultSet getClienteRSLT = getClienteSTM.executeQuery();
			
			if(getClienteRSLT.next()) {
				Cliente aux = new Cliente(
						getClienteRSLT.getString("cod_cli"),
						getClienteRSLT.getString("nombre"),
						getClienteRSLT.getString("clave"),
						getClienteRSLT.getInt("cod_car")
						);
				return aux;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//OBTENGO VEHICULOS DE UNA CUIDAD
	
	public Vehiculo getVehiculo(String localidad) {
		try {
			getVehiculoSTM.setString(1, localidad);
			ResultSet getVehiculoRSLT = getVehiculoSTM.executeQuery();
			
			if(getVehiculoRSLT.next()) {
				Vehiculo aux=new Vehiculo(
						getVehiculoRSLT.getInt("cod_car"),
						getVehiculoRSLT.getString("localidad"),
						getVehiculoRSLT.getInt("estado"),
						getVehiculoRSLT.getInt("bateria")
						);
				return aux;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	//operacion de alquiler
	
	public void alquiler (Vehiculo vehiculo, Cliente cliente) {
		
		try {
			updateVehiculoSTM.setInt(1, vehiculo.getCod_car());
			updateVehiculoSTM.execute();
			updateClienteSTM.setInt(1,vehiculo.getCod_car());
			updateClienteSTM.setString(2,cliente.getCod_cli());
			updateClienteSTM.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
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
public AccesoDatosExamen clone(){
       try {
           throw new CloneNotSupportedException();
       } catch (CloneNotSupportedException ex) {
       	ex.printStackTrace();
       }
       return null;
   }    
}
