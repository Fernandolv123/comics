package Modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Usuario implements Serializable{
	String email;
	String contraseņa;
	byte[] foto;
	private static Usuario miUser;

	public static Usuario miUser(String email,String contraseņa, byte[] foto) {
		miUser=new Usuario(email,contraseņa,foto);
		return miUser;
	}
	
	public static Usuario miUser(Usuario u) {
		miUser=u;
		return miUser;
	}
	 
	public static Usuario miUser() {
		 return miUser;
	}
	 
	public Usuario() {
	}

	public Usuario(String email, String contraseņa, byte[] foto) {
		super();
		this.email = email;
		this.contraseņa = contraseņa;
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenha() {
		return contraseņa;
	}

	public void setContrasenha(String contraseņa) {
		this.contraseņa = contraseņa;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contraseņa=" + contraseņa + ", foto=" + Arrays.toString(foto) + "]";
	}
	
	public boolean isFormated() {
		if(this.foto != null && !this.email.equals("") && !this.contraseņa.equals("")) {
			return true;
		}
		return false;
	}

}
