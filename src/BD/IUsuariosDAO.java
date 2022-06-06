package BD;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Modelo.Comic;
import Modelo.Usuario;

public interface IUsuariosDAO {
	public void getUser(String email,String passwd) throws IOException;
	public void deleteUser(Usuario u);
	public void insertUser(Usuario u) throws InterruptedException, UnknownHostException, IOException;
	public ArrayList<Usuario> getAllUsers() throws UnknownHostException, IOException;
}
