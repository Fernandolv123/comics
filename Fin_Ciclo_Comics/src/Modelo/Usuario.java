package Modelo;

import java.util.Arrays;

public class Usuario {
	String email;
	String contraseña;
	static byte[] foto;

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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
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
