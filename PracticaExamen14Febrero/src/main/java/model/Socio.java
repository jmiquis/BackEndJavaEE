package model;

public class Socio {
	
	public Socio(String cod_socio, String nombre, String clave, int cantidad_max) {
		super();
		this.cod_socio = cod_socio;
		this.nombre = nombre;
		this.clave = clave;
		this.cantidad_max = cantidad_max;
	}
	
	private String cod_socio;
	public String getCod_socio() {
		return cod_socio;
	}
	public void setCod_socio(String cod_socio) {
		this.cod_socio = cod_socio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getCantidad_max() {
		return cantidad_max;
	}
	public void setCantidad_max(int cantidad_max) {
		this.cantidad_max = cantidad_max;
	}
	private String nombre;
	private String clave;
	private int cantidad_max;
	
	
	
	
}
		