package modelo;

public class Usuario {

	private String nombre;
	private String login;
    private String password;
    private String comentario;
    private int bloqueo;
    private float saldo;
    
    public int getBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(int bloqueo) {
		this.bloqueo = bloqueo;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
