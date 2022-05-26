package Modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Usuario implements Serializable{
	String email;
	String contraseña;
	byte[] foto;
	private static Usuario miUser;

	public static Usuario miUser(String email,String contraseña, byte[] foto) {
		miUser=new Usuario(email,contraseña,foto);
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

	public Usuario(String email, String contraseña, byte[] foto) {
		super();
		this.email = email;
		this.contraseña = contraseña;
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenha() {
		return contraseña;
	}

	public void setContrasenha(String contraseña) {
		this.contraseña = contraseña;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contraseña=" + contraseña + ", foto=" + Arrays.toString(foto) + "]";
	}
	
	public boolean isFormated() {
		if(this.foto != null && !this.email.equals("") && !this.contraseña.equals("")) {
			return true;
		}
		return false;
	}

}
