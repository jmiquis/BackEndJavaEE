package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	
	private static DAO model = null;
	private Connection conexion;
	private PreparedStatement getClientsQuery =  null;
	
	
	//metodo que devueve la instancia con patron singleton
		public static synchronized DAO initModel() {
			if (model == null) {
				model = new DAO();
			}
			return model;
		}
		
		
		//constructor privado
		private DAO() {
			try {
				// Class.forName("org.apache.derby.jdbc.ClientDriver");
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/telefonia", "root", "root");
				//se da valor a las sentencias preparadas
				getClientsQuery = conexion.prepareStatement("SELECT * from clientes WHERE puntos <= ?");

			} catch (Exception ex) {
				System.err.println(" Error - En la base de datos.");
				ex.printStackTrace();
			}
		}
		
		public ArrayList <Cliente> getClientes(int puntos){
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			
			try {
				this.getClientsQuery.setInt(1, puntos);
				
				ResultSet clientsResultset = this.getClientsQuery.executeQuery();
				
				while(clientsResultset.next()) {
					Cliente clienteAux = new Cliente(
							clientsResultset.getString("telefono"),
							clientsResultset.getString("nombre"),
							clientsResultset.getInt("puntos")						
							);
					listaClientes.add(clienteAux);
				}
			} catch (Exception e) {
				
			}
			return listaClientes;		
		}
		
		
		// Evito que se pueda clonar el objeto.
		 @Override
		 public DAO clone(){
		         try {
		             throw new CloneNotSupportedException();
		         } catch (CloneNotSupportedException ex) {
		         	ex.printStackTrace();
		         }
		         return null; 
		     }
		 
		}

		
		


