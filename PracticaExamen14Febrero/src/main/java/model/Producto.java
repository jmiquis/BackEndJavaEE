package model;

public class Producto {
	
	private String cod_pro;
	private String nombre_pro;
	private int precio;
	private int stock;
	
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Producto(String cod_pro, String nombre_pro, int precio) {
	
		this.cod_pro = cod_pro;
		this.nombre_pro = nombre_pro;
		this.precio = precio;
	}
	
	public String getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(String cod_pro) {
		this.cod_pro = cod_pro;
	}
	public String getNombre_pro() {
		return nombre_pro;
	}
	public void setNombre_pro(String nombre_pro) {
		this.nombre_pro = nombre_pro;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
}
