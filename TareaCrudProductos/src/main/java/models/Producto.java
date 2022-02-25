package models;

public class Producto {

	private int producto_no;
	private String descripcion;
	private float precio_actual;
	private int stock_disponible;
	
	
	
	
	
	
	
	
	
	public Producto(int producto_no, String descripcion, float precio_actual, int stock_disponible) {

		this.producto_no         = producto_no;
		this.descripcion          = descripcion;
		this.precio_actual       = precio_actual;
		this.stock_disponible = stock_disponible;
	}
	
	public Producto() {
		this.producto_no         = 0;
		this.descripcion          = null;
		this.precio_actual       = 0;
		this.stock_disponible = 0;
	}
	
	
	public int getProducto_no() {
		return producto_no;
	}
	public void setProducto_no(int producto_no) {
		this.producto_no = producto_no;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio_actual() {
		return precio_actual;
	}
	public void setPrecio_actual(float precio_actual) {
		this.precio_actual = precio_actual;
	}
	public int getStock_disponible() {
		return stock_disponible;
	}
	public void setStock_disponible(int stock_disponible) {
		this.stock_disponible = stock_disponible;
	}
	
	
	
}
