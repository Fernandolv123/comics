package Modelo;

import java.io.Serializable;

public class Transaccion implements Serializable{
	private int id;
	private String usumail;
	private String transaccion;
	private String isbncomic;
	
	public Transaccion(String usumail, String transaccion, String isbncomic) {
		super();
		this.usumail = usumail;
		this.transaccion = transaccion;
		this.isbncomic = isbncomic;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsumail() {
		return usumail;
	}
	
	public void setUsumail(String usumail) {
		this.usumail = usumail;
	}
	
	public String getTransaccion() {
		return transaccion;
	}
	
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	
	public String getIsbncomic() {
		return isbncomic;
	}
	
	public void setIsbncomic(String isbncomic) {
		this.isbncomic = isbncomic;
	}
	
	@Override
	public String toString() {
		return "Transaccion [usumail=" + usumail + ", transaccion=" + transaccion + ", isbncomic=" + isbncomic + "]";
	}
	
	
}
