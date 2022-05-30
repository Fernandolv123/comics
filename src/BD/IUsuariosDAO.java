package BD;

import Modelo.Usuario;

public interface IUsuariosDAO {
	public void getUser(String email,String passwd);
	public void deleteUser(Usuario u);
	public void insertUser(Usuario u);
}
