package modelo;

public class Movimiento {

	private int num_mov;
	private String cod_cliente;
	String concepto;
	private int importe;
	
	public Movimiento(int num_mov, String cod_cliente, String concepto, int importe) {
		this.num_mov     = num_mov;
		this.cod_cliente  = cod_cliente;
		this.concepto      = concepto;
		this.importe        = importe;
	}

	public int getNum_mov() {
		return num_mov;
	}

	public void setNum_mov(int num_mov) {
		this.num_mov = num_mov;
	}

	public String getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(String cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}
	
	protected boolean checkEquals(String cod_cliente, int importmin, int importmax) {	
    	String codigoCliente = this.getCod_cliente();
    	int importe                = this.getImporte();
    	
    	if(codigoCliente.equals(cod_cliente)) {
    		if(importe<=importmax) {
    			if(importe>=importmin) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
	
	

}
