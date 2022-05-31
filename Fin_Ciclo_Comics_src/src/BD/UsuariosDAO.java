package BD;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Conexion;
import Modelo.Usuario;
import Vista.LogginScreen;
import Vista.Hilos.HiloCliente;

public class UsuariosDAO implements IUsuariosDAO {
	Socket socketClient;
	HiloCliente hc;

	@Override
	public void getUser(String email, String passwd) {
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
				hc = new HiloCliente(socketClient, "getUser",
						"Select * from usuarios where email= \"" + email + "\" and contrasenha=\"" + passwd + "\"");
				hc.start();
				hc.join();
			}
			
		} catch (InterruptedException ex) {
			Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(Usuario u) {
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			hc = new HiloCliente(socketClient, "deleteUser", "delete from usuario where email=\"" + u.getEmail()
					+ "\" and password=\"" + u.getContrasenha() + "\"");
			hc.start();
			hc.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertUser(Usuario u) {
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			// WIP
			hc = new HiloCliente(socketClient, "insertUser",
					"insert into usuarios values(\"" + u.getEmail() + "\",\"" + u.getContrasenha() + "\",?)",
					u.getFoto());
			hc.start();
			hc.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Usuario> getAllUsers() throws UnknownHostException, IOException{
		try {
			socketClient = new Socket(Conexion.ip, Conexion.puerto);
			if (socketClient == null || !socketClient.isClosed() || !socketClient.isConnected()) {
				hc = new HiloCliente(socketClient, "getUsuarios","SELECT * FROM comic_library.usuarios");
				// Select * from Comics inner join colecciones on comics.id_coleccion =
				// colecciones.id
				hc.start();
				hc.join();
			}
		} catch (InterruptedException ex) {
			// System.out.println("ERROR");
			ex.printStackTrace();
			Logger.getLogger(LogginScreen.class.getName()).log(Level.SEVERE, null, ex);
		} 
		System.out.println(HiloCliente.listaU);
		return HiloCliente.listaU;
	}

}
