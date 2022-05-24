package Modelo;

import java.util.Arrays;

public class Usuario {
	String email;
	String contrase�a;
	static byte[] foto;

	public Usuario() {
	}

	public Usuario(String email, String contrase�a, byte[] foto) {
		super();
		this.email = email;
		this.contrase�a = contrase�a;
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contrase�a=" + contrase�a + ", foto=" + Arrays.toString(foto) + "]";
	}
	
	public boolean isFormated() {
		if(this.foto != null && !this.email.equals("") && !this.contrase�a.equals("")) {
			return true;
		}
		return false;
	}

}
