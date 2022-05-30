package Modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Usuario implements Serializable{
	String email;
	String contrasenha;
	byte[] foto;
	private static Usuario miUser;

	public static Usuario miUser(String email,String contrasenha, byte[] foto) {
		miUser=new Usuario(email,contrasenha,foto);
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

	public Usuario(String email, String contrasenha, byte[] foto) {
		super();
		this.email = email;
		this.contrasenha = contrasenha;
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contrasenha=" + contrasenha + ", foto=" + /*Arrays.toString(foto) +*/ "]";
	}
	
	public boolean isFormated() {
		if(this.foto != null && !this.email.equals("") && !this.contrasenha.equals("")) {
			return true;
		}
		return false;
	}

}
