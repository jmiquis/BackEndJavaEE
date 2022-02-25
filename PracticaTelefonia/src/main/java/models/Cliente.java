package models;

public class Cliente {
	private String telefono;
	private String nombre;
	private int      puntos;
	
	
	
	
	public Cliente(String telefono, String nombre, int puntos) {
		
		this.telefono  = telefono;
		this.nombre  = nombre;
		this.puntos   = puntos;
		
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
	
}
